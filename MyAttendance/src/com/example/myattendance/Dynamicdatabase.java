package com.example.myattendance;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Selection;
import android.widget.Toast;

public class Dynamicdatabase extends SQLiteOpenHelper{
	
	
      private static final String DB_NAME = "AttendaceDB";
	
      public static final String TABLE_STUDENTS = "Students";
      public static final String KEY_ROWID="_id";
	  public static final String KEY_ROLLNO="persons_rollno";
	  public static final String KEY_NAME="persons_name";
	  public static final String KEY_STUDENTBATCH="batch";
	  public static final String KEY_PRESENTCOUNT1="Presentcount1";
	  public static final String KEY_PRESENTCOUNT2="Presentcount2";
	  public static final String KEY_PRESENTCOUNT3="Presentcount3";
	  public static final String KEY_PRESENTCOUNT4="Presentcount4";
	  public static final String KEY_PRESENTCOUNT5="Presentcount5";
	  public static final String KEY_PRESENTCOUNT6="Presentcount6";
	  public static final String KEY_PRESENTCOUNT7="Presentcount7";
	  public static final String KEY_PRESENTCOUNT8="Presentcount8";
	  public static final String KEY_PRESENTCOUNT9="Presentcount9";
	  public static final String KEY_PRESENTCOUNT10="Presentcount10";
	  public static final String KEY_PRESENTCOUNT11="Presentcount11";
	  public static final String KEY_PRESENTCOUNT12="Presentcount12";
	  public static final String KEY_PRESENTCOUNT0="Presentcount";
	  public static final String KEY_Percent="percent";
	  public static final String KEY_TOTAL="total";
	  
	  public static final String TABLE_CLASSCOUNT = "Classes";
	  public static final String KEY_MONID="ID";
	  public static final String KEY_MONTH="month";
	  public static final String KEY_BATCH2="batch2";
	  public static final String KEY_CLASSES="numofclasses";
	  
	  public static final String TABLE_BATCH = "Batches";
	  public static final String KEY_BATCHID="id";
	  public static final String KEY_BATCH="batch_name";
	
	 
	  public static final String CVIEW="view_count";
	  public static final String KEYPRIME_DAYCOUNT="pk";
	  
	  public static final int DB_VERSION = 1;
	  
	
	  
	  public Dynamicdatabase(Context context)
	  {
			super(context, DB_NAME, null, DB_VERSION);
			// TODO Auto-generated constructor stub
		}
	  
	  
	  
	  @Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		  db.execSQL("CREATE TABLE "+TABLE_BATCH+" ("+KEY_BATCHID +" integer PRIMARY KEY AUTOINCREMENT ,"+ KEY_BATCH + " text )");
					  
			db.execSQL("CREATE TABLE " + TABLE_STUDENTS +" (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT"+"," +
					KEY_ROLLNO + " integer "+","+	
					KEY_NAME + " text "+","+
					KEY_STUDENTBATCH+" integer "+","+
					KEY_PRESENTCOUNT1+" INTEGER,"+
					KEY_PRESENTCOUNT2+" INTEGER,"+
					KEY_PRESENTCOUNT3+" INTEGER,"+
					KEY_PRESENTCOUNT4+" INTEGER,"+
					KEY_PRESENTCOUNT5+" INTEGER,"+
					KEY_PRESENTCOUNT6+" INTEGER,"+
					KEY_PRESENTCOUNT7+" INTEGER,"+
					KEY_PRESENTCOUNT8+" INTEGER,"+
					KEY_PRESENTCOUNT9+" INTEGER,"+
					KEY_PRESENTCOUNT10+" INTEGER,"+
					KEY_PRESENTCOUNT11+" INTEGER,"+
					KEY_PRESENTCOUNT12+" INTEGER,"+
					KEY_Percent+" integer,"+
					KEY_TOTAL+" integer ," +
							" CHECK ("+KEY_Percent+">=0 AND "+KEY_Percent+"<=100)," +
									" constraint A unique("+KEY_ROLLNO+","+KEY_STUDENTBATCH+") )");

			db.execSQL("create table "+TABLE_CLASSCOUNT+"("+
					KEYPRIME_DAYCOUNT+ " INTEGER PRIMARY KEY AUTOINCREMENT"+"," +
					
					KEY_MONID + " INTEGER ," +
					KEY_CLASSES+" integer"+","+
					KEY_BATCH2+" integer"+
						")");
	}@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// TODO Auto-generated method stub
		/*db.execSQL("DROP IF TABLE EXISTS" + TABLE_BATCH);
		db.execSQL("DROP IF TABLE EXISTS" + TABLE_CLASSCOUNT);
		db.execSQL("DROP IF TABLE EXISTS" + TABLE_STUDENTS);
	*/	onCreate(db);
	}
	 
	
	public  void  addBatch(String bname)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues c=new ContentValues();
		 
		c.put(KEY_BATCH, bname);
		db.insert(TABLE_BATCH, KEY_BATCHID, c);
		int bid = 1;
		 String[] selectionArgs={bname};
		
		 Cursor c1=db.rawQuery("SELECT "+KEY_BATCHID+" from "+TABLE_BATCH+" where "+KEY_BATCH+"=?",selectionArgs );
			if(c1.moveToFirst())
		 	{
				 bid=Integer.valueOf(c1.getString(0));
		 		
		 	}
		  
			c1.close();
			for(int i=1;i<=12;i++)
			{
			  
			  ContentValues c2=new ContentValues();
				c2.put(KEY_BATCH2, bid);
				c2.put(KEY_MONID,i);
				c2.put(KEY_CLASSES,0);
				db.insert(TABLE_CLASSCOUNT, KEYPRIME_DAYCOUNT, c2);
			  
			}

		db.close();
		
	}

public void deletebatch(int batch) {

	
	SQLiteDatabase db=this.getWritableDatabase();
	
	String[] whereArgs={String.valueOf(batch)};

	db.delete(TABLE_BATCH, KEY_BATCHID+"=?", whereArgs);
	db.delete(TABLE_STUDENTS, KEY_STUDENTBATCH+"=?", whereArgs);
	
	db.close();
}	
	
	public  int  addStudent(String sname,String en,int batchid)
	{int flag=1;
		try{SQLiteDatabase db=this.getWritableDatabase();
		ContentValues c=new ContentValues();
		
		c.put(KEY_ROLLNO, en);
		c.put(KEY_NAME, sname);
		c.put(KEY_STUDENTBATCH, batchid);
		c.put(KEY_PRESENTCOUNT1, 0);
		c.put(KEY_PRESENTCOUNT2, 0);
		c.put(KEY_PRESENTCOUNT3, 0);
		c.put(KEY_PRESENTCOUNT4, 0);
		c.put(KEY_PRESENTCOUNT5, 0);
		c.put(KEY_PRESENTCOUNT6, 0);
		c.put(KEY_PRESENTCOUNT7, 0);
		c.put(KEY_PRESENTCOUNT8, 0);
		c.put(KEY_PRESENTCOUNT9, 0);
		c.put(KEY_PRESENTCOUNT10, 0);
		c.put(KEY_PRESENTCOUNT11, 0);
		c.put(KEY_PRESENTCOUNT12, 0);
		c.put(KEY_Percent, 0);
		c.put(KEY_TOTAL, 0);
		
		db.insertOrThrow(TABLE_STUDENTS, KEY_ROWID, c);
		db.close();}
		catch (Exception e) {
			// TODO: handle exception
			flag=-1;
		}
		
		
		return flag;
	}
	
	public Cursor getbatchlist()
	{		
	  SQLiteDatabase db=this.getReadableDatabase();
	  Cursor cur=db.rawQuery("SELECT "+KEY_BATCHID+" as _id, "+KEY_BATCH+" from "+TABLE_BATCH,new String [] {});
	  
	  return cur;		
	 }

	public void deletestudent(int id,int bid)
	{
		SQLiteDatabase db=this.getWritableDatabase();

		String[] whereArgs={String.valueOf(id),String.valueOf(bid)};

		db.delete(TABLE_STUDENTS, KEY_ROLLNO+"=? and "+KEY_STUDENTBATCH+"=?", whereArgs);
	
		db.close();
	}
	 
	public Cursor markatt(int bid,int mon)
	{
		SQLiteDatabase db=this.getWritableDatabase();
	
		String[] selectionArgs={};
		
		Cursor c=db.rawQuery("select "+
						KEY_ROLLNO+" as _id,"+
						KEY_NAME+
						","+KEY_PRESENTCOUNT0+mon+" as  pc "+","+
						KEY_CLASSES+","+
						KEY_TOTAL+
				" from "+TABLE_STUDENTS	//+" inner join "
					+","
				+TABLE_CLASSCOUNT
				//+" on "+
				//TABLE_STUDENTS+"."+KEY_STUDENTBATCH+"="    +TABLE_CLASSCOUNT+"."+KEY_BATCH2
				+" where "+KEY_BATCH2+"="+bid+" and "
			    +KEY_STUDENTBATCH+"="+bid+" and "         
				+TABLE_CLASSCOUNT+"."+KEY_MONID+"="+mon
				, selectionArgs);
		
	return c;
		}  
	

	public void percent(String rn,int bid) {
int p;
			SQLiteDatabase db=getWritableDatabase();
		
				String sql="select "+KEY_TOTAL+" from "+TABLE_STUDENTS+" where "+KEY_ROLLNO+"=?"+" and "+KEY_STUDENTBATCH+"=?";
		String[] selectionArgs={rn,String.valueOf(bid)};
		Cursor c=db.rawQuery(sql, selectionArgs);
		c.moveToFirst();
		int tot=Integer.valueOf(c.getInt(0));
		
		c.close();
		String sql2="select sum("+KEY_CLASSES+") as s from "+TABLE_CLASSCOUNT+" where "+KEY_BATCH2+"=?";
		String[] selectionArgs2={String.valueOf(bid)};
		Cursor c2=db.rawQuery(sql2, selectionArgs2);
		
		c2.moveToFirst();
		int classes=Integer.valueOf(c2.getInt(0));
	if(classes!=0)	
		p=Integer.valueOf(tot*100/classes);
	else {p=100;}
db.execSQL( "update "+TABLE_STUDENTS+" set "+KEY_Percent+" = "+p+" where "+KEY_ROLLNO+" = " + String.valueOf(rn)+" and "+KEY_STUDENTBATCH+" = " +Integer.valueOf(bid) );

c2.close();

db.close();
}
	
	
	public Cursor mark(int bid)
	{
		SQLiteDatabase db=this.getWritableDatabase();
	
		Cursor c=db.rawQuery("SELECT "+
										KEY_ROLLNO+" as _id"+","+
										KEY_NAME+","+
										KEY_Percent+","+KEY_TOTAL+
										" FROM "+TABLE_STUDENTS+" WHERE "+KEY_STUDENTBATCH+" =?", new String [] {String.valueOf(bid)});
		
		return c;
	}
	public int totcl(int bid)
	{SQLiteDatabase db=this.getWritableDatabase();
		String sql2="select sum("+KEY_CLASSES+") as s from "+TABLE_CLASSCOUNT+" where "+KEY_BATCH2+"=?";
		String[] selectionArgs2={String.valueOf(bid)};
		Cursor c2=db.rawQuery(sql2, selectionArgs2);
		
		c2.moveToFirst();
		int classes=Integer.valueOf(c2.getInt(0));
		db.close();
		return classes;
	}

	public void updatePcount(String eno,int bid,int mon)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		
		db.execSQL( "update "+TABLE_STUDENTS+" set "+KEY_PRESENTCOUNT0+mon+" ="+ KEY_PRESENTCOUNT0+mon+ " + 1 where "+KEY_ROLLNO+" = " + String.valueOf(eno)+" and "+KEY_STUDENTBATCH+" = " +Integer.valueOf(bid) );				
		db.execSQL( "update "+TABLE_STUDENTS+" set "+KEY_TOTAL+" ="+ KEY_TOTAL+ " + 1 where "+KEY_ROLLNO+" = " + String.valueOf(eno)+" and "+KEY_STUDENTBATCH+" = " +Integer.valueOf(bid));
		db.close();
		}

	public void updatetotalcount(int batchid,int month)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		db.execSQL( "update "+TABLE_CLASSCOUNT+" set "+KEY_CLASSES+" = "+KEY_CLASSES+ " + 1 where "+KEY_BATCH2+" = " +Integer.valueOf(batchid)+" and "+KEY_MONID+" = " +Integer.valueOf(month) );		
		
		db.close();
		}
	
	public void clearbatch(int bid)
	{int mon;
		SQLiteDatabase db=this.getWritableDatabase();
		for(int i=1;i<=12;i++)
		{
			mon=i;
			db.execSQL( "update "+TABLE_STUDENTS+" set "+KEY_PRESENTCOUNT0+mon+" = 0 where "+KEY_STUDENTBATCH+" = " +Integer.valueOf(bid) );				
		}
		db.execSQL( "update "+TABLE_STUDENTS+" set "+KEY_Percent+" = 100  where "+KEY_STUDENTBATCH+" = " +Integer.valueOf(bid) );
		db.execSQL( "update "+TABLE_CLASSCOUNT+" set "+KEY_CLASSES+" = 0 "+" where "+KEY_BATCH2+" = " +Integer.valueOf(bid));
		db.execSQL( "update "+TABLE_STUDENTS+" set "+KEY_TOTAL+"= 0 where "+KEY_STUDENTBATCH+" = " +Integer.valueOf(bid) );
		db.close();
		}
	
}	  




