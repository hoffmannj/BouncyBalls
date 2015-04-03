package com.hofi.game.bouncyballs.screens;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import com.hofi.game.bouncyballs.Button;
import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.Sprite;
import com.hofi.game.bouncyballs.gameObjects.Borders;
import com.hofi.game.bouncyballs.gameObjects.BowlingBall;
import com.hofi.game.bouncyballs.gameObjects.CannonBall;
import com.hofi.game.bouncyballs.gameObjects.LongWoodBar;
import com.hofi.game.bouncyballs.gameObjects.Pot;
import com.hofi.game.bouncyballs.gameObjects.WoodBar;
import com.hofi.game.bouncyballs.interfaces.ButtonHandler;

public class Level6 extends LevelScreen {

	private final String strName = "level_6";
	private Sprite hint;
	private boolean showHint = false;

	@Override
	public void init() {
		super.init();

		levelNumber = 6;

		hint = new Sprite("level6_help");
		hint.setPosition(400, 240);
		hint.setVisible(false);

		Common.addGameObject("borders", new Borders().init());
		Common.addGameObject("pot", new Pot().init());
		Common.addGameObject("ball", new BowlingBall().init());

		Common.addGameObject("cannonball", new CannonBall().init());
		Common.getGameObject("cannonball").getDPO().getPhysicsObject()
				.setWorldPosition(660, 350);

		Common.addGameObject("woodbar1", new WoodBar().init());
		Common.getGameObject("woodbar1").getDPO().getPhysicsObject()
				.setAngle(90f * Common.RadDeg);
		Common.getGameObject("woodbar1").getDPO().getPhysicsObject()
				.setWorldPosition(690, 300);

		Common.addGameObject("longwoodbar1", new LongWoodBar().init());
		Common.getGameObject("longwoodbar1").getDPO().getPhysicsObject()
				.getBody().setType(BodyType.DYNAMIC);
		Common.getGameObject("longwoodbar1").getDPO().getPhysicsObject()
				.setAngle(90f * Common.RadDeg);
		Common.getGameObject("longwoodbar1").getDPO().getPhysicsObject()
				.setWorldPosition(670, 130);

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
	}

	@Override
	public String getName() {
		return strName;
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
