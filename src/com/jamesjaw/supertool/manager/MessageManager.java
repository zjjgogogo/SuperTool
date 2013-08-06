package com.jamesjaw.supertool.manager;

import com.jamesjaw.supertool.data.MessageItem;
import com.jamesjaw.supertool.data.SMS;
import com.jamesjaw.supertool.utils.Debug;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;


public class MessageManager {
 
	public void deleteSMS(Context context, String message, String number) {
	    try {
	      
	        Uri uriSms = Uri.parse("content://sms/inbox");
	        Cursor c = context.getContentResolver().query(uriSms,
	            new String[] { "_id", "thread_id", "address",
	                "person", "date", "body" }, null, null, null);

	        if (c != null && c.moveToFirst()) {
	            do {
	                long id = c.getLong(0);
	                String address = c.getString(2);

	                if (address.equals(number)) { 
	                    context.getContentResolver().delete(
	                        Uri.parse("content://sms/" + id), null, null);
	                }
	            } while (c.moveToNext());
	            
	           c.close();
	        }
	    } catch (Exception e) {
	    		e.printStackTrace();
	    }
	}
	

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

	
	public void updateForChange(Context context) {

		Cursor cursor = context.getContentResolver().query(
				Uri.parse("content://sms/inbox"),
				PROJECTION,
				" address=? ", new String[] { "10086"}, "date desc");
//				"read=?",new String[]{"0"},"date desc");
		
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
