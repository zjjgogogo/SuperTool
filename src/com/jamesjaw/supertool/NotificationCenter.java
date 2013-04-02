package com.jamesjaw.supertool;

import com.jamesjaw.supertool.file.PreferencesSave;

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
		mBuilder.setContentTitle("Event tracker");
		mBuilder.setContentText("Events received");

		if (PreferencesSave.getBoolean(context,
				context.getString(R.string.lock_screen_preferences_tag), false))
			mBuilder.addAction(R.drawable.ic_launcher, "Lock", PendingIntent
					.getBroadcast(
							context,
							0,
							new Intent(context
									.getString(R.string.lock_screen_action)),
							PendingIntent.FLAG_CANCEL_CURRENT));
		
		if (PreferencesSave.getBoolean(context,
				context.getString(R.string.network_preferences_tag), false))
			mBuilder.addAction(R.drawable.ic_launcher, "Wifi", PendingIntent
					.getBroadcast(
							context,
							1,
							new Intent(context
									.getString(R.string.wifi_change_action)),
							PendingIntent.FLAG_CANCEL_CURRENT));
  
		Notification mNotification = mBuilder.build();

		mNotificationManager.notify(123456, mNotification);
	}

}
