package com.jamesjaw.supertool.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

public class NetWorkManager {

	public static void changeWifiSwitch(Context context) {

		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		if (wifiManager.isWifiEnabled()) {
			wifiManager.setWifiEnabled(false);
		} else {
			wifiManager.setWifiEnabled(true);
		}
	}

	public static boolean isWifi(Context context){
		
		if(context == null){
			return false;
		}
		
//		ConnectivityManager mConnectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo nInfo = mConnectivity.getActiveNetworkInfo();
//		boolean isConnected = false;
//
//		if (nInfo != null) {
//			
//			int netType = nInfo.getType();
//			
//			if (netType == ConnectivityManager.TYPE_WIFI) {
//				
//				isConnected =  nInfo.isConnected();
//				
//			}
//		}
		WifiManager wifiManager = (WifiManager) context    
                .getSystemService(Context.WIFI_SERVICE);    
        return wifiManager.isWifiEnabled();    
//		return isConnected;
	}
}
