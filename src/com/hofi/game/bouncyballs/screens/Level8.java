package com.hofi.game.bouncyballs.screens;

import org.jbox2d.dynamics.BodyType;

import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.gameObjects.Borders;
import com.hofi.game.bouncyballs.gameObjects.BowlingBall;
import com.hofi.game.bouncyballs.gameObjects.LongWoodBar;
import com.hofi.game.bouncyballs.gameObjects.Pot;

public class Level8 extends LevelScreen {

	private final String strName = "level_8";

	@Override
	public void init() {
		super.init();

		levelNumber = 8;

		Common.addGameObject("borders", new Borders().init());
		Common.addGameObject("pot", new Pot().init());
		Common.getGameObject("pot").getDPO().getPhysicsObject()
				.setWorldPosition(150, 310);
		Common.addGameObject("ball", new BowlingBall().init());

		Common.addGameObject("longwoodbar1", new LongWoodBar().init());
		Common.getGameObject("longwoodbar1").getDPO().getPhysicsObject()
				.getBody().setType(BodyType.STATIC);
		Common.getGameObject("longwoodbar1").getDPO().getPhysicsObject()
				.setAngle(90f * Common.RadDeg);
		Common.getGameObject("longwoodbar1").getDPO().getPhysicsObject()
				.setWorldPosition(120, 250);

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
	}

	@Override
	public String getName() {
		return strName;
	}

}
