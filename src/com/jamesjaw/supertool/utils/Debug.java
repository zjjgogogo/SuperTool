package com.jamesjaw.supertool.utils;

import android.util.Log;

public class Debug {

	static final boolean DEBUG = true;
	
	static final String DEAFAULT_TAG = "DEBUG";
	
	public static void show(String message)
	{
		if(message != null)
			Log.e(DEAFAULT_TAG, message);
		else
			Log.e(DEAFAULT_TAG, "Message :  NULL" );
	}
	
	public static void show(String tag, String message)
	{
		
		String final_tag = tag != null ? tag : DEAFAULT_TAG;
		
		if(message != null)
			Log.e(final_tag, message);
		else
			Log.e(final_tag, "Message :  NULL" );
		
		
	}
}
