package com.hofi.game.bouncyballs.screens;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;

import com.hofi.game.bouncyballs.Button;
import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.DrawablePhysicsObject;
import com.hofi.game.bouncyballs.Sprite;
import com.hofi.game.bouncyballs.gameObjects.Borders;
import com.hofi.game.bouncyballs.gameObjects.BowlingBall;
import com.hofi.game.bouncyballs.gameObjects.LongWoodBar;
import com.hofi.game.bouncyballs.gameObjects.Pot;
import com.hofi.game.bouncyballs.gameObjects.Trampoline;
import com.hofi.game.bouncyballs.gameObjects.Trampoline_Sensor;
import com.hofi.game.bouncyballs.interfaces.ButtonHandler;
import com.hofi.game.bouncyballs.interfaces.ContactHandler;
import com.hofi.game.bouncyballs.interfaces.GameObject;

public class Level12 extends LevelScreen {

	private boolean deleteObj = false;

	private final String strName = "level_12";
	private final String strLongwoodbar = "longwoodbar1";

	private Sprite hint;
	private boolean showHint = false;

	@Override
	public void init() {
		super.init();

		levelNumber = 12;

		hint = new Sprite("level12_help");
		hint.setPosition(400, 240);
		hint.setVisible(false);

		Common.addGameObject("borders", new Borders().init());
		Common.addGameObject("pot", new Pot().init());
		Common.addGameObject("ball", new BowlingBall().init());
		Common.getGameObject("ball").getDPO().getPhysicsObject().getBody()
				.setAngularDamping(0.5f);
		Common.getGameObject("ball").getDPO().getPhysicsObject().getBody()
				.setLinearDamping(0.5f);

		Common.addGameObject("longwoodbar1", new LongWoodBar().init());
		Common.getGameObject("longwoodbar1").getDPO().getPhysicsObject()
				.getBody().setType(BodyType.STATIC);
		Common.getGameObject("longwoodbar1").getDPO().getPhysicsObject()
				.setAngle(90f * Common.RadDeg);
		Common.getGameObject("longwoodbar1").getDPO().getPhysicsObject()
				.setWorldPosition(650, 130);

		Common.addGameObject("trampoline", new Trampoline().init());
		Common.addGameObject("trampoline_sensor",
				new Trampoline_Sensor().init());

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
		Common.mainContactHandler.addHandler(ballObjName, "trampoline_sensor",
				new ContactHandler() {

					@Override
					public void handleEnter(DrawablePhysicsObject objectA,
							Fixture fixtureA, DrawablePhysicsObject objectB,
							Fixture fixtureB) {
						System.out.println("trampoline_sensor enter");
					}

					@Override
					public void handleLeave(DrawablePhysicsObject objectA,
							Fixture fixtureA, DrawablePhysicsObject objectB,
							Fixture fixtureB) {
						System.out.println("trampoline_sensor leave");
						deleteObj = true;
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
