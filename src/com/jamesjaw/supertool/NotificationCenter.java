package com.jamesjaw.supertool;

import com.jamesjaw.supertool.file.PreferencesSave;
import com.jamesjaw.supertool.manager.NetWorkManager;
import com.jamesjaw.supertool.utils.Debug;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class NotificationCenter {

	Context context;

	NotificationManager mNotificationManager;

	public NotificationCenter(Context context) {
		this.context = context;
	}

	public void notification() {
		mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification.Builder mBuilder = new Notification.Builder(context);

		mBuilder.setOngoing(true);

		mBuilder.setWhen(System.currentTimeMillis());

		mBuilder.setSmallIcon(R.drawable.ic_launcher);
		mBuilder.setContentTitle("SuperTools");
		mBuilder.setContentText("Click to MainPage");

		Intent intent = new Intent(context, SuperToolsActivity.class);

		mBuilder.setContentIntent(PendingIntent.getActivity(context, 1, intent,
				Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY));

		if (PreferencesSave.getBoolean(context,
				context.getString(R.string.lock_screen_preferences_tag), false)) {
			mBuilder.addAction(R.drawable.lock_icon, "lock", PendingIntent
					.getBroadcast(
							context,
							0,
							new Intent(context
									.getString(R.string.lock_screen_action)),
							PendingIntent.FLAG_CANCEL_CURRENT));
		}
		if (PreferencesSave.getBoolean(context,
				context.getString(R.string.network_preferences_tag), false)) {
			Debug.show("isWifi: " + NetWorkManager.isWifi(context));
			mBuilder.addAction(
					(NetWorkManager.isWifi(context) ? R.drawable.wifi_yes
							: R.drawable.wifi_no), "wifi",
					PendingIntent.getBroadcast(
							context,
							1,
							new Intent(context
									.getString(R.string.wifi_change_action)),
							PendingIntent.FLAG_CANCEL_CURRENT));
		}

		Notification mNotification = mBuilder.build();

		mNotificationManager.notify(123456, mNotification);
	}

}
