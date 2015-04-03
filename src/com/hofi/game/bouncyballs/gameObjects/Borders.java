package com.hofi.game.bouncyballs.gameObjects;

import com.hofi.game.bouncyballs.DrawablePhysicsObject;
import com.hofi.game.bouncyballs.interfaces.GameObject;

public class Borders implements GameObject {

	private DrawablePhysicsObject borders;

	@Override
	public GameObject init() {
		borders = new DrawablePhysicsObject("borders", "borders", false);
		borders.getPhysicsObject().setWorldPosition(400, 240);
		return this;
	}

	@Override
	public GameObject update() {
		return this;
	}

	@Override
	public GameObject draw() {
		borders.draw();
		return this;
	}

	@Override
	public GameObject cleanUp() {
		return this;
	}

	@Override
	public DrawablePhysicsObject getDPO() {
		return borders;
	}

}
