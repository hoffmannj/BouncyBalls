package com.hofi.game.bouncyballs.screens;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Fixture;

import com.hofi.game.bouncyballs.Arrow;
import com.hofi.game.bouncyballs.Button;
import com.hofi.game.bouncyballs.CharMap;
import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.DrawablePhysicsObject;
import com.hofi.game.bouncyballs.UIObject;
import com.hofi.game.bouncyballs.VectorData;
import com.hofi.game.bouncyballs.interfaces.ButtonHandler;
import com.hofi.game.bouncyballs.interfaces.ContactHandler;
import com.hofi.game.bouncyballs.interfaces.GameObject;
import com.hofi.game.bouncyballs.interfaces.GameScreen;

public class LevelScreen implements GameScreen {

	protected CharMap charMap;
	protected Arrow arrow;
	protected float divider = 10.0f;
	protected boolean ballMoving = false;
	protected boolean isBallKicked = false;
	protected boolean isInPot = false;
	protected String ballObjName = "ball";
	protected String potObjName = "pot";
	protected int kickCount = 0;
	protected long startTime = -1;
	protected boolean levelSplash = true;
	protected int levelNumber = 1;
	protected UIObject btnReset = null;
	protected boolean reversedKick = false;

	private final String str1 = "Goal:";
	private final String str2 = "Get the ball into the pot";
	private final String str3 = "with 3 or less flicks.";
	private final String str4 = "Touch the screen to start";
	private final String strFlicks = "Flicks: ";
	private final String strLevel = "Level ";
	private final String strPer = " / ";

	@Override
	public void TouchStart() {
		if (levelSplash || kickCount >= 3)
			return;
		arrow.setVisible(true);
		arrow.setByTouchVector(Common.getGameObject(ballObjName), reversedKick);
	}

	@Override
	public void TouchEnd() {
		if (levelSplash || kickCount >= 3) {
			levelSplash = false;
			return;
		}
		VectorData vd = Common.input.getTouchVectorData();
		if (reversedKick) {
			Common.getGameObject(ballObjName)
					.getDPO()
					.getPhysicsObject()
					.setLinearVelocity(-vd.vector.x / divider,
							-vd.vector.y / divider);
		} else {
			Common.getGameObject(ballObjName)
					.getDPO()
					.getPhysicsObject()
					.setLinearVelocity(vd.vector.x / divider,
							vd.vector.y / divider);
		}
		ballMoving = true;
		isBallKicked = true;
		arrow.setVisible(false);
		handleKick();
	}

	@Override
	public void TouchMove() {
		if (levelSplash || kickCount >= 3)
			return;
		arrow.setByTouchVector(Common.getGameObject(ballObjName), reversedKick);
	}

	protected void handleKick() {
		kickCount++;
	}

	@Override
	public String getName() {
		return "Level";
	}

	@Override
	public void init() {
		Common.gameState.levelCleared = false;
		charMap = new CharMap();
		arrow = new Arrow();

		Common.addButton(this, new Vec2(720, 430), "btnquit",
				new ButtonHandler() {
					@Override
					public void handle(Button sender, Object owner) {
						Common.gameState.score = 0;
						Common.gameState.level = 0;
						Common.gameState.screenName = "mainmenu";
						Common.gameState.livesLeft = 3;
						Common.gameState.ballPosition.set(45, 45);
						Common.gameState.ballRotation = 0;
						Common.gameState.ballLinearVelocity.set(0, 0);
						Common.gameState.ballAngularVelocity = 0;
						Common.gamePlayManager.setCurrentScreen("mainmenu");
					}
				});

		btnReset = Common.addButton(this, new Vec2(470, 430), "btnendit",
				new ButtonHandler() {

					@Override
					public void handle(Button sender, Object owner) {
						Common.gamePlayManager.forceScreenChange();
						/*Common.gamePlayManager.setCurrentScreen("level_"
								+ levelNumber);*/
						Common.gamePlayManager.setCurrentScreen("gameover");
					}
				});
		btnReset.setEnabled(false);
	}

	protected void setContactHandler() {
		Common.mainContactHandler.addHandler(ballObjName, potObjName,
				new ContactHandler() {

					@Override
					public void handleEnter(DrawablePhysicsObject objectA,
							Fixture fixtureA, DrawablePhysicsObject objectB,
							Fixture fixtureB) {
						isInPot = true;
					}

					@Override
					public void handleLeave(DrawablePhysicsObject objectA,
							Fixture fixtureA, DrawablePhysicsObject objectB,
							Fixture fixtureB) {
						isInPot = false;
					}

				});
	}

	@Override
	public void draw() {
		if (levelSplash) {
			drawLevelSplash();
		} else {
			Common.gameObjects.draw();
			Common.uiObjects.draw();
			arrow.draw();
			charMap.drawString(strFlicks, 30, 440);
			charMap.drawString(Integer.toString(kickCount),
					30 + charMap.measureString(strFlicks), 440);
		}
	}

	protected void drawLevelSplash() {
		float x = 230;

		charMap.drawString(strLevel, 30, x, 330, 1, 1, 1, 1);
		x += charMap.measureString(strLevel, 30);
		charMap.drawString(Integer.toString(levelNumber), 30, x, 330, 1, 1, 1,
				1);
		x += charMap.measureString(Integer.toString(levelNumber), 30);
		charMap.drawString(strPer, 30, x, 330, 1, 1, 1, 1);
		x += charMap.measureString(strPer, 30);
		charMap.drawString(
				Integer.toString(Common.gamePlayManager.levelsCount), 30, x,
				330, 1, 1, 1, 1);

		charMap.drawString(str1, 20, 400 - charMap.measureString(str1) / 2,
				200, 0, 0, 1, 1);
		charMap.drawString(str2, 20, 400 - charMap.measureString(str2) / 2,
				170, 1, 1, 1, 1);
		charMap.drawString(str3, 20, 400 - charMap.measureString(str3) / 2,
				140, 1, 1, 1, 1);
		charMap.drawString(str4, 15, 400 - charMap.measureString(str4, 15) / 2,
				70, 1, 0, 0, 1);
	}

	@Override
	public void update() {
		if (levelSplash) {
			return;
		}

		if (kickCount == Common.maxKicks) {
			btnReset.setEnabled(true);
		}

		if (!ballMoving && isBallKicked && !isInPot
				&& kickCount >= Common.maxKicks) {
			Common.gamePlayManager.forceScreenChange();
			//Common.gamePlayManager.setCurrentScreen("level_" + levelNumber);
			Common.gamePlayManager.setCurrentScreen("gameover");
		}

		if (!isInPot && kickCount < Common.maxKicks)
			Common.getGameObject(ballObjName).getDPO().getPhysicsObject()
					.getBody().setAwake(true);

		if (ballMoving)
			ballMoving = Common.getGameObject(ballObjName).getDPO()
					.getPhysicsObject().isAwake();

		if (!ballMoving && isBallKicked && isInPot) {
			handleBallInPot();
			saveHighScore();
			saveGameState();
		}
	}

	protected void handleBallInPot() {
		if (kickCount > Common.maxKicks) {
			Common.gamePlayManager.setCurrentScreen("notgoodenough");
		} else {
			Common.gameState.lastLevelKickCount = kickCount;
			Common.gamePlayManager.setCurrentScreen("levelcleared");
		}
	}

	@Override
	public void cleanUp() {

	}

	@Override
	public void initFromGameState() {
		GameObject ball = Common.getGameObject(ballObjName);
		ball.getDPO().getPhysicsObject()
				.setPosition(Common.gameState.ballPosition);
		ball.getDPO().getPhysicsObject()
				.setAngle(Common.gameState.ballRotation);
		ball.getDPO().getPhysicsObject()
				.setLinearVelocity(Common.gameState.ballLinearVelocity);
		ball.getDPO().getPhysicsObject()
				.setAngularVelocity(Common.gameState.ballAngularVelocity);
	}

	@Override
	public void updateGameState() {
		GameObject ball = Common.getGameObject(ballObjName);
		Common.gameState.screenName = this.getName();
		Common.gameState.level = levelNumber;
		Common.gameState.ballPosition.set(ball.getDPO().getPhysicsObject()
				.getPosition());
		Common.gameState.ballRotation = ball.getDPO().getPhysicsObject()
				.getAngle();
		Common.gameState.ballLinearVelocity.set(ball.getDPO()
				.getPhysicsObject().getLinearVelocity());
		Common.gameState.ballAngularVelocity = ball.getDPO().getPhysicsObject()
				.getAngularVelocity();
	}

	protected void saveHighScore() {
		Common.saveHighScore();
	}

	protected void saveGameState() {
		Common.saveGameState();
	}
}
