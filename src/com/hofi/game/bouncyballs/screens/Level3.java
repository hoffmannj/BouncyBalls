package com.hofi.game.bouncyballs.screens;

import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.gameObjects.Borders;
import com.hofi.game.bouncyballs.gameObjects.BowlingBall;
import com.hofi.game.bouncyballs.gameObjects.BrickWall;
import com.hofi.game.bouncyballs.gameObjects.Pot;

public class Level3 extends LevelScreen {

	private final String strName = "level_3";
	private final String strBrickwall = "brickwall";

	@Override
	public void init() {
		super.init();

		levelNumber = 3;

		Common.addGameObject("borders", new Borders().init());
		Common.addGameObject("pot", new Pot().init());
		Common.addGameObject("brickwall", new BrickWall().init());
		Common.addGameObject("ball", new BowlingBall().init());

		setContactHandler();
	}

	@Override
	public void draw() {
		super.draw();
		if (levelSplash)
			return;
	}

	@Override
	public void update() {
		super.update();
		Common.gameObjects.getObject(strBrickwall).update();
	}

	@Override
	public String getName() {
		return strName;
	}

}
