package com.hofi.game.bouncyballs.gameObjects;

import com.hofi.game.bouncyballs.DrawablePhysicsObject;
import com.hofi.game.bouncyballs.interfaces.GameObject;

public class Pot implements GameObject {

	private DrawablePhysicsObject pot;

	@Override
	public GameObject init() {
		pot = new DrawablePhysicsObject("pot", "pot", false);
		pot.getPhysicsObject().setWorldPosition(650, 70);
		pot.loadSensor();
		return this;
	}

	@Override
	public GameObject update() {
		return this;
	}

	@Override
	public GameObject draw() {
		pot.draw();
		return this;
	}

	@Override
	public GameObject cleanUp() {
		return this;
	}

	@Override
	public DrawablePhysicsObject getDPO() {
		return pot;
	}

}
