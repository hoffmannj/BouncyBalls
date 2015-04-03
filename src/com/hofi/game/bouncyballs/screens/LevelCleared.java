package com.hofi.game.bouncyballs.screens;

import org.jbox2d.common.Vec2;

import com.hofi.game.bouncyballs.Button;
import com.hofi.game.bouncyballs.CharMap;
import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.interfaces.ButtonHandler;
import com.hofi.game.bouncyballs.interfaces.GameScreen;

public class LevelCleared implements GameScreen {

	protected CharMap charMap;
	protected String newHighScore = "New High Score!";

	private final String strName = "LevelCleared";
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
		Common.gameState.levelCleared = true;
		Common.gameState.score += (int) (100.0f / Common.gameState.lastLevelKickCount);
		Common.saveHighScore();
		Common.saveGameState();
		charMap = new CharMap();

		Common.addButton(this, new Vec2(400, 280), "btnnextlevel",
				new ButtonHandler() {
					@Override
					public void handle(Button sender, Object owner) {
						Common.gamePlayManager.setCurrentScreen("level_"
								+ (Common.gameState.level + 1));
					}
				});

		Common.addButton(this, new Vec2(400, 200), "btnquit",
				new ButtonHandler() {
					@Override
					public void handle(Button sender, Object owner) {
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
		charMap.drawString(strScore, 300, 350);
		charMap.drawString(Long.toString(Common.gameState.score),
				300 + charMap.measureString(strScore), 350);
		if (Common.gameState.score == Common.highScore
				&& Common.gameState.score > Common.previousHighScore) {
			charMap.drawString(newHighScore, 260, 120, 1, 0, 0, 1);
		}
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
