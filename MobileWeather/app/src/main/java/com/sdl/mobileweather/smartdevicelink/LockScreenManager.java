package com.sdl.mobileweather.smartdevicelink;

import android.app.Activity;
import android.content.Intent;

/**
 * This class manages the lockscreen for the app.
 */
public class LockScreenManager {
	// variable to contain the current state of the lockscreen
	private static boolean lockScreenUp = false;
	
	public static synchronized void showLockScreen() {
		// only throw up lockscreen if main activity is currently on top
		// else, wait until onResume() to throw lockscreen so it doesn't 
		// pop-up while a user is using another app on the phone
		Activity currentAct = SdlApplication.getCurrentActivity();
		if (currentAct != null && !currentAct.getClass().equals(LockScreenActivity.class)) {
			if (((SdlActivity) SdlApplication.getCurrentActivity()).isActivityonTop() == true) {
				Intent i = new Intent(SdlApplication.getInstance(), LockScreenActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
				SdlApplication.getInstance().startActivity(i);
			}
		}
		lockScreenUp = true;		
	}

	public static synchronized void clearLockScreen() {
		if (LockScreenActivity.getInstance() != null) {  
			LockScreenActivity.getInstance().exit();
		}
		lockScreenUp = false;
	}
	
	public static synchronized boolean getLockScreenStatus() {
		return lockScreenUp;
	}	
}
