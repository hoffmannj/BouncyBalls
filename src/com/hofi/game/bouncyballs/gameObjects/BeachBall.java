package com.hofi.game.bouncyballs.gameObjects;

import com.hofi.game.bouncyballs.DrawablePhysicsObject;
import com.hofi.game.bouncyballs.interfaces.GameObject;

public class BeachBall implements GameObject {

	private DrawablePhysicsObject ball;

	@Override
	public GameObject init() {
		ball = new DrawablePhysicsObject("beachball", "beachball", false);
		ball.getPhysicsObject().setWorldPosition(50, 50);
		ball.getPhysicsObject().setLinearDamping(1f);
		return this;
	}

	@Override
	public GameObject update() {
		return this;
	}

	@Override
	public GameObject draw() {
		ball.draw();
		return this;
	}

	@Override
	public GameObject cleanUp() {
		return this;
	}

	@Override
	public DrawablePhysicsObject getDPO() {
		return ball;
	}

}
