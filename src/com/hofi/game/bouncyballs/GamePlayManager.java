package com.hofi.game.bouncyballs;

import java.util.Hashtable;

import com.hofi.game.bouncyballs.interfaces.GameScreen;
import com.hofi.game.bouncyballs.screens.EmptyScreen;
import com.hofi.game.bouncyballs.screens.GameOver;
import com.hofi.game.bouncyballs.screens.Level1;
import com.hofi.game.bouncyballs.screens.Level10;
import com.hofi.game.bouncyballs.screens.Level11;
import com.hofi.game.bouncyballs.screens.Level12;
import com.hofi.game.bouncyballs.screens.Level13;
import com.hofi.game.bouncyballs.screens.Level14;
import com.hofi.game.bouncyballs.screens.Level15;
import com.hofi.game.bouncyballs.screens.Level16;
import com.hofi.game.bouncyballs.screens.Level2;
import com.hofi.game.bouncyballs.screens.Level3;
import com.hofi.game.bouncyballs.screens.Level4;
import com.hofi.game.bouncyballs.screens.Level5;
import com.hofi.game.bouncyballs.screens.Level6;
import com.hofi.game.bouncyballs.screens.Level7;
import com.hofi.game.bouncyballs.screens.Level8;
import com.hofi.game.bouncyballs.screens.Level9;
import com.hofi.game.bouncyballs.screens.LevelCleared;
import com.hofi.game.bouncyballs.screens.LevelHowTo;
import com.hofi.game.bouncyballs.screens.MainMenu;
import com.hofi.game.bouncyballs.screens.NotGoodEnough;
import com.hofi.game.bouncyballs.screens.SplashScreen;

public class GamePlayManager {
	@SuppressWarnings("rawtypes")
	private Hashtable<String, Class> screens;
	private GameScreen current = null;
	private GameScreen empty;
	private String nextScreen = "", currentScreen = "";
	public int levelsCount = 16;
	private Object lockObj = new Object();

	private final String strLevel = "level_";
	private final String strGameOver = "gameover";

	@SuppressWarnings("rawtypes")
	public GamePlayManager() {
		// initialize screens list with GameScreen classes
		empty = new EmptyScreen();
		screens = new Hashtable<String, Class>();
		screens.put("empty", EmptyScreen.class);
		screens.put("splashscreen", SplashScreen.class);
		screens.put("mainmenu", MainMenu.class);
		screens.put("gameover", GameOver.class);
		screens.put("levelcleared", LevelCleared.class);
		screens.put("notgoodenough", NotGoodEnough.class);
		screens.put("howto", LevelHowTo.class);
		screens.put("level_1", Level1.class);
		screens.put("level_2", Level2.class);
		screens.put("level_3", Level3.class);
		screens.put("level_4", Level4.class);
		screens.put("level_5", Level5.class);
		screens.put("level_6", Level6.class);
		screens.put("level_7", Level7.class);
		screens.put("level_8", Level8.class);
		screens.put("level_9", Level9.class);
		screens.put("level_10", Level10.class);
		screens.put("level_11", Level11.class);
		screens.put("level_12", Level12.class);
		screens.put("level_13", Level13.class);
		screens.put("level_14", Level14.class);
		screens.put("level_15", Level15.class);
		screens.put("level_16", Level16.class);
	}

	private void initCurrent() {
		setCurrentScreen("splashscreen");
	}

	public void iniFromGameState() {
		setCurrentScreen(Common.gameState.screenName);
	}

	public GameScreen getCurrent() {
		synchronized (lockObj) {
			if (current == null)
				initCurrent();
			return current;
		}
	}

	private void cleanUpCurrent() {
		if (current == null)
			return;
		Common.mainContactHandler.clearHandlers();
		current.cleanUp();
		Common.clearGameObjects();
		Common.clearUIObjects();
		Common.textures.clearTextures();
	}

	public void setCurrentScreen(String screenName) {
		synchronized (lockObj) {
			nextScreen = screenName;
		}
	}

	public void forceScreenChange() {
		synchronized (lockObj) {
			currentScreen = "";
		}
	}

	public void update(boolean fromState) {
		synchronized (lockObj) {
			if (nextScreen.equalsIgnoreCase(currentScreen))
				return;
			cleanUpCurrent();
			if (!screens.containsKey(nextScreen)) {
				if (nextScreen.startsWith(strLevel)) {
					nextScreen = strGameOver;
				} else {
					current = empty;
					return;
				}
			}

			try {
				currentScreen = nextScreen;
				Common.clearGameObjects();
				Common.clearUIObjects();
				Common.textures.clearTextures();
				Common.mainContactHandler.clearHandlers();
				GameScreen gs = (GameScreen) screens.get(currentScreen)
						.newInstance();
				gs.init();
				if (fromState)
					gs.initFromGameState();
				gs.updateGameState();
				current = gs;
				return;
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			current = empty;
			return;
		}
	}
}
