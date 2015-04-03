package com.hofi.game.bouncyballs.screens;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Fixture;

import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.DrawablePhysicsObject;
import com.hofi.game.bouncyballs.gameObjects.BeachBall;
import com.hofi.game.bouncyballs.gameObjects.Borders;
import com.hofi.game.bouncyballs.gameObjects.Pot;
import com.hofi.game.bouncyballs.gameObjects.Ventilator;
import com.hofi.game.bouncyballs.gameObjects.Ventilator_Sensor;
import com.hofi.game.bouncyballs.interfaces.ContactHandler;

public class Level14 extends LevelScreen {

	private boolean inWind = false;

	private final String strName = "level_14";
	private final String strVentilator = "ventilator";

	@Override
	public void init() {
		super.init();

		levelNumber = 14;

		Common.addGameObject("borders", new Borders().init());
		Common.addGameObject("pot", new Pot().init());
		Common.addGameObject("ball", new BeachBall().init());

		Common.addGameObject("ventilator", new Ventilator().init());
		Common.getGameObject("ventilator").getDPO().getPhysicsObject()
				.setWorldPosition(750, 150);
		((Ventilator) Common.getGameObject("ventilator")).mirrorOnY();

		Common.addGameObject("ventilator_sensor",
				new Ventilator_Sensor().init());
		Common.getGameObject("ventilator_sensor").getDPO().getPhysicsObject()
				.setWorldPosition(350, 160);
		Common.getGameObject("ventilator_sensor").getDPO().getSprite()
				.setColor(1, 1, 1, 0.2f);

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
		Common.getGameObject(strVentilator).update();

		if (inWind) {
			Vec2 p = Common.getGameObject(ballObjName).getDPO()
					.getPhysicsObject().getPosition();
			Vec2 m1 = Common.getGameObject(strVentilator).getDPO()
					.getPhysicsObject().getPosition();
			Vec2 d1 = p.sub(m1);
			float dx = d1.length();
			float ls = 0.50f / Math.abs(dx * dx);
			float mult1 = ls;
			d1.set(-1, 0);
			d1.mulLocal(mult1);
			Common.getGameObject(ballObjName).getDPO().getPhysicsObject()
					.getBody().applyForceToCenter(d1);
		}
	}

	@Override
	public String getName() {
		return strName;
	}

	@Override
	protected void setContactHandler() {
		super.setContactHandler();
		Common.mainContactHandler.addHandler(ballObjName, "ventilator_sensor",
				new ContactHandler() {

					@Override
					public void handleEnter(DrawablePhysicsObject objectA,
							Fixture fixtureA, DrawablePhysicsObject objectB,
							Fixture fixtureB) {
						inWind = true;
					}

					@Override
					public void handleLeave(DrawablePhysicsObject objectA,
							Fixture fixtureA, DrawablePhysicsObject objectB,
							Fixture fixtureB) {
						inWind = false;
					}

				});
	}
}
