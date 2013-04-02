package com.jamesjaw.supertool.db;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jamesjaw.supertool.utils.Debug;



/**
 * 
 * 	 通用数据表操作类（试用版2.1）
 *  
 * 
 */
public class DataManager {
	
	private final static String TAG = "DataManager";
	
	public DataManager()
	{
		
	}
	
	
	private static SQLiteDatabase database = null;
	
	/**
	 *   
	 *   开启数据库(注，每次使用DataManager时，必先执行先执行此方法)
	 *   参数1:Context
	 *   参数2:数据库名字
	 * 
	 */
	public void OpenDB(Context con,String dbname)
	{
		if(database == null)
			database = con.openOrCreateDatabase(dbname, Context.MODE_PRIVATE, null);
	}
	
	
	
	/**
	 * 
	 *   关闭数据库（注：操作数据库完成后，记得使用！）
	 * 
	 */
	public void CloseDB()
	{
		if(database != null)
			database.close();
		database = null;
	}
	

	/**
	 * 
	 *   检查表是否存在
	 *   参数1,表名
	 * 
	 */
	public boolean IsExistTable(String tablename)
	{ 
		if(database != null)
		{
			Cursor cs = database.query("sqlite_master", null, "type='table' and name=?", new String[]{tablename}, null, null,null);
		
		if(cs.getCount() > 0)
		{  
			cs.close();
			return true;
		}
		
		cs.close();
		}
		else
		{
			Debug.show(TAG, "Database do not opened!");
		}
		return false;
	}
	
	/**
	 * 
	 *   创建表格
	 *   参数1,表名
	 *   参数2,创建语句
	 * 
	 */
	public boolean CreateTable(String tablename,String createsql)
	{
		if(!IsExistTable(tablename))
		{
			if(database != null)
			{
				database.execSQL(createsql);
				return true;
			}
			else 
			{
				return false;
			}
		}
		else
		{

		}
		return false;
	}
	
	/**
	 * 
	 * 删除表所有数据
	 * @param tablename
	 */
	public void deleteAll(String tablename)
	{
		   if(database != null)
		   {
				database.execSQL("delete from " + tablename);
		   }
		   
	}
	
	
	
	/**
	 * 
	 *   插入数据(少数据量使用)
	 *   参数1,表名
	 *   参数2,为一个ContentValues,就是要插入的值
	 *   参数3,自动生成主键，只能主键在第一字段的情况
	 * 
	 */
	public long Insert(String tablename,ContentValues values,boolean autoPK)
	{  
		long result = 1;
		if(IsExistTable(tablename))
		{
			if(database != null)
			{  
				if(autoPK && values.containsKey(BaseData.c0))//判断是否有设置主键
				{
					    boolean isRight = false;
						do
						{
							try
							{  
								values.remove(BaseData.c0);
								result = System.currentTimeMillis();
								values.put(BaseData.c0, result);
								database.insert(tablename, null, values);
								isRight = true;
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					while(!isRight);
					return result;
				}
				else
				{
					database.insert(tablename, null, values);
					return 1;
				}
			}
			else
			{
				return 0;
			}
		}
		return 0;
	}
	
	/**
	 * 
	 *   插入数据(批量，不带自动生成主键功能)
	 *   参数1,表名
	 *   参数2,为一个ContentValues,就是要插入的值
	 * 
	 */
	public void InsertBath(String tablename,ContentValues[] values)
	{
		
		if(IsExistTable(tablename))
		{
		   if(values != null)
		   {    
			   database.beginTransaction();
			   
			   for(int i = 0; i < values.length ; i ++)
			   { 
				   String tag1 = "Insert into " + tablename + " ";
			       String tag2 = " values";
			       String tag1_1 = "(";
			       
			       String tag1_2 = ")";
			       String tag_colum = tag1_1 + "";
			       String tag_param = tag1_1 + "";
			       Set<Entry<String, Object>> set = values[i].valueSet();
			       int size = set.size();
			       Object[] content =  new Object[size];
			       Iterator<Entry<String, Object>> iter = set.iterator();
			       int index = 0;
			       while(iter.hasNext())
			       {  
			    	   Entry<String, Object> temp = iter.next();
			    	   tag_colum += temp.getKey();
			    	   tag_param += "?";
			    	   content[index++] = temp.getValue();
			    	   if(index != size)
			    	   {
			    		   tag_colum += ",";
			    		   tag_param += ",";
			    	   }
			    	   else
			    	   {
			    		   tag_colum += tag1_2;
			    		   tag_param += tag1_2;
			    	   }
			       }
			       Debug.show(TAG, "SQL: " + tag1 + tag_colum + tag2 + tag_param + " value1 :" + content[0] + " value2 :" + content[1]);
			       database.execSQL(tag1 + tag_colum + tag2 + tag_param, content);
			   }
			   
			   database.setTransactionSuccessful();
			   database.endTransaction();
		   }
		   else 
		   {
			   Debug.show(TAG, "Not data!");
		   }
		}
	}
	
	/**
	 * 
	 *   取得表行数
	 *   参数1:表名
	 * 
	 */
	public int GetRowNum(String tablename)
	{
		int num = 0;
		if(IsExistTable(tablename))
		{
			if(database != null)
			{  
				Cursor cs = Query0(tablename,null);
				if(cs != null)
					{
						num = cs.getCount();
						cs.close();
					}
				else
					return num;
			}
			else
			{
				return num;
			}
		}
		return num;
	}
	
	/**
	 * 
	 *   批量删除数据
	 *   只能根据主键删除
	 *   参数1,表名
	 *   参数2,删除数据
	 * 
	 */
	public boolean DeleteBath(String tablename,ContentValues[] values,String pk)
	{
		if(IsExistTable(tablename))
		{
			if(database != null)
			{   
				String tag1 = "Delete from " + tablename + " where " ; 
				database.beginTransaction();
				for(int i = 0 ; i < values.length ; i++)
				{
					Object value = values[i].get(pk);
					Debug.show(TAG, tag1 + pk + " = ?");
					database.execSQL(tag1 + pk + " = ?" , new Object[]{value});
				}
				database.setTransactionSuccessful();
				database.endTransaction();
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 
	 *   删除数据
	 *   参数1,表名
	 *   参数2,删除条件
	 * 
	 */
	public boolean Delete(String tablename,String wheresql)
	{
		if(IsExistTable(tablename))
		{
			if(database != null)
			{ 
				database.delete(tablename, wheresql, null);
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;
	}
	
	
	/**
	 * 
	 *   更新数据(少数据量使用)
	 *   参数1,表名
	 *   参数2,为一个ContentValues,就是要更新的值
	 *   参数3,条件语句
	 * 
	 */
	public boolean Update(String tablename,ContentValues values,String wheresql)
	{
		if(IsExistTable(tablename))
		{
			if(database != null)
			{   
				database.update(tablename, values, wheresql, null);
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 
	 *   批量更新数据
	 *   参数1,表名
	 *   参数2,为一个ContentValues,就是要更新的值
	 *   参数3,条件语句
	 * 
	 */
	public boolean UpdateBath(String tablename,ContentValues[] values,String pk)
	{
		if(IsExistTable(tablename))
		{
			if(database != null)
			{   
				database.beginTransaction();
				Debug.show(TAG, "Update start!");
				String tag1 = "Update " + tablename + " Set ";
				for (int i = 0; i < values.length; i++) {
					Set<Entry<String, Object>> set = values[i].valueSet();
					Iterator<Entry<String, Object>> iter = set.iterator();
					int index = 0;
					String tag_colum = "";
					int size = set.size();
					int curindex = index;
					Object pkvalue = null;
					String tag_final = null;
					Object[] content = new Object[size];
					while (iter.hasNext()) {
						Entry<String, Object> temp = iter.next();
						String key = temp.getKey();
						
						if(!key.equals(pk))
						{
							tag_colum += temp.getKey() + "= ? ";
							content[index++] = temp.getValue();
						}
						else
						{
							pkvalue = temp.getValue();
						}
						
						if (curindex < size - 1 && !key.equals(pk)) {
							tag_colum += ",";
						} else if(curindex == size - 1){
							content[curindex] = pkvalue;
							tag_final = " where " + pk + "= ?";
						}
						curindex++;
					}
					Debug.show(TAG, tag1 + tag_colum + tag_final + " id : " + pkvalue);
					database.execSQL(tag1 + tag_colum + tag_final, content);
				}
				database.setTransactionSuccessful();
				database.endTransaction();
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;
	}
	
	public Cursor Query0(String tablename,String wheresql)
	{
		return database.query(tablename, null, wheresql, null, null, null, null);	
	}
	
	
	/**
	 * 
	 *   查询数据,当所有数据类型为TEXT
	 *   @param参数1,表名
	 *   @param参数2,条件语句
	 *   @return 返回ArrayList<HashMap<String,String>>
	 */
	public ArrayList<HashMap<String,String>> Query(String tablename,String wheresql)
	{
		ArrayList<HashMap<String,String>> infos = null;
		if(IsExistTable(tablename))
		{
			if(database != null)
			{
				Cursor cs = Query0(tablename, wheresql);	
			    int max = cs.getCount();
			    if(max > 0)
			    {   
			    	infos = new ArrayList<HashMap<String,String>>();
			    	cs.moveToFirst();
			    	do
			    	{
			    	int columnmax = cs.getColumnCount();
			    	HashMap<String,String> data = new HashMap<String,String>();
			    	for(int i = 0 ; i < columnmax ; i++)
			    	{
			    		data.put(cs.getColumnName(i), cs.getString(i));
			    	}
			    	infos.add(data);
			    	}
			    	while(cs.moveToNext())
			    	;
			    	cs.close();
			    }
			    return infos;
			}
			else
			{
				return infos;
			}
		}
		return infos;
	}



	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if(database != null)
		{
			database.close();
		}
	}
	
	
}
