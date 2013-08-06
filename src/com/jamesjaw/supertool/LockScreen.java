package com.jamesjaw.supertool;

import com.jamesjaw.supertool.manager.LockScreenManager;

import android.app.Activity;
import android.os.Bundle;

public class LockScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		LockScreenManager.lockScreen(this);
		
		finish();
		
	}

	
	
}
