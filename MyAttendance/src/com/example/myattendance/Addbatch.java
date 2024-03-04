package com.example.myattendance;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Addbatch extends Activity {
	EditText edtbatchname;
	ListView lv;
	String batchname;
	Button btn_save,btn_back;
	Dynamicdatabase db;
	TextView txtheading;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_batch);
		 edtbatchname=(EditText)findViewById(R.id.edt_enterbatchname);
		 
		 txtheading=(TextView)findViewById(R.id.heading);
		 txtheading.setText("Create Batch");
		
		 lv = (ListView)findViewById(R.id.lv_batchlist);
		 btn_back=(Button) findViewById(R.id.btn_back);		
		 btn_back.setOnClickListener(new View.OnClickListener() 
			{
			  @Override
			  public void onClick(View v)  
			   {
				   finish();
			   }
			});
		
		btn_save=(Button) findViewById(R.id.btn_add_save);		
		btn_save.setOnClickListener(new View.OnClickListener() 
		{
		  @Override
		  public void onClick(View v)  
		   {
			  if(!edtbatchname.getText().toString().equals(""))
			   createBatch(); 
		   }
		});
	}	
	
	@Override
	protected void onStart()
	{
		// TODO Auto-generated method stub
		super.onStart();
		db =new Dynamicdatabase(this);
		loadbatches();
		
	}

	

	public void loadbatches()
	{		
	  try
		{	
		 Cursor c=db.getbatchlist();
		  startManagingCursor(c);
		  
		 SimpleCursorAdapter s=new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, c, new String [] {Dynamicdatabase.KEY_BATCH}, new int []{android.R.id.text1});
		  s.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  lv.setAdapter(s);
		  db.close();	

		}
		
		catch (Exception e) 
		{
			// TODO: handle exception
			  AlertDialog al=new AlertDialog.Builder(Addbatch.this).create();
			  al.setTitle(e.toString());
			  al.show();
		}
	}
	
	public void  createBatch() 
	{
		AlertDialog al=new AlertDialog.Builder(this).create();
		al.setTitle("Create Batch");
		al.setMessage("CREATE?");
		al.setButton(AlertDialog.BUTTON_NEUTRAL, "Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				    batchname = edtbatchname.getText().toString();
					db.addBatch(batchname);
				back();
				
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
	
	public void back() 
	{	edtbatchname.setText("");
		Toast.makeText(this, "BATCH CREATED!!", Toast.LENGTH_LONG).show();
		Intent intback=new Intent();
		setResult(RESULT_OK, intback);
		finish();
	}
	
	
	
	
}
