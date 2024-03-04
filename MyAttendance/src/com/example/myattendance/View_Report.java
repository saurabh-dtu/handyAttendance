package com.example.myattendance;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class View_Report extends Activity  implements OnItemSelectedListener
{Cursor c,c2,c3;
	Button btn_back;
int bid;String rn,name;
ListView lv;
Spinner spbatch;
Dynamicdatabase db;
TextView tv;
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewattendance);
		
		spbatch=(Spinner)findViewById(R.id.spinviewatt);
		lv=(ListView)findViewById(R.id.viewattendance);
		tv = (TextView)findViewById(R.id.tvtot2);
		spbatch.setOnItemSelectedListener(this);
		 btn_back=(Button)findViewById(R.id.btn_bac);
		 btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				finish();
			}
		});
		 
}

	@Override
		protected void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			db=new Dynamicdatabase(this);
			loadbatches();
		}
	
	
	public void loadbatches()
	{		
	  try
		{
		 Cursor c3=db.getbatchlist();
		  startManagingCursor(c3);
			
		  SimpleCursorAdapter s=new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, c3, new String [] {Dynamicdatabase.KEY_BATCH}, new int []{android.R.id.text1});
		  s.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  spbatch.setAdapter(s);
		 
		  db.close();
		  
		}
		
		catch (Exception e) 
		{
			// TODO: handle exception
			  AlertDialog al=new AlertDialog.Builder(this).create();
			  al.setTitle(e.toString());
			  al.show();
		}
	}
	public void  loadreport() {
		
		
		try
		{c=db.mark(bid);
		
		if(c.moveToFirst())
	 	{
	 		do
	 		{
	 	    	 rn=c.getString(0).toString();
	 	    	name=c.getString(1).toString();
	 	    	db.percent(rn, bid);
	 		}
	 		while(c.moveToNext());
	    }	 	     
db.close();

		 c2=db.mark(bid);
		startManagingCursor(c2);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, 
	            R.layout.threerow, 
	            c2, 
	           new String [] {"_id",Dynamicdatabase.KEY_NAME,Dynamicdatabase.KEY_TOTAL,Dynamicdatabase.KEY_Percent},
	            new int[] {R.id.col_txt31,R.id.col_txt32,R.id.col_txt33,R.id.col_txt34});

  lv.setAdapter(adapter);
  lv.setBackgroundColor(0);
 
  db.close();
  String s=String.valueOf(db.totcl(bid));
 
 tv.setText(s) ;
		}
		catch(Exception e)
		{
			AlertDialog al=new AlertDialog.Builder(this).create();
			  al.setMessage(e.toString());
			  al.show();
		}      

		
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		bid=Integer.valueOf((int)spbatch.getSelectedItemId());
		loadreport();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}	
	}
	
