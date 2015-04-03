package com.hofi.game.bouncyballs.gameObjects;

import com.hofi.game.bouncyballs.DrawablePhysicsObject;
import com.hofi.game.bouncyballs.interfaces.GameObject;

public class WoodBar implements GameObject {

	private DrawablePhysicsObject obj;

	@Override
	public GameObject init() {
		obj = new DrawablePhysicsObject("woodbar", "woodbar", false);
		obj.getPhysicsObject().setWorldPosition(45, 45);
		return this;
	}

	@Override
	public GameObject update() {
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
