package com.hofi.game.bouncyballs.gameObjects;

import com.hofi.game.bouncyballs.DrawablePhysicsObject;
import com.hofi.game.bouncyballs.interfaces.GameObject;

public class BowlingBall implements GameObject {

	private DrawablePhysicsObject ball;

	@Override
	public GameObject init() {
		ball = new DrawablePhysicsObject("bowlingball", "bowlingball", false);
		ball.getPhysicsObject().setWorldPosition(45, 45);
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
