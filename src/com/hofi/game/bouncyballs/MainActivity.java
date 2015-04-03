package com.hofi.game.bouncyballs;

import java.io.IOException;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.graphics.drawable.Drawable;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class MainActivity extends Activity {
	private GLSurfaceView mGLView;
	private static final String TEST_DEVICE_ID = "AEEC932B0DFF63CF8B9EEBB4659A6BAF";
	private static final int DIALOG_GET_GOOGLE_PLAY_SERVICES = 1;
	private AdView adView;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Common.mainActivity = this;
		Common.assetManager = new MyAssetManager();

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		final ConfigurationInfo configurationInfo = activityManager
				.getDeviceConfigurationInfo();
		final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

		setContentView(R.layout.main);

		if (!supportsEs2) {
			return;
		}

		adView = (AdView) this.findViewById(R.id.ad);

		try {
			Drawable bmp = null;
			bmp = Drawable.createFromStream(getAssets()
					.open("adbackground.png"), "adbackground.png");
			int sdk = android.os.Build.VERSION.SDK_INT;
			if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
				adView.setBackgroundDrawable(bmp);
			} else {
				adView.setBackground(bmp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		AdRequest adRequest = new AdRequest.Builder().addTestDevice(
				AdRequest.DEVICE_ID_EMULATOR).build();

		adView.setVisibility(AdView.VISIBLE);
		adView.loadAd(adRequest);

		mGLView = (MyGLSurfaceView) this.findViewById(R.id.glSurface);
		Common.glView = (MyGLSurfaceView) mGLView;

		Common.screenWidth = Common.glView.getWidth();
		Common.screenHeight = Common.glView.getHeight();
	}

	@Override
	protected void onPause() {
		if (Common.glView != null) {
			Common.screenWidth = Common.glView.getWidth();
			Common.screenHeight = Common.glView.getHeight();
		}
		super.onPause();
		if (mGLView != null)
			mGLView.onPause();
	}

	private Activity getActivity() {
		return this;
	}

	@Override
	protected void onResume() {
		if (Common.glView != null) {
			Common.screenWidth = Common.glView.getWidth();
			Common.screenHeight = Common.glView.getHeight();
		}
		int checkGooglePlayServices = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getActivity());
		if (checkGooglePlayServices != ConnectionResult.SUCCESS) {
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(
					checkGooglePlayServices, getActivity(),
					DIALOG_GET_GOOGLE_PLAY_SERVICES);
			if (dialog != null)
				dialog.show();
		}
		super.onResume();
		if (mGLView != null)
			mGLView.onResume();
	}
}
