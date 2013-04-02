package com.jamesjaw.supertool;

import android.content.Context;
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

}
