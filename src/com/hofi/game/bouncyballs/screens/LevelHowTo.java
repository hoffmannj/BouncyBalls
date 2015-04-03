package com.hofi.game.bouncyballs.screens;

import com.hofi.game.bouncyballs.CharMap;
import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.Sprite;
import com.hofi.game.bouncyballs.interfaces.GameScreen;

public class LevelHowTo implements GameScreen {

	private Sprite image;
	protected CharMap charMap;
	private final String str = "Touch the screen to go back";

	private final String strName = "HowTo";

	@Override
	public void TouchStart() {

	}

	@Override
	public void TouchEnd() {
		Common.gamePlayManager.setCurrentScreen("level_"
				+ Common.gameState.level);
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

		image = new Sprite("level" + Common.gameState.level + "_help");
		image.setPosition(400, 240);
		image.setScale(800, 480);
	}

	@Override
	public void draw() {
		image.draw();
		charMap.drawString(str, 400 - charMap.measureString(str) / 2, 440);
	}

	@Override
	public void update() {

	}

	@Override
	public void cleanUp() {

	}

	@Override
	public void initFromGameState() {

	}

	@Override
	public void updateGameState() {
	}
}
