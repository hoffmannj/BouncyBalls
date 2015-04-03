package com.hofi.game.bouncyballs.screens;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;

import android.os.SystemClock;

import com.hofi.game.bouncyballs.Button;
import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.DrawablePhysicsObject;
import com.hofi.game.bouncyballs.Sprite;
import com.hofi.game.bouncyballs.gameObjects.Basket;
import com.hofi.game.bouncyballs.gameObjects.BasketBall;
import com.hofi.game.bouncyballs.gameObjects.Basket_Sensor1;
import com.hofi.game.bouncyballs.gameObjects.Basket_Sensor2;
import com.hofi.game.bouncyballs.gameObjects.Borders;
import com.hofi.game.bouncyballs.gameObjects.LongWoodBar;
import com.hofi.game.bouncyballs.gameObjects.Pot;
import com.hofi.game.bouncyballs.interfaces.ButtonHandler;
import com.hofi.game.bouncyballs.interfaces.ContactHandler;
import com.hofi.game.bouncyballs.interfaces.GameObject;

public class Level10 extends LevelScreen {

	private long lasts1enter = 0;
	private long lasts2enter = 0;
	private boolean deleteObj = false;

	private final String strName = "level_10";
	private final String strLongwoodbar = "longwoodbar1";

	private Sprite hint;
	private boolean showHint = false;

	@Override
	public void init() {
		super.init();

		levelNumber = 10;

		hint = new Sprite("level10_help");
		hint.setPosition(400, 240);
		hint.setVisible(false);

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

		Common.addButton(this, new Vec2(600, 430), "btnhowto",
				new ButtonHandler() {
					@Override
					public void handle(Button sender, Object owner) {
						showHint = true;
					}
				});

		setContactHandler();
	}

	@Override
	public void draw() {
		if (showHint) {
			hint.setVisible(true);
			hint.draw();
			hint.setVisible(false);
			return;
		}
		super.draw();
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
						System.out.println("basket_sensor1 enter");
						lasts1enter = SystemClock.uptimeMillis();
					}

					@Override
					public void handleLeave(DrawablePhysicsObject objectA,
							Fixture fixtureA, DrawablePhysicsObject objectB,
							Fixture fixtureB) {
						System.out.println("basket_sensor1 leave");
						lasts1enter = SystemClock.uptimeMillis();
					}

				});
		Common.mainContactHandler.addHandler(ballObjName, "basket_sensor2",
				new ContactHandler() {

					@Override
					public void handleEnter(DrawablePhysicsObject objectA,
							Fixture fixtureA, DrawablePhysicsObject objectB,
							Fixture fixtureB) {
						System.out.println("basket_sensor2 enter");
						lasts2enter = SystemClock.uptimeMillis();
						long d = lasts2enter - lasts1enter;
						if (d > 0 && d <= 700) {
							deleteObj = true;
						}
					}

					@Override
					public void handleLeave(DrawablePhysicsObject objectA,
							Fixture fixtureA, DrawablePhysicsObject objectB,
							Fixture fixtureB) {
						System.out.println("basket_sensor2 leave");
					}

				});
	}

	@Override
	public void TouchEnd() {
		if (showHint) {
			showHint = false;
			return;
		}
		super.TouchEnd();
	}

}
