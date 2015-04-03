package com.hofi.game.bouncyballs;

import org.jbox2d.common.Vec2;

public class GameState {
	public String screenName;
	public int level;
	public boolean levelCleared;
	public int lastLevelKickCount;
	public long score;
	public int livesLeft;
	public Vec2 ballPosition;
	public float ballRotation;
	public Vec2 ballLinearVelocity;
	public float ballAngularVelocity;

	public GameState() {
		screenName = "splashscreen";
		level = 0;
		levelCleared = false;
		lastLevelKickCount = 0;
		score = 0;
		livesLeft = 3;
		ballPosition = new Vec2(45, 45);
		ballRotation = 0;
		ballLinearVelocity = new Vec2();
		ballAngularVelocity = 0;
	}
}
