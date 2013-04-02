package com.jamesjaw.mycode.demo.database;

import com.jamesjaw.mycode.util.Debug;

public class DataTestInfo extends BaseData {

	public static final String TABLENAME = "DataTestInfo";
	
	public static final int[] types = {BaseData.TYPE_TEXT,BaseData.TYPE_INTEGER,BaseData.TYPE_INTEGER};
	
	public static final String NAME = BaseData.c0;
	
	public static final String TIME = BaseData.pk;
	
	public static final String ISCHECK = BaseData.c1;
	
	public String name;
	public long time;
	public int i = 0;
	
	@Override
	public String getCreateSQL() {
		// TODO Auto-generated method stub
		Debug.e(getClass().getName(), BaseData.createTableSQL(TABLENAME, types, 1));
		return BaseData.createTableSQL(TABLENAME, types, 1) ;
	}

}
