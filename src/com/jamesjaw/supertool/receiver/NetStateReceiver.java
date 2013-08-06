package com.jamesjaw.supertool.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

import com.jamesjaw.supertool.NotificationCenter;
import com.jamesjaw.supertool.R;
import com.jamesjaw.supertool.manager.NetWorkManager;
import com.jamesjaw.supertool.utils.Debug;

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
    	 		else if(WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action))
    	 		{
    	 			
    	 			int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);   
    	 			Debug.show("WIFI状态", "wifiState"+wifiState);   
    	 			NotificationCenter nc = new NotificationCenter(context);
    	 			nc.notification();
    	 		}
    	 	}
    }
}
