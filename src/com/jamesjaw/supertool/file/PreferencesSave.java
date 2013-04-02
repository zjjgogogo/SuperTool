package com.jamesjaw.supertool.file;

import com.jamesjaw.supertool.R;

import android.R.bool;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesSave {

	
	public static boolean getBoolean(Context context,String tag,boolean defaultValue)
	{
		SharedPreferences mPreferences = getSharedPreferences(context);
		return mPreferences.getBoolean(tag, defaultValue);
	}
	
	public static boolean saveBoolean(Context context, String tag,boolean value)
	{
		SharedPreferences mPreferences = getSharedPreferences(context);
		Editor mEditor = mPreferences.edit();
		mEditor.putBoolean(tag, value);
		return mEditor.commit();
	}
	
	
	protected static SharedPreferences getSharedPreferences(Context context)
	{
		return context.getSharedPreferences(context.getString(R.string.super_tool_preferences_file), 0); 
	}
	
}
