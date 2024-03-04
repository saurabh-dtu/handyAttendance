package com.example.myattendance;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class Deletestudent extends Activity implements OnItemSelectedListener{
	Dynamicdatabase db;
	Spinner spbatch,spst;
	Button del,bck;
	int bid,sid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_deletestudent);
	del=(Button)findViewById(R.id.bt_deletestudent);
	spbatch=(Spinner)findViewById(R.id.spsel_batch);
	bck=(Button)findViewById(R.id.btn_back);
	spst=(Spinner)findViewById(R.id.spsel_student);
	spbatch.setOnItemSelectedListener(this);
	spst.setOnItemSelectedListener(this);
	bck.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	});
del.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		db.deletestudent(sid, bid);
loadstudents();
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
public void loadbatches() {

	  Cursor c=db.getbatchlist();
	  startManagingCursor(c);
		 SimpleCursorAdapter s=new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, c, new String [] {Dynamicdatabase.KEY_BATCH,"_id"}, new int []{android.R.id.text1});
	  s.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	  spbatch.setAdapter(s);
db.close();		

}
public void loadstudents() {
	
	try {
		Cursor c2=db.mark(bid);
		  startManagingCursor(c2);
			 SimpleCursorAdapter s2=new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, c2, new String [] {"_id"}, new int []{android.R.id.text1});
		  s2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  spst.setAdapter(s2);
	db.close();
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
			long id) {
		// TODO Auto-generated method stub
		Spinner spinner = (Spinner) parent;
	     if(spinner.getId() == R.id.spsel_batch)
	     {
	       bid=(int) spbatch.getSelectedItemId();
           loadstudents();	       
	     }
	     else if(spinner.getId() == R.id.spsel_student);
	     {
	       sid=(int) spst.getSelectedItemId();
	     }
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
