package com.jamesjaw.supertool.receiver;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

import com.jamesjaw.supertool.data.MessageItem;
import com.jamesjaw.supertool.data.SMS;
import com.jamesjaw.supertool.utils.Debug;

public class SMSObserver extends ContentObserver {

	private final String[] PROJECTION = new String[] {

			SMS._ID,// 0

			SMS.TYPE,// 1

			SMS.ADDRESS,// 2

			SMS.BODY,// 3

			SMS.DATE,// 4

			SMS.THREAD_ID,// 5

			SMS.READ,// 6

			SMS.PROTOCOL // 7

	};

	Cursor cursor = null;
	Context context;

	public SMSObserver(Handler handler, Context context) {
		super(handler);
		this.context = context;
	}
  
	@Override
	public void onChange(boolean selfChange) {
		// TODO Auto-generated method stub
		super.onChange(selfChange);
		updateForChange();

	}

	protected void updateForChange() {

		cursor = context.getContentResolver().query(
				Uri.parse("content://sms/inbox"),
				PROJECTION,
//				" address=? and read=?", new String[] { "10086", "0" }, "date desc");
				"read=?",new String[]{"1"},"date desc");
		
		cursor.moveToFirst();
		
		while (cursor.moveToNext())
	      {
			
			MessageItem item = new MessageItem();
			
	        int id = cursor.getInt(SMS.COLUMN_INDEX_ID);

	        int type = cursor.getInt(SMS.COLUMN_INDEX_TYPE);

	        String phone = cursor.getString(SMS.COLUMN_INDEX_PHONE);

	        String body = cursor.getString(SMS.COLUMN_INDEX_BODY);

	        String date = cursor.getString(SMS.COLUMN_INDEX_DATE);
	        
	        int read = cursor.getInt(SMS.COLUMN_INDEX_READ);
	        
	        int protocol = cursor.getInt(SMS.COLUMN_INDEX_PROTOCOL);
 
	        item.setId(id);
	        item.setType(type);
	        item.setPhone(phone);
	        item.setBody(body);
	        item.setProtocol(protocol);
	        item.setDate(date);
	        item.setRead(read);
	        
	        Debug.show(item.toString());
	      }
		
		cursor.close();

	}

}
