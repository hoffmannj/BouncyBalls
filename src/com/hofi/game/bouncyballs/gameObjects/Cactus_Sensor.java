package com.hofi.game.bouncyballs.gameObjects;

import com.hofi.game.bouncyballs.DrawablePhysicsObject;
import com.hofi.game.bouncyballs.interfaces.GameObject;

public class Cactus_Sensor implements GameObject {

	private DrawablePhysicsObject obj;

	@Override
	public GameObject init() {
		obj = new DrawablePhysicsObject("cactus_sensor", "empty", true);
		obj.getPhysicsObject().setWorldPosition(300, 150);
		return this;
	}

	@Override
	public GameObject update() {
		return this;
	}

	@Override
	public GameObject draw() {
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
