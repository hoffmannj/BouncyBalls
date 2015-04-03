package com.hofi.game.bouncyballs.screens;

import org.jbox2d.common.Vec2;

import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.gameObjects.Borders;
import com.hofi.game.bouncyballs.gameObjects.CannonBall;
import com.hofi.game.bouncyballs.gameObjects.Magnet;
import com.hofi.game.bouncyballs.gameObjects.Pot;

public class Level9 extends LevelScreen {

	private final String strName = "level_9";
	private final String strMagnet1 = "magnet1";
	private final String strMagnet2 = "magnet2";

	@Override
	public void init() {
		super.init();

		levelNumber = 9;

		Common.addGameObject("borders", new Borders().init());
		Common.addGameObject("pot", new Pot().init());
		Common.getGameObject("pot").getDPO().getPhysicsObject()
				.setWorldPosition(600, 70);
		Common.addGameObject("magnet1", new Magnet().init());
		Common.getGameObject("magnet1").getDPO().getPhysicsObject()
				.setWorldPosition(485, 70);
		Common.addGameObject("magnet2", new Magnet().init());
		Common.getGameObject("magnet2").getDPO().getPhysicsObject()
				.setWorldPosition(715, 70);
		Common.addGameObject("ball", new CannonBall().init());
		Common.getGameObject("ball").getDPO().getPhysicsObject()
				.setWorldPosition(45, 45);

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
		Vec2 p = Common.getGameObject(ballObjName).getDPO().getPhysicsObject()
				.getPosition();
		Vec2 m1 = Common.getGameObject(strMagnet1).getDPO().getPhysicsObject()
				.getPosition();
		Vec2 m2 = Common.getGameObject(strMagnet2).getDPO().getPhysicsObject()
				.getPosition();
		Vec2 d1 = p.sub(m1);
		float ls = 100.0f / d1.lengthSquared();
		float mult1 = ls;
		d1.normalize();
		d1.mulLocal(mult1);
		Common.getGameObject(ballObjName).getDPO().getPhysicsObject().getBody()
				.applyForceToCenter(d1);

		d1 = p.sub(m2);
		ls = 100.0f / d1.lengthSquared();
		mult1 = ls;
		d1.normalize();
		d1.mulLocal(mult1);
		Common.getGameObject(ballObjName).getDPO().getPhysicsObject().getBody()
				.applyForceToCenter(d1);
	}

	@Override
	public String getName() {
		return strName;
	}

}
