package com.hofi.game.bouncyballs.interfaces;

public interface GameScreen extends InputListener {
	String getName();

	void init();

	void initFromGameState();

	void draw();

	void update();

	void updateGameState();

	void cleanUp();
}
