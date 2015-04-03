package com.hofi.game.bouncyballs.screens;

import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.gameObjects.Borders;
import com.hofi.game.bouncyballs.gameObjects.BowlingBall;
import com.hofi.game.bouncyballs.gameObjects.Pot;
import com.hofi.game.bouncyballs.gameObjects.Propeller;

public class Level2 extends LevelScreen {

	private final String strName = "level_2";
	private final String strPropeller = "propeller";

	@Override
	public void init() {
		super.init();

		levelNumber = 2;

		Common.addGameObject("borders", new Borders().init());
		Common.addGameObject("pot", new Pot().init());
		Common.addGameObject("propeller", new Propeller().init());
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
		Common.gameObjects.getObject(strPropeller).update();
	}

	@Override
	public String getName() {
		return strName;
	}

}
