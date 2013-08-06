package com.jamesjaw.supertool.db;

import com.jamesjaw.supertool.utils.Debug;

/**
 * 
 *  号码名单信息
 *  可用于黑白名单
 *  
 * @author James
 *
 */
public class NumberListData extends BaseData {

	public static final String TABLENAME = "NumberListData";
	
	public static final int[] types = {BaseData.TYPE_TEXT,BaseData.TYPE_INTEGER,BaseData.TYPE_INTEGER};
	
	public static final String NUMBER = BaseData.pk; 
	
	/**
	 * 
	 * <li>0 WhiteList
	 * <li>1 BlackList
	 */
	public static final String TYPE = BaseData.c0;
	
	/**
	 * 
	 * <li>0 false
	 * <li>1 true
	 */
	public static final String ISACTIVE = BaseData.c1;
	
	public String number;
	
	public int type;
	
	public boolean isActive;
	
	@Override
	public String getCreateSQL() {
		
		Debug.show(getClass().getName(), BaseData.createTableSQL(TABLENAME, types, 0));
		return BaseData.createTableSQL(TABLENAME, types, 1) ;
		
	}

}
