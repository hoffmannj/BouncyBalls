package com.hofi.game.bouncyballs.screens;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Fixture;

import android.os.SystemClock;

import com.hofi.game.bouncyballs.Button;
import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.DrawablePhysicsObject;
import com.hofi.game.bouncyballs.Sprite;
import com.hofi.game.bouncyballs.gameObjects.Borders;
import com.hofi.game.bouncyballs.gameObjects.BowlingBall;
import com.hofi.game.bouncyballs.gameObjects.Portal;
import com.hofi.game.bouncyballs.gameObjects.Pot;
import com.hofi.game.bouncyballs.gameObjects.WoodBar;
import com.hofi.game.bouncyballs.interfaces.ButtonHandler;
import com.hofi.game.bouncyballs.interfaces.ContactHandler;

public class Level4 extends LevelScreen {

	private Vec2 newBallPos = new Vec2(-1, -1);
	protected long lastChange = SystemClock.uptimeMillis();
	private final long expectedTimeChange = 200;
	private Sprite hint;
	private boolean showHint = false;

	private final String strName = "level_4";
	private final String strPortal1 = "portal1";
	private final String strPortal2 = "portal2";

	@Override
	public void init() {
		super.init();

		levelNumber = 4;

		hint = new Sprite("level4_help");
		hint.setPosition(400, 240);
		hint.setVisible(false);

		Common.addGameObject("borders", new Borders().init());
		Common.addGameObject("pot", new Pot().init());
		Common.addGameObject("ball", new BowlingBall().init());

		Common.addGameObject("woodbar1", new WoodBar().init());
		Common.getGameObject("woodbar1").getDPO().getPhysicsObject()
				.setAngle(75f);
		Common.getGameObject("woodbar1").getDPO().getPhysicsObject()
				.setWorldPosition(595, 160);

		Common.addGameObject("woodbar2", new WoodBar().init());
		Common.getGameObject("woodbar2").getDPO().getPhysicsObject()
				.setAngle(-75f);
		Common.getGameObject("woodbar2").getDPO().getPhysicsObject()
				.setWorldPosition(705, 160);

		Common.addGameObject("portal1", new Portal().init());
		Common.getGameObject("portal1").getDPO().getPhysicsObject()
				.setWorldPosition(240, 240);
		Common.addGameObject("portal2", new Portal().init());
		Common.getGameObject("portal2").getDPO().getPhysicsObject()
				.setWorldPosition(650, 230);

		Common.addButton(this, new Vec2(600, 430), "btnhowto",
				new ButtonHandler() {
					@Override
					public void handle(Button sender, Object owner) {
						showHint = true;
					}
				});

		setContactHandler();
	}

	@Override
	public void draw() {
		if (showHint) {
			hint.setVisible(true);
			hint.draw();
			hint.setVisible(false);
			return;
		}
		super.draw();
	}

	@Override
	public void update() {
		super.update();
		Common.getGameObject(strPortal1).update();
		Common.getGameObject(strPortal2).update();
		if (newBallPos.x >= 0 && newBallPos.y >= 0) {
			Common.getGameObject(ballObjName).getDPO().getPhysicsObject()
					.setWorldPosition(newBallPos);
			newBallPos.set(-1, -1);
			lastChange = SystemClock.uptimeMillis();
		}
	}

	@Override
	public String getName() {
		return strName;
	}

	@Override
	protected void setContactHandler() {
		super.setContactHandler();
		Common.mainContactHandler.addHandler(ballObjName, "portal1",
				new ContactHandler() {

					@Override
					public void handleEnter(DrawablePhysicsObject objectA,
							Fixture fixtureA, DrawablePhysicsObject objectB,
							Fixture fixtureB) {
						long currTime = SystemClock.uptimeMillis();
						if (currTime - lastChange < expectedTimeChange)
							return;
						newBallPos.set(Common.getGameObject("portal2").getDPO()
								.getPhysicsObject().getWorldPosition());
					}

					@Override
					public void handleLeave(DrawablePhysicsObject objectA,
							Fixture fixtureA, DrawablePhysicsObject objectB,
							Fixture fixtureB) {
					}

				});
		Common.mainContactHandler.addHandler(ballObjName, "portal2",
				new ContactHandler() {

					@Override
					public void handleEnter(DrawablePhysicsObject objectA,
							Fixture fixtureA, DrawablePhysicsObject objectB,
							Fixture fixtureB) {
						long currTime = SystemClock.uptimeMillis();
						if (currTime - lastChange < expectedTimeChange)
							return;
						newBallPos.set(Common.getGameObject("portal1").getDPO()
								.getPhysicsObject().getWorldPosition());
					}

					@Override
					public void handleLeave(DrawablePhysicsObject objectA,
							Fixture fixtureA, DrawablePhysicsObject objectB,
							Fixture fixtureB) {
					}

				});
	}

	@Override
	public void TouchEnd() {
		if (showHint) {
			showHint = false;
			return;
		}
		super.TouchEnd();
	}
}
