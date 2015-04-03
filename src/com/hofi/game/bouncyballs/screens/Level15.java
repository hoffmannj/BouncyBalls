package com.hofi.game.bouncyballs.screens;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Fixture;

import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.DrawablePhysicsObject;
import com.hofi.game.bouncyballs.Sprite;
import com.hofi.game.bouncyballs.gameObjects.BeachBall;
import com.hofi.game.bouncyballs.gameObjects.Borders;
import com.hofi.game.bouncyballs.gameObjects.Cactus;
import com.hofi.game.bouncyballs.gameObjects.Cactus_Sensor;
import com.hofi.game.bouncyballs.gameObjects.Pot;
import com.hofi.game.bouncyballs.gameObjects.Ventilator;
import com.hofi.game.bouncyballs.gameObjects.Ventilator_Sensor2;
import com.hofi.game.bouncyballs.interfaces.ContactHandler;

public class Level15 extends LevelScreen {

	private boolean cactusWasHit = false;
	private boolean inWind = false;
	private Sprite pow;

	private final String strName = "level_15";
	private final String strVentilator = "ventilator";
	private final String strLevel = "level_";

	@Override
	public void init() {
		super.init();

		levelNumber = 15;

		pow = new Sprite("pow");
		pow.setVisible(false);

		Common.addGameObject("borders", new Borders().init());
		Common.addGameObject("pot", new Pot().init());
		Common.getGameObject("pot").getDPO().getPhysicsObject()
				.setWorldPosition(630, 70);
		Common.addGameObject("ball", new BeachBall().init());

		Common.addGameObject("ventilator", new Ventilator().init());
		Common.getGameObject("ventilator").getDPO().getPhysicsObject()
				.setWorldPosition(25, 150);

		for (int i = 0; i < 6; i++) {
			Common.addGameObject("cactus" + i, new Cactus().init());
			Common.getGameObject("cactus" + i).getDPO().getPhysicsObject()
					.setWorldPosition(150 + i * 70, 70);

			Common.addGameObject("cactus_sensor" + i,
					new Cactus_Sensor().init());
			Common.getGameObject("cactus_sensor" + i).getDPO()
					.getPhysicsObject().setWorldPosition(150 + i * 70, 70);
		}

		Common.addGameObject("cactus6", new Cactus().init());
		Common.getGameObject("cactus6").getDPO().getPhysicsObject()
				.setWorldPosition(740, 70);

		Common.addGameObject("cactus_sensor6", new Cactus_Sensor().init());
		Common.getGameObject("cactus_sensor6").getDPO().getPhysicsObject()
				.setWorldPosition(740, 70);

		Common.addGameObject("ventilator_sensor2",
				new Ventilator_Sensor2().init());
		Common.getGameObject("ventilator_sensor2").getDPO().getPhysicsObject()
				.setWorldPosition(410, 160);
		Common.getGameObject("ventilator_sensor2").getDPO().getSprite()
				.setColor(1, 1, 1, 0.2f);

		setContactHandler();
	}

	@Override
	public void draw() {
		super.draw();
		if (levelSplash)
			return;
		pow.draw();
		if (cactusWasHit) {
			pow.setVisible(false);
			cactusWasHit = false;
		}
	}

	@Override
	public void update() {
		super.update();
		Common.getGameObject(strVentilator).update();

		Vec2 wp = Common.getGameObject(ballObjName).getDPO().getPhysicsObject()
				.getWorldPosition();
		pow.setPosition(wp.x, wp.y);

		if (inWind) {
			Vec2 p = Common.getGameObject(ballObjName).getDPO()
					.getPhysicsObject().getPosition();
			Vec2 m1 = Common.getGameObject(strVentilator).getDPO()
					.getPhysicsObject().getPosition();
			Vec2 d1 = p.sub(m1);
			float dx = d1.length();
			float ls = 0.50f / Math.abs(dx * dx);
			float mult1 = ls;
			d1.set(1, 0);
			d1.mulLocal(mult1);
			Common.getGameObject(ballObjName).getDPO().getPhysicsObject()
					.getBody().applyForceToCenter(d1);
		}

		if (cactusWasHit) {
			pow.setVisible(true);
			if (kickCount >= Common.maxKicks) {
				Common.gamePlayManager.forceScreenChange();
				Common.gamePlayManager.setCurrentScreen(strLevel + levelNumber);
			} else if (kickCount < Common.maxKicks) {
				Common.getGameObject(ballObjName).getDPO().getPhysicsObject()
						.setWorldPosition(45, 45);
				Common.getGameObject(ballObjName).getDPO().getPhysicsObject()
						.setAngularVelocity(0);
				Common.getGameObject(ballObjName).getDPO().getPhysicsObject()
						.setLinearVelocity(0, 0);
				Common.getGameObject(ballObjName).getDPO().getPhysicsObject()
						.getBody().m_force.set(0, 0);
			}
		}
	}

	@Override
	public String getName() {
		return strName;
	}

	@Override
	protected void setContactHandler() {
		super.setContactHandler();
		for (int i = 0; i < 7; i++) {
			Common.mainContactHandler.addHandler(ballObjName, "cactus_sensor"
					+ i, new ContactHandler() {

				@Override
				public void handleEnter(DrawablePhysicsObject objectA,
						Fixture fixtureA, DrawablePhysicsObject objectB,
						Fixture fixtureB) {
					cactusWasHit = true;
				}

				@Override
				public void handleLeave(DrawablePhysicsObject objectA,
						Fixture fixtureA, DrawablePhysicsObject objectB,
						Fixture fixtureB) {
					cactusWasHit = true;
				}

			});
		}

		Common.mainContactHandler.addHandler(ballObjName, "ventilator_sensor2",
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
