package com.hofi.game.bouncyballs.gameObjects;

import com.hofi.game.bouncyballs.DrawablePhysicsObject;
import com.hofi.game.bouncyballs.interfaces.GameObject;

public class Propeller implements GameObject {

	private DrawablePhysicsObject obj;

	@Override
	public GameObject init() {
		obj = new DrawablePhysicsObject("propeller", "propeller", false);
		obj.getPhysicsObject().setWorldPosition(650, 230);
		return this;
	}

	@Override
	public GameObject update() {
		obj.getPhysicsObject().setAngle(
				obj.getPhysicsObject().getAngle() + 0.05f);
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
