package com.hofi.game.bouncyballs.gameObjects;

import com.hofi.game.bouncyballs.DrawablePhysicsObject;
import com.hofi.game.bouncyballs.Sprite;
import com.hofi.game.bouncyballs.interfaces.GameObject;

public class Ventilator implements GameObject {

	private DrawablePhysicsObject obj;
	private Sprite[] sprites = new Sprite[6];
	private int currFrame = 0;

	@Override
	public GameObject init() {
		obj = new DrawablePhysicsObject("ventilator", "empty", false);
		obj.getPhysicsObject().setWorldPosition(500, 200);
		for (int i = 0; i < 6; i++) {
			sprites[i] = new Sprite("ventilator" + i);
		}
		return this;
	}

	public void mirrorOnY() {
		for (int i = 0; i < 6; i++) {
			sprites[i].mirrorOnY();
		}
	}

	@Override
	public GameObject update() {
		currFrame++;
		if (currFrame == 6)
			currFrame = 0;
		return this;
	}

	@Override
	public GameObject draw() {
		sprites[currFrame].setPosition(obj.getPhysicsObject()
				.getWorldPosition());
		sprites[currFrame].draw();
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
