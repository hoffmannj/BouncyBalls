package com.hofi.game.bouncyballs.screens;

import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;

import android.os.SystemClock;

import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.DrawablePhysicsObject;
import com.hofi.game.bouncyballs.gameObjects.Basket;
import com.hofi.game.bouncyballs.gameObjects.BasketBall;
import com.hofi.game.bouncyballs.gameObjects.Basket_Sensor1;
import com.hofi.game.bouncyballs.gameObjects.Basket_Sensor2;
import com.hofi.game.bouncyballs.gameObjects.Borders;
import com.hofi.game.bouncyballs.gameObjects.LongWoodBar;
import com.hofi.game.bouncyballs.gameObjects.Pot;
import com.hofi.game.bouncyballs.interfaces.ContactHandler;
import com.hofi.game.bouncyballs.interfaces.GameObject;

public class Level11 extends LevelScreen {

	private long lasts1enter = 0;
	private long lasts2enter = 0;
	private boolean deleteObj = false;
	private float dx = -1.5f;
	private float bx = 650;

	private final String strName = "level_11";
	private final String strLongwoodbar = "longwoodbar1";
	private final String strBasket = "basket";
	private final String strBasketSensor1 = "basket_sensor1";
	private final String strBasketSensor2 = "basket_sensor2";

	@Override
	public void init() {
		super.init();

		levelNumber = 11;

		Common.addGameObject("borders", new Borders().init());
		Common.addGameObject("pot", new Pot().init());
		Common.addGameObject("basket", new Basket().init());
		Common.getGameObject("basket").getDPO().getPhysicsObject()
				.setWorldPosition(650, 250);
		Common.addGameObject("basket_sensor1", new Basket_Sensor1().init());
		Common.getGameObject("basket_sensor1").getDPO().getPhysicsObject()
				.setWorldPosition(650, 250);
		Common.addGameObject("basket_sensor2", new Basket_Sensor2().init());
		Common.getGameObject("basket_sensor2").getDPO().getPhysicsObject()
				.setWorldPosition(650, 250);
		Common.addGameObject("ball", new BasketBall().init());

		Common.addGameObject("longwoodbar1", new LongWoodBar().init());
		Common.getGameObject("longwoodbar1").getDPO().getPhysicsObject()
				.getBody().setType(BodyType.STATIC);
		Common.getGameObject("longwoodbar1").getDPO().getPhysicsObject()
				.setAngle(90f * Common.RadDeg);
		Common.getGameObject("longwoodbar1").getDPO().getPhysicsObject()
				.setWorldPosition(650, 130);

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
		if (deleteObj) {
			GameObject obj = Common.getGameObject(strLongwoodbar);
			if (obj != null) {
				Common.world.destroyBody(obj.getDPO().getPhysicsObject()
						.getBody());
				obj.getDPO().getSprite().setVisible(false);
				Common.gameObjects.removeObject(strLongwoodbar);
			}
			deleteObj = false;
		}

		Common.getGameObject(strBasket).getDPO().getPhysicsObject()
				.setWorldPosition(bx, 250);
		Common.getGameObject(strBasketSensor1).getDPO().getPhysicsObject()
				.setWorldPosition(bx, 250);
		Common.getGameObject(strBasketSensor2).getDPO().getPhysicsObject()
				.setWorldPosition(bx, 250);

		bx += dx;
		if (bx > 740) {
			dx *= -1;
			bx += dx;
		} else if (bx < 560) {
			dx *= -1;
			bx += dx;
		}
	}

	@Override
	public String getName() {
		return strName;
	}

	@Override
	protected void setContactHandler() {
		super.setContactHandler();
		Common.mainContactHandler.addHandler(ballObjName, "basket_sensor1",
				new ContactHandler() {

					@Override
					public void handleEnter(DrawablePhysicsObject objectA,
							Fixture fixtureA, DrawablePhysicsObject objectB,
							Fixture fixtureB) {
						lasts1enter = SystemClock.uptimeMillis();
					}

					@Override
					public void handleLeave(DrawablePhysicsObject objectA,
							Fixture fixtureA, DrawablePhysicsObject objectB,
							Fixture fixtureB) {
					}

				});
		Common.mainContactHandler.addHandler(ballObjName, "basket_sensor2",
				new ContactHandler() {

					@Override
					public void handleEnter(DrawablePhysicsObject objectA,
							Fixture fixtureA, DrawablePhysicsObject objectB,
							Fixture fixtureB) {
						lasts2enter = SystemClock.uptimeMillis();
						long d = lasts2enter - lasts1enter;
						if (d > 0 && d < 700) {
							deleteObj = true;
						}
					}

					@Override
					public void handleLeave(DrawablePhysicsObject objectA,
							Fixture fixtureA, DrawablePhysicsObject objectB,
							Fixture fixtureB) {
					}

				});
	}
}
