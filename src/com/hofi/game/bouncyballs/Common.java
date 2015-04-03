package com.hofi.game.bouncyballs;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

import com.hofi.game.bouncyballs.interfaces.ButtonHandler;
import com.hofi.game.bouncyballs.interfaces.GameObject;

public class Common {
	public static Activity mainActivity;
	public static MyAssetManager assetManager;
	public static MyGLSurfaceView glView;
	public static Input input;
	public static InputManager inputManager;
	public static Textures textures;
	public static World world;
	public static PhysicsObjectFactory physicsObjectFactory;
	public static UIObjects uiObjects;
	public static GameObjects gameObjects;
	public static GamePlayManager gamePlayManager;
	public static MainContactHandler mainContactHandler;
	public static GameState gameState;
	public static AppPreferences appPreferences;

	public static ShaderHandles shaderHandles;

	public static int fps;
	public static float screenWidth;
	public static float screenHeight;
	public static long previousHighScore;
	public static long highScore;
	public static int lastFinishedLevelNumber = 0;

	public static final float Mult = 100.0f;
	public static final Vec2 G = new Vec2(0, -9.81f);
	public static final float RadDeg = (float) (Math.PI / 180.0f);
	public static final int maxKicks = 3;

	public static final String highScoreKey = "bouncyBallsHighScore";
	public static final String lastFinishedlevelKey = "bouncyBallsLastFinishedLevel";
	public static final String lastFinishedlevelScoreKey = "bouncyBallsLastFinishedLevelScore";

	private final static String strUp = "up";
	private final static String strDown = "down";

	private static Vec2 worldPosition = new Vec2();

	public static Vec2 getWorldPosition(float x, float y) {
		int m = Common.mainActivity.findViewById(R.id.glSurface).getTop();
		y -= m;
		float xx = (800f / screenWidth) * x;
		float yy = 480f - (480f / screenHeight) * y;
		worldPosition.set(xx, yy);
		return worldPosition;
	}

	public static <T> T instantiate(final String className, final Class<T> type) {
		try {
			return type.cast(Class.forName(className).newInstance());
		} catch (final InstantiationException e) {
			throw new IllegalStateException(e);
		} catch (final IllegalAccessException e) {
			throw new IllegalStateException(e);
		} catch (final ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
	}

	public static void updatePhysicsWorld() {
		world.step(1.0f / 60.0f, 8, 8);
	}

	public static void clearUIObjects() {
		uiObjects.clear();
	}

	public static UIObject addButton(Object owner, Vec2 position,
			String btnName, ButtonHandler handler) {
		return uiObjects.addObject(new Button(owner, position, btnName + strUp,
				btnName + strDown).setHandler(handler));
	}

	public static void clearGameObjects() {
		gameObjects.clear();
	}

	public static GameObject addGameObject(String objName, GameObject obj) {
		return gameObjects.addObject(objName, obj);
	}

	public static GameObject getGameObject(String objName) {
		return gameObjects.getObject(objName);
	}

	public static void saveHighScore() {
		long hs = appPreferences.getLong(highScoreKey);
		if (gameState.score > hs) {
			appPreferences.putLong(highScoreKey, gameState.score);
			previousHighScore = highScore;
			highScore = gameState.score;
		}
	}

	public static void saveGameState() {
		int lfl = gameState.level;
		if (!gameState.levelCleared)
			lfl--;
		if (lfl < 0)
			lfl = 0;
		lastFinishedLevelNumber = lfl;
		long lastScore = gameState.score;
		lastFinishedLevelNumber = lfl;
		appPreferences.putInt(lastFinishedlevelKey, lfl);
		appPreferences.putLong(lastFinishedlevelScoreKey, lastScore);
	}
	
	public static int getScreenWidth(){
		Display display = Common.mainActivity.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		System.out.println("width: "+width);
		return width;
	}
	
	public static int getScreenHeight(){
		Display display = Common.mainActivity.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int height = size.y;
		System.out.println("height: "+height);
		return height;
	}
}
