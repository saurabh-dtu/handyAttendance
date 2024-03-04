package com.example.myattendance;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class Deletebatch extends Activity implements OnItemSelectedListener{
	Dynamicdatabase db;
View view;
	Spinner sp;
	Button del,bck,clr;
	int bid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deletebatch);
		sp = (Spinner)findViewById(R.id.spin_delbatch);	
		del=(Button)findViewById(R.id.bt_delbatch);
		 sp.setOnItemSelectedListener( this);
		 bck=(Button)findViewById(R.id.btn_back2);
		 clr=(Button)findViewById(R.id.bt_clearbatch);
		 bck.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});

		
	}
	
	@Override
	protected void onStart()
	{
		// TODO Auto-generated method stub
		super.onStart();
		db =new Dynamicdatabase(this);
			
		  Cursor c=db.getbatchlist();
		  startManagingCursor(c);
			
		  SimpleCursorAdapter s=new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, c, new String [] {Dynamicdatabase.KEY_BATCH,"_id"}, new int []{android.R.id.text1});
		  s.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  sp.setAdapter(s);
				  
db.close();		
del.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			alet();
				
				//sp.removeViewInLayout(view);
			}
		});	
clr.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View v) {
	
		alertd();
	}
});
}
	public void alet() {
		AlertDialog al=new AlertDialog.Builder(this).create();
		al.setTitle("ARE U SURE?");
		al.setMessage(" THE BATCH WILL BE DELETED PERMANENTLY!!");
		al.setButton(AlertDialog.BUTTON_NEUTRAL, "Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				db.deletebatch(bid);
				showt();
			}

		});
       al.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
       al.show();

	}
	public void alertd() {
		AlertDialog al=new AlertDialog.Builder(this).create();
		al.setTitle("ARE U SURE?");
		al.setMessage(" ALL THE DATA OF STUDENTS WILL BE RESET!!");
		al.setButton(AlertDialog.BUTTON_NEUTRAL, "Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				db.clearbatch(bid);
				show();
			}

		});
       al.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
       al.show();

	}
	public void showt() {
		Toast.makeText(this, "deleted!!", Toast.LENGTH_LONG).show();
	}
	public void show() {
		Toast.makeText(this, "cleared all data of batch!!", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		 bid=Integer.valueOf((int)sp.getSelectedItemId());
		// view=arg1;
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}


}
