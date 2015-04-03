package com.hofi.game.bouncyballs;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyGLSurfaceView extends GLSurfaceView {

	public MyGLSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAll();
	}

	private Vec2 vec = new Vec2();

	public MyGLSurfaceView(Context context) {
		super(context);
		initAll();
	}

	public void initAll() {
		Common.appPreferences = new AppPreferences();
		Common.appPreferences.getPreferences();
		Common.highScore = Common.appPreferences.getLong(Common.highScoreKey);
		Common.previousHighScore = Common.highScore;
		Common.lastFinishedLevelNumber = Common.appPreferences
				.getInt(Common.lastFinishedlevelKey);
		Common.input = new Input();
		Common.shaderHandles = new ShaderHandles();
		Common.textures = new Textures();
		Common.world = new World(Common.G);
		Common.physicsObjectFactory = new PhysicsObjectFactory();
		Common.uiObjects = new UIObjects();
		Common.gameObjects = new GameObjects();
		Common.gamePlayManager = new GamePlayManager();
		Common.inputManager = new InputManager();
		Common.mainContactHandler = new MainContactHandler();
		Common.gameState = new GameState();

		Common.input.ClearListeners();
		Common.input.AddListener(Common.inputManager);
		Common.world.setContactListener(Common.mainContactHandler);
		setEGLContextClientVersion(2);
		setRenderer(new GLRenderer());
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		if (Common.input == null)
			return false;
		if (Common.glView == null)
			return false;
		Common.screenWidth = Common.glView.getWidth();
		Common.screenHeight = Common.glView.getHeight();
		vec.set(Common.getWorldPosition(e.getX(), e.getY()));

		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Common.input.setTouchStart(vec);
			break;
		case MotionEvent.ACTION_MOVE:
			Common.input.setTouchMove(vec);
			break;
		case MotionEvent.ACTION_UP:
			Common.input.setTouchEnd(vec);
			break;
		}

		return true;
	}

	@Override
	public void onPause() {
		if (Common.gamePlayManager != null
				&& Common.gamePlayManager.getCurrent() != null) {
			Common.gamePlayManager.getCurrent().updateGameState();
		}
	}

	@Override
	public void onResume() {
	}

}
