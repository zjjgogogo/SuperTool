package com.jamesjaw.supertool.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.jamesjaw.supertool.data.Define;
import com.jamesjaw.supertool.utils.Debug;

public class NumberListManager extends DataManager {

	public ArrayList<NumberListData> data = null;
	Context context = null;
	
	public NumberListManager(Context context)
	{
		this.context = context;
		super.OpenDB(context, Define.db_name);
	}	
	
	public Cursor getData()
	{ 
		return super.Query_Origin(DataTestInfo.TABLENAME, null);
	}
	
	public ArrayList<NumberListData> getData2()
	{
		data = new ArrayList<NumberListData>();
		Cursor cs = super.Query_Origin(NumberListData.TABLENAME, null);
		int size = cs.getCount();
		cs.moveToFirst();
		for(int i = 0 ; i < size; i++)
		{
			NumberListData dti = new NumberListData();
			dti.type = cs.getInt(cs.getColumnIndex(NumberListData.TYPE));
			dti.isActive = cs.getInt(cs.getColumnIndex(NumberListData.ISACTIVE)) == 0 ? false : true;
			dti.number = cs.getString(cs.getColumnIndex(NumberListData.NUMBER));
			data.add(dti);
			cs.moveToNext();
		}
		return data;
	}
	
	public Cursor getData(String wheresql)
	{
		return super.Query_Origin(NumberListData.TABLENAME, wheresql);
	}
	
	public void InsertData(NumberListData di)
	{
		ContentValues values = new ContentValues();
		values.put(NumberListData.NUMBER,di.number);
		values.put(NumberListData.TYPE, di.type);
		values.put(NumberListData.ISACTIVE, di.isActive ? 1 : 0);
		super.Insert(NumberListData.TABLENAME, values, false);
	}
	
	public void InsertData(NumberListData[] dis)
	{
		ContentValues[] values = new ContentValues[dis.length];
		for(int i = 0 ; i < dis.length ; i++)
		{
			values[i] = new ContentValues();
			values[i].put(NumberListData.NUMBER, dis[i].number);
			values[i].put(NumberListData.TYPE, dis[i].type);
			values[i].put(NumberListData.ISACTIVE, dis[i].isActive ? 1: 0);
		}
		super.InsertBath(DataTestInfo.TABLENAME, values);
	}
	
	public void DeleteData(NumberListData di)
	{
		String wheresql = NumberListData.NUMBER + " = '" + di.number + "'";
		Debug.show("SQL test", wheresql);
		super.Delete(NumberListData.TABLENAME, wheresql);
	}
	
	public void DeleteData(NumberListData[] dis)
	{
		ContentValues[] values = new ContentValues[dis.length];
		for(int i = 0 ; i < dis.length ; i++)
		{
			values[i] = new ContentValues();
			values[i].put(NumberListData.NUMBER, dis[i].number);
			values[i].put(NumberListData.TYPE, dis[i].type);
			values[i].put(NumberListData.ISACTIVE, dis[i].isActive ? 1:0);
		}
		super.DeleteBath(NumberListData.TABLENAME, values, NumberListData.NUMBER);
	}
	
	public void UpdateData(NumberListData di)
	{
		String wheresql = NumberListData.NUMBER + " = '" + di.number + "'";
		ContentValues values = new ContentValues(); 
		values.put(NumberListData.TYPE, di.type);
		values.put(NumberListData.ISACTIVE, di.isActive ? 1 : 0);
		Debug.show("Test", wheresql);
		super.Update(NumberListData.TABLENAME, values, wheresql);
	}
	
	public void UpdateData(NumberListData[] dis)
	{
		ContentValues[] values = new ContentValues[dis.length];
		for(int i = 0 ; i < dis.length ; i++)
		{
			values[i] = new ContentValues(); 
			values[i].put(NumberListData.TYPE, dis[i].type);
			values[i].put(NumberListData.ISACTIVE, dis[i].isActive ? 1 : 0);
		}
		super.UpdateBath(NumberListData.TABLENAME, values, NumberListData.NUMBER);
	}
	
	
}
