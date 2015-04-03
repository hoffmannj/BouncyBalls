package com.hofi.game.bouncyballs.screens;

import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.gameObjects.Borders;
import com.hofi.game.bouncyballs.gameObjects.BowlingBall;
import com.hofi.game.bouncyballs.gameObjects.BrickWall;
import com.hofi.game.bouncyballs.gameObjects.Pot;

public class Level5 extends LevelScreen {

	private final String strName = "level_5";
	private final String strBrickwall1 = "brickwall1";
	private final String strBrickwall2 = "brickwall2";

	@Override
	public void init() {
		super.init();

		levelNumber = 5;

		Common.addGameObject("borders", new Borders().init());
		Common.addGameObject("pot", new Pot().init());
		Common.addGameObject("brickwall1", new BrickWall().init());
		Common.addGameObject("brickwall2", new BrickWall().init());
		((BrickWall) Common.getGameObject("brickwall2")).startY = ((BrickWall) Common
				.getGameObject("brickwall1")).startY + 800;
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
		Common.gameObjects.getObject(strBrickwall1).update();
		Common.gameObjects.getObject(strBrickwall2).update();
	}

	@Override
	public String getName() {
		return strName;
	}

}
