package com.hofi.game.bouncyballs.gameObjects;

import com.hofi.game.bouncyballs.DrawablePhysicsObject;
import com.hofi.game.bouncyballs.Sprite;
import com.hofi.game.bouncyballs.interfaces.GameObject;

public class Portal implements GameObject {

	private DrawablePhysicsObject obj;
	private Sprite[] sprites = new Sprite[4];
	private float[] delta = { 1, 2, 4, 0.33f };
	private float[] angle = { 0, 0, 0, 0 };

	@Override
	public GameObject init() {
		obj = new DrawablePhysicsObject("portal_sensor", "empty", true);
		obj.getPhysicsObject().setWorldPosition(200, 200);
		for (int i = 0; i < 4; i++) {
			sprites[i] = new Sprite("portal" + (i + 1));
			sprites[i].setColor(1, 1, 1, 0.5f);
		}
		return this;
	}

	@Override
	public GameObject update() {
		for (int i = 0; i < 4; i++) {
			sprites[i].setRotation(angle[i]);
			angle[i] += delta[i];
			if (angle[i] > 360)
				angle[i] -= 360;
		}
		return this;
	}

	@Override
	public GameObject draw() {
		for (int i = 0; i < 4; i++) {
			sprites[i].setPosition(obj.getPhysicsObject().getWorldPosition());
			sprites[i].draw();
		}
		return this;
	}

	@Override
	public GameObject cleanUp() {
		return this;
	}

	@Override
	public DrawablePhysicsObject getDPO() {
		return obj;
	}

}
