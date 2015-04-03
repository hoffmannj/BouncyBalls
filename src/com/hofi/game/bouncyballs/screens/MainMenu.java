package com.hofi.game.bouncyballs.screens;

import org.jbox2d.common.Vec2;

import com.hofi.game.bouncyballs.Button;
import com.hofi.game.bouncyballs.CharMap;
import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.interfaces.ButtonHandler;
import com.hofi.game.bouncyballs.interfaces.GameScreen;

public class MainMenu implements GameScreen {

	protected CharMap charMap;
	private final String str = "High Score: ";
	private final String strName = "MainMenu";
	private String strHighScore;

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

		float y = 390;
		/*if (Common.lastFinishedLevelNumber == 0)
			y -= 80;*/
		Common.addButton(this, new Vec2(400, y), "btnplay",
				new ButtonHandler() {
					@Override
					public void handle(Button sender, Object owner) {
						Common.appPreferences.putInt(
								Common.lastFinishedlevelKey, 0);
						Common.appPreferences.putLong(
								Common.lastFinishedlevelScoreKey, 0);
						Common.lastFinishedLevelNumber = 0;
						Common.gamePlayManager.setCurrentScreen("level_1");
					}
				});
		y -= 80;

		if (Common.lastFinishedLevelNumber > 0) {
			Common.addButton(this, new Vec2(400, y), "btncontinue",
					new ButtonHandler() {
						@Override
						public void handle(Button sender, Object owner) {
							Common.gameState.score = Common.appPreferences
									.getLong(Common.lastFinishedlevelScoreKey);
							int l = Common.lastFinishedLevelNumber + 1;
							if (l <= 0)
								l = 1;
							Common.gamePlayManager.setCurrentScreen("level_"
									+ l);
						}
					});
			y -= 80;
		}

		Common.addButton(this, new Vec2(400, y), "btnresethighscore",
				new ButtonHandler() {
					@Override
					public void handle(Button sender, Object owner) {
						Common.gameState.score = 0;
						Common.appPreferences.putLong(Common.highScoreKey, 0);
						Common.appPreferences.putInt(
								Common.lastFinishedlevelKey, 0);
						Common.appPreferences.putLong(
								Common.lastFinishedlevelScoreKey, 0);
						Common.lastFinishedLevelNumber = 0;
						Common.previousHighScore = 0;
						Common.highScore = 0;
						Common.gamePlayManager.forceScreenChange();
						Common.gamePlayManager.setCurrentScreen("mainmenu");
					}
				});
		y -= 80;

		Common.addButton(this, new Vec2(400, y), "btnquit",
				new ButtonHandler() {
					@Override
					public void handle(Button sender, Object owner) {
						Common.mainActivity.finish();
					}
				});
		y -= 80;

		strHighScore = str + Common.highScore;
	}

	@Override
	public void initFromGameState() {

	}

	@Override
	public void draw() {
		Common.uiObjects.draw();
		charMap.drawString(strHighScore,
				400 - charMap.measureString(strHighScore) / 2, 80);
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
