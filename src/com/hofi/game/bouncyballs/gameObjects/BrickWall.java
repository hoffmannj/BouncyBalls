package com.hofi.game.bouncyballs.gameObjects;

import com.hofi.game.bouncyballs.DrawablePhysicsObject;
import com.hofi.game.bouncyballs.interfaces.GameObject;

public class BrickWall implements GameObject {

	private DrawablePhysicsObject obj;
	public float delta = 200;
	public float startY = -140;
	private float deltaY = 0;
	private float step = -4;

	@Override
	public GameObject init() {
		obj = new DrawablePhysicsObject("brickwall", "brickwall", false);
		obj.getPhysicsObject().setWorldPosition(500, startY);
		return this;
	}

	@Override
	public GameObject update() {
		deltaY += step;
		obj.getPhysicsObject().setWorldPosition(
				obj.getPhysicsObject().getWorldPosition().x, startY + deltaY);
		if (Math.abs(deltaY) > delta) {
			deltaY -= step;
			step *= -1.0f;
		}
		return this;
	}

	@Override
	public GameObject draw() {
		obj.draw();
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
