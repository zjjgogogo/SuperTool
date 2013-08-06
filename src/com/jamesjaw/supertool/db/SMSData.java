package com.jamesjaw.supertool.db;

import com.jamesjaw.supertool.utils.Debug;

/**
 * 
 * 私密短信信息
 * 
 * @author James
 *
 */
public class SMSData extends BaseData {

	public static final String TABLENAME = "SMSData";
	
	public static final int[] types = {BaseData.TYPE_TEXT,BaseData.TYPE_TEXT,BaseData.TYPE_TEXT,BaseData.TYPE_TEXT,BaseData.TYPE_TEXT,BaseData.TYPE_TEXT,BaseData.TYPE_TEXT};
	
	public static final String ID = BaseData.pk;
	  
	public static final String TYPE = BaseData.c0;
 
	public static final String PROTOCOL = BaseData.c1;
	
	public static final String PHONE = BaseData.c2;
	
	public static final String BODY = BaseData.c3;
	
	public static final String DATE = BaseData.c4;
	
	public static final String READ = BaseData.c5; 
	
	public String id;

	public String type;

	public String protocol;

	public String phone;

	public String body;
	
	public String date;
	
	public String read;
	
	@Override
	public String getCreateSQL() {
		 
		Debug.show(getClass().getName(), BaseData.createTableSQL(TABLENAME, types, 0));
		return BaseData.createTableSQL(TABLENAME, types, 1) ;
	}

}
