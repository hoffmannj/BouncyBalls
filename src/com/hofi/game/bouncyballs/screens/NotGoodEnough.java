package com.hofi.game.bouncyballs.screens;

import org.jbox2d.common.Vec2;

import com.hofi.game.bouncyballs.Button;
import com.hofi.game.bouncyballs.CharMap;
import com.hofi.game.bouncyballs.Common;
import com.hofi.game.bouncyballs.interfaces.ButtonHandler;
import com.hofi.game.bouncyballs.interfaces.GameScreen;

public class NotGoodEnough implements GameScreen {

	protected CharMap charMap;

	private final String strName = "NotGoodEnough";
	private final String str1 = "Not bad, but not good enough.";
	private final String str2 = "You should try again.";

	@Override
	public void TouchStart() {

	}

	@Override
	public void TouchEnd() {

	}

	@Override
	public void TouchMove() {

	}

	@Override
	public String getName() {
		return strName;
	}

	@Override
	public void init() {
		charMap = new CharMap();

		Common.addButton(this, new Vec2(400, 150), "btnrestart",
				new ButtonHandler() {
					@Override
					public void handle(Button sender, Object owner) {
						Common.gamePlayManager.forceScreenChange();
						Common.gamePlayManager.setCurrentScreen("level_"
								+ Common.gameState.level);
					}
				});

		Common.addButton(this, new Vec2(400, 70), "btnquit",
				new ButtonHandler() {
					@Override
					public void handle(Button sender, Object owner) {
						Common.gamePlayManager.setCurrentScreen("mainmenu");
					}
				});
	}

	@Override
	public void initFromGameState() {

	}

	@Override
	public void draw() {
		Common.uiObjects.draw();
		charMap.drawString(str1, 400 - charMap.measureString(str1) / 2, 300);
		charMap.drawString(str2, 400 - charMap.measureString(str2) / 2, 260);
	}

	@Override
	public void update() {

	}

	@Override
	public void updateGameState() {

	}

	@Override
	public void cleanUp() {

	}

}
