package com.hofi.game.bouncyballs.interfaces;

import com.hofi.game.bouncyballs.DrawablePhysicsObject;

public interface GameObject {
	GameObject init();

	GameObject update();

	GameObject draw();

	GameObject cleanUp();

	DrawablePhysicsObject getDPO();
}
