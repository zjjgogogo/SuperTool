package com.jamesjaw.supertool.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jamesjaw.supertool.utils.Debug;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
 
		if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
 
			Debug.show("收到短信");
		}
		
	}
	
}
