package com.hofi.game.bouncyballs.gameObjects;

import com.hofi.game.bouncyballs.DrawablePhysicsObject;
import com.hofi.game.bouncyballs.interfaces.GameObject;

public class LongWoodBar implements GameObject {
	private DrawablePhysicsObject obj;

	@Override
	public GameObject init() {
		obj = new DrawablePhysicsObject("longwoodbar", "longwoodbar", false);
		obj.getPhysicsObject().setWorldPosition(300, 300);
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
