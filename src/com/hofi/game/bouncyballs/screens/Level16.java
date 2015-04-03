package com.hofi.game.bouncyballs.screens;

import org.jbox2d.common.Vec2;

import com.hofi.game.bouncyballs.Button;
import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.Sprite;
import com.hofi.game.bouncyballs.gameObjects.BasketBall;
import com.hofi.game.bouncyballs.gameObjects.Borders;
import com.hofi.game.bouncyballs.gameObjects.Pot;
import com.hofi.game.bouncyballs.gameObjects.Stool;
import com.hofi.game.bouncyballs.interfaces.ButtonHandler;

public class Level16 extends LevelScreen {

	private final String strName = "level_16";
	private Sprite hint;
	private boolean showHint = false;

	@Override
	public void init() {
		super.init();

		levelNumber = 16;
		reversedKick = true;

		hint = new Sprite("level16_help");
		hint.setPosition(400, 240);
		hint.setVisible(false);

		Common.addGameObject("borders", new Borders().init());
		Common.addGameObject("pot", new Pot().init());
		Common.addGameObject("stool", new Stool().init());
		Common.addGameObject("ball", new BasketBall().init());
		Common.getGameObject("ball").getDPO().getPhysicsObject()
				.setWorldPosition(130, 140);

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
