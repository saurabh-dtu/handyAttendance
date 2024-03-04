package com.example.myattendance;

import mypackage.MultiListView;

import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;




public class Markattendance extends Activity implements OnItemSelectedListener  {
	Dynamicdatabase db;
	Spinner sp,spmonth;
	ListView lv;
	int bid ;
	int spm;
	Cursor c,c1;
	Button ch;
	String s;
	TextView tv;
	
	Button btn_back,btn_add;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_markattendance);
		 sp=(Spinner)findViewById(R.id.spinmarkatt);
		 spmonth=(Spinner)findViewById(R.id.spinmonth);
		 sp.setOnItemSelectedListener(this);
		 spmonth.setOnItemSelectedListener(this);
	tv=(TextView)findViewById(R.id.tvtot);	
		 lv = (ListView)findViewById(R.id.markattendance);
	
		 
		 btn_back=(Button)findViewById(R.id.btn_back);
		 btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		 
		 btn_add=(Button)findViewById(R.id.btn_add_save);
		 btn_add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent go=new Intent(Markattendance.this,MultiListView.class);
				int sp1=Integer.valueOf((int)sp.getSelectedItemId());
				 spm=Integer.valueOf((int)spmonth.getSelectedItemId());
				
				go.putExtra("bid",sp1);
				go.putExtra("mon",spm);
				
				startActivity(go);
			}
		});
	}
	
	@Override
	protected void onStart()
	{
		// TODO Auto-generated method stub
		super.onStart();
		
		loadbatches();	
		
		int bid=Integer.valueOf((int)sp.getSelectedItemId());
		loadstudents(bid,spm);
		
	}
	
	public void loadbatches()
	{		
	  try
		{db =new Dynamicdatabase(this);	
		 c=db.getbatchlist();
		  startManagingCursor(c);
			
		  SimpleCursorAdapter s=new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, c, new String [] {Dynamicdatabase.KEY_BATCH}, new int []{android.R.id.text1});
		  s.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  sp.setAdapter(s);
		  
		  String[] paths1={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
		ArrayAdapter<String> a1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, paths1); 
		a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
		spmonth.setAdapter(a1); 
		  
		
		
		   	  db.close();
		  
		}
		
		catch (Exception e) 
		{
			// TODO: handle exception
			  AlertDialog al=new AlertDialog.Builder(Markattendance.this).create();
			  al.setTitle(e.toString());
			  al.show();
		}
	}
	
	
	public void loadstudents(int bid,int mon)
	{
		
		try
		{db =new Dynamicdatabase(this);
			
			c1=db.markatt(bid,mon+1);	
			
			startManagingCursor(c1);
			
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, 
			            R.layout.multirow, 
			            c1, 
			           new String [] {"_id",Dynamicdatabase.KEY_NAME,"pc"}, 
			            new int[] {R.id.col_txt1,R.id.col_txt2,R.id.col_txt3});

		
		lv.setAdapter(adapter);
	      lv.setBackgroundColor(0);
	      
	         int i;
			if(c1.moveToFirst())
	         i=c1.getInt(c1.getColumnIndex(Dynamicdatabase.KEY_CLASSES));
			else i=0;
	   
			 tv.setText(String.valueOf(i));
			 
	      db.close();
	   

		}
		catch(Exception e)
		{
			AlertDialog al=new AlertDialog.Builder(Markattendance.this).create();
			  al.setMessage(e.toString());
			  al.show();
		}      
	    	
}
@Override
protected void onStop() {
	// TODO Auto-generated method stub
	super.onStop();
	c1.close();
	c.close();
}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long id) {
		// TODO Auto-generated method stub
		//bid=Integer.valueOf((int)sp.getSelectedItemId());
		bid=(int)sp.getSelectedItemId();
		spm=Integer.valueOf((int)spmonth.getSelectedItemId());
		loadstudents(bid,spm);
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
