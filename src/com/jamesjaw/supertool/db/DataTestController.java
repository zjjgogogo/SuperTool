package com.jamesjaw.supertool.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.jamesjaw.supertool.data.Define;
import com.jamesjaw.supertool.utils.Debug;

public class DataTestController extends DataManager {

	public ArrayList<DataTestInfo> data = null;
	Context context = null;
	
	public DataTestController(Context context)
	{
		this.context = context;
		super.OpenDB(context, Define.db_name);
	}	
	
	public Cursor getData()
	{
//		data = new ArrayList<DataTestInfo>();
		return super.Query_Origin(DataTestInfo.TABLENAME, null);
//		return data;
	}
	
	 
	public ArrayList<DataTestInfo> getData2()
	{
		data = new ArrayList<DataTestInfo>();
		Cursor cs = super.Query_Origin(DataTestInfo.TABLENAME, null);
		int size = cs.getCount();
		cs.moveToFirst();
		for(int i = 0 ; i < size; i++)
		{
			DataTestInfo dti = new DataTestInfo();
			dti.time = cs.getLong(cs.getColumnIndex(DataTestInfo.TIME));
			dti.name = cs.getString(cs.getColumnIndex(DataTestInfo.NAME));
			data.add(dti);
			cs.moveToNext();
		}
		return data;
	}
	
	public Cursor getData(String wheresql)
	{
//		data = new ArrayList<DataTestInfo>();
		return super.Query_Origin(DataTestInfo.TABLENAME, wheresql);
//		return data;
	}
	
	public void InsertData(DataTestInfo di)
	{
		ContentValues values = new ContentValues();
		values.put(DataTestInfo.NAME, di.name);
		values.put(DataTestInfo.TIME, di.time);
		values.put(DataTestInfo.ISCHECK, di.i);
		super.Insert(DataTestInfo.TABLENAME, values, false);
	}
	
	public void InsertData(DataTestInfo[] dis)
	{
		ContentValues[] values = new ContentValues[dis.length];
		for(int i = 0 ; i < dis.length ; i++)
		{
			values[i] = new ContentValues();
			values[i].put(DataTestInfo.NAME, dis[i].name);
			values[i].put(DataTestInfo.TIME, dis[i].time);
			values[i].put(DataTestInfo.ISCHECK, dis[i].i);
		}
		super.InsertBath(DataTestInfo.TABLENAME, values);
	}
	
	public void DeleteData(DataTestInfo di)
	{
		String wheresql = DataTestInfo.pk + " = '" + di.time + "'";
		Debug.show("SQL test", wheresql);
		super.Delete(DataTestInfo.TABLENAME, wheresql);
	}
	
	public void DeleteData(DataTestInfo[] dis)
	{
		ContentValues[] values = new ContentValues[dis.length];
		for(int i = 0 ; i < dis.length ; i++)
		{
			values[i] = new ContentValues();
			values[i].put(DataTestInfo.NAME, dis[i].name);
			values[i].put(DataTestInfo.TIME, dis[i].time);
			values[i].put(DataTestInfo.ISCHECK, dis[i].i);
		}
		super.DeleteBath(DataTestInfo.TABLENAME, values, DataTestInfo.TIME);
	}
	
	public void UpdateData(DataTestInfo di)
	{
		String wheresql = DataTestInfo.pk + " = '" + di.time + "'";
		ContentValues values = new ContentValues();
		values.put(DataTestInfo.NAME, di.name);
		values.put(DataTestInfo.ISCHECK, di.i);
		Debug.show("Test", wheresql);
		super.Update(DataTestInfo.TABLENAME, values, wheresql);
	}
	
	public void UpdateData(DataTestInfo[] dis)
	{
		ContentValues[] values = new ContentValues[dis.length];
		for(int i = 0 ; i < dis.length ; i++)
		{
			values[i] = new ContentValues();
			values[i].put(DataTestInfo.NAME, dis[i].name);
			values[i].put(DataTestInfo.ISCHECK, dis[i].i);
			values[i].put(DataTestInfo.TIME, dis[i].time);
		}
		super.UpdateBath(DataTestInfo.TABLENAME, values, DataTestInfo.TIME);
	}
}
