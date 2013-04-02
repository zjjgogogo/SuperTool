package com.jamesjaw.supertool;

import com.jamesjaw.supertool.file.PreferencesSave;
import com.jamesjaw.supertool.receiver.LockScreenReceiver;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class LockScreenManager {

	protected static ComponentName componentName;
	
	public static void cancelAuth(Context context)
	{
		checkComponent(context);
		DevicePolicyManager policyManager = (DevicePolicyManager)context.getSystemService(
				Context.DEVICE_POLICY_SERVICE);
		if (policyManager.isAdminActive(componentName)) {// 判断是否有权限(激活了设备管理器)
			policyManager.removeActiveAdmin(componentName); 
			PreferencesSave.saveBoolean(context, context.getString(R.string.lock_screen_preferences_tag), false);
		}
	}
	
	public static void getAuth(Context context)
	{
		checkComponent(context);
		
		DevicePolicyManager policyManager = (DevicePolicyManager)context.getSystemService(
				Context.DEVICE_POLICY_SERVICE);
		
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
				"lock_screen");
		context.startActivity(intent);
	}
	
	public static void lockScreen(Context context)
	{
		checkComponent(context);
		DevicePolicyManager policyManager = (DevicePolicyManager)context.getSystemService(
				Context.DEVICE_POLICY_SERVICE);
		
		if (policyManager.isAdminActive(componentName)) {// 判断是否有权限(激活了设备管理器)
			policyManager.lockNow();
		}
	}
	
	protected static void checkComponent(Context context)
	{
		if(componentName == null)
		{
			componentName = new ComponentName(context,
					LockScreenReceiver.class);
		}
	}
}
