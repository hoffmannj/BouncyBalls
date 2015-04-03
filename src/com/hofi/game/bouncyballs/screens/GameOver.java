package com.hofi.game.bouncyballs.screens;

import org.jbox2d.common.Vec2;

import com.hofi.game.bouncyballs.Button;
import com.hofi.game.bouncyballs.CharMap;
import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.interfaces.ButtonHandler;
import com.hofi.game.bouncyballs.interfaces.GameScreen;

public class GameOver implements GameScreen {

	protected CharMap charMap;
	private final String strName = "GameOver";
	private final String strGameOver = "Game Over";
	private final String strScore = "Score: ";

	@Override
	public void TouchStart() {

	}

	@Override
	public void TouchEnd() {

	}

	@Override
	public void TouchMove() {

	}

	@Override
	public String getName() {
		return strName;
	}

	@Override
	public void init() {
		charMap = new CharMap();

		Common.addButton(this, new Vec2(400, 200), "btnquit",
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
						Common.lastFinishedLevelNumber = 0;
						Common.gameState.levelCleared = false;
						Common.saveGameState();
						Common.gamePlayManager.setCurrentScreen("mainmenu");
					}
				});
	}

	@Override
	public void initFromGameState() {

	}

	@Override
	public void draw() {
		Common.uiObjects.draw();
		charMap.drawString(strGameOver, 30, 260, 350, 0, 0, 1, 1);
		charMap.drawString(strScore, 300, 270);
		charMap.drawString(Long.toString(Common.gameState.score),
				300 + charMap.measureString(strScore), 270);
	}

	@Override
	public void update() {

	}

	@Override
	public void updateGameState() {

	}

	@Override
	public void cleanUp() {

	}
}
