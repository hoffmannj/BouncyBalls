package com.hofi.game.bouncyballs.screens;

import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.gameObjects.Borders;
import com.hofi.game.bouncyballs.gameObjects.BowlingBall;
import com.hofi.game.bouncyballs.gameObjects.CutCircle;
import com.hofi.game.bouncyballs.gameObjects.Pot;

public class Level7 extends LevelScreen {

	private final String strName = "level_7";
	private final String strCutcircle = "cutcircle";

	@Override
	public void init() {
		super.init();

		levelNumber = 7;

		Common.addGameObject("borders", new Borders().init());
		Common.addGameObject("pot", new Pot().init());
		Common.addGameObject("ball", new BowlingBall().init());
		Common.addGameObject("cutcircle", new CutCircle().init());

		setContactHandler();
	}

	@Override
	public void draw() {
		super.draw();
	}

	@Override
	public void update() {
		super.update();
		Common.gameObjects.getObject(strCutcircle).update();
	}

	@Override
	public String getName() {
		return strName;
	}

}
