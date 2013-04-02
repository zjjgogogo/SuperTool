package com.jamesjaw.supertool.receiver;

import com.jamesjaw.supertool.LockScreenManager;
import com.jamesjaw.supertool.NetWorkManager;
import com.jamesjaw.supertool.R;
import com.jamesjaw.supertool.utils.Debug;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetStateReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
	 
		
		catchActionAndDoSomething(context, intent);
	}

	
	protected void catchActionAndDoSomething(Context context,Intent intent)
    {
    	 	if(intent != null)
    	 	{
    	 		String action = intent.getAction();
    	 		Debug.show("NetStateReceiver : "  + action);
    	 		if(action != null && action.equals(context.getString(R.string.wifi_change_action)))
    	 		{
    	 			NetWorkManager.changeWifiSwitch(context);
    	 		}
    	 	}
    }
}
