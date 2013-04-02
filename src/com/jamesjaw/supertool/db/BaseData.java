package com.jamesjaw.supertool.db;



/**
 *  
 *   VO的基类,万能数据基类（试用版）
 *   目前功能 ：
 *   1.存放基础字段名13个
 *   2.生成创表语句（简单版）
 *   
 *   注意：
 *   要使用主键，请使用第一个字段c0;
 *   
 */
public abstract class BaseData {
	
   private final static String TAG = "BaseData";
   
   /**
    * 
    *  字段名
    *  c0..c12
    */
   public final static String pk = "_id";
   public final static String c0 = "c0";
   public final static String c1 = "c1";
   public final static String c2 = "c2";
   public final static String c3 = "c3";
   public final static String c4 = "c4";
   public final static String c5 = "c5";
   public final static String c6 = "c6";
   public final static String c7 = "c7";
   public final static String c8 = "c8";
   public final static String c9 = "c9";
   public final static String c10 = "c10";
   public final static String c11 = "c11";
   public final static String c12 = "c12";
   
   public static final int TYPE_TEXT = 1;
   public static final int TYPE_INTEGER = 2;
   public static final int TYPE_NUMERIC = 3;
   public static final int TYPE_REAL = 4;
   public static final int TYPE_BLOB = 5;

   /**
    * 
    * 字段类型  TYPE1 = "TEXT"
    * 
    */
   public static  final String TYPE1 = "TEXT";
   
   /**
    * 
    * 字段类型  TYPE2 = "INTEGER"
    * 
    */
   public static final String TYPE2 = "INTEGER";
   
   /**
    * 
    * 字段类型  TYPE3 = "NUMERIC"
    * 
    */
   public static final String TYPE3 = "NUMERIC";
   
   /**
    * 
    * 字段类型  TYPE4 = "REAL"
    * 
    */
   public static final String TYPE4 = "REAL";
   
   /**
    * 
    * 字段类型  TYPE5 = "BLOB"
    * 
    */
   public static final String TYPE5 = "BLOB";
   
   /**
    *   
    *    关键字修饰符 
    * 
    */
   public static final String PK = "PRIMARY KEY";
   
  /**
   * 
   * 
   * @param tablename 需要创建的表名
   * @param cNums 需要字段数（12个以内）不包括主键
   * @param needID 是否需要主键,主键为第一个字段
   * @return 创表语句
   * 待改进，要加入类型的自定义，目前只自动生成text类型的字段
   */
   public static final String createTableSQL(String tablename , int cNums , boolean needID)
   {
	   String createTable = null;
	   if(tablename != null && cNums > 0)
	   {
		   createTable = "create table " + tablename + " ( ";
		   if(needID)
		   {
			   createTable += c0 + " " + TYPE1 + " " + PK + ", ";
		   }
		   for(int i = 1 ; i < cNums; i++)
		   {
			   createTable += "c" + i + " " + TYPE1 + " , ";
		   }
		   createTable += "c" + cNums + " " + TYPE1 + " )";
	   }
	   return createTable;
   }
   
   /**
    * 
    * 
    * @param tablename 需要创建的表名
    * @param types 字段的类型数组 ，数组长度小于等于13
    * @param pk_index 主键的位置  ： -1为没主键
    * @return 创表语句
    * 待改进，要加入类型的自定义，目前只自动生成text类型的字段
    */
   public static final String createTableSQL(String tablename , int[] types , int pk_index)
   {
	   String createTable = null;
	   if(tablename != null && types != null && types.length > 0 && types.length <= 13)
	   {
		   createTable = "create table " + tablename + " ( ";
		   int index = 0;
		   for(int i = 0 ; i < types.length; i++)
		   { 
			 if(i != pk_index)
			 {
				 switch(types[i])
				 {
				 case TYPE_TEXT:
					 createTable += " c" + index + " " + TYPE1;
					 break;
				 case TYPE_INTEGER:
					 createTable += " c" + index + " " + TYPE2;
					 break;
				 case TYPE_REAL:
					 createTable += " c" + index + " " + TYPE4;
					 break;
				 case TYPE_NUMERIC:
					 createTable += " c" + index + " " + TYPE3;
					 break;
				 case TYPE_BLOB:
					 createTable += " c" + index + " " + TYPE5;
					 break;
				 }
				 index++;
			 }
			 else 
			 {
				 switch(types[i])
				 {
				 case TYPE_TEXT:
					 createTable += " " + pk + " " + TYPE1;
					 break;
				 case TYPE_INTEGER:
					 createTable += " " + pk + " " + TYPE2;
					 break;
				 case TYPE_REAL:
					 createTable += " " + pk + " " + TYPE4;
					 break;
				 case TYPE_NUMERIC:
					 createTable += " " + pk + " " + TYPE3;
					 break;
				 case TYPE_BLOB:
					 createTable += " " + pk + " " + TYPE5;
					 break;
				 }
			 }
			 if(pk_index == i)
		     {
			     createTable += " " + PK;
		     }
			 
			 if(i == types.length - 1)
			 {
				 createTable += ")";
			 }
			 else
			 {
				 createTable += ",";
			 }
			 
		   }
		  
	   }
	   return createTable;
   }
   
   
   /**
    * 
    *   取得建表语句，子类需实现
    * 
    * @return 建表语句
    */
   public abstract String getCreateSQL();
}
