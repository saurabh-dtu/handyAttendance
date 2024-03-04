package com.example.myattendance;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;

import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class AddStudent extends Activity implements OnItemSelectedListener
 {int flag=1;
	EditText castname,castrollno;
	Button castok,btn_back; 
	Dynamicdatabase db;
	Spinner sp;
	int batchid;
	//ListView lv;
	Cursor c1;
	String[] blist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addstudent);
		
		//sp.setOnItemSelectedListener(AddStudent.this);
		//lv = (ListView)findViewById(R.id.lv_studentlist);
		castrollno=(EditText)findViewById(R.id.edt_enterrollno);
		castname=(EditText)findViewById(R.id.edt_enterstdname);
		
		TextView txtheading=(TextView)findViewById(R.id.heading);
		 txtheading.setText("Add Student");
		
		 btn_back=(Button) findViewById(R.id.btn_back);		
		 btn_back.setOnClickListener(new View.OnClickListener() 
			{
			  @Override
			  public void onClick(View v)  
			   {
				   finish();
			   }
			});
		
		
		castok=(Button)findViewById(R.id.btn_add_save);
		sp=(Spinner)findViewById(R.id.spin);
		 castok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				 
				try
				{
					if(castrollno.getText().toString().equals("")||castname.getText().toString().equals(""))
						t();
					else{
					int num=Integer.valueOf(castrollno.getText().toString());
					
					if(num<=200&&num>0)
					{if(!castname.getText().toString().equals(""))addStudent();else t();}
					else
					name();}
				} 
				catch (Exception e) 
				  {
					// TODO: handle exception
					  AlertDialog al=new AlertDialog.Builder(AddStudent.this).create();
					  al.setTitle(e.toString());
					  al.show();
				  }
			}
			});
	}
	public void t() {
		// TODO Auto-generated method stub
		
		Toast.makeText(this, "enter valid data", Toast.LENGTH_LONG).show();		
	}
	
	public void name() {
		castrollno.setText("");
		Toast.makeText(this, "invalid roll number", Toast.LENGTH_LONG).show();
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		db= new Dynamicdatabase(this);
		loadData();
	}
	
		
	
	public void loadData()
	{	
	  try
		{	
		  Cursor c=db.getbatchlist();
		  startManagingCursor(c);
			
		  SimpleCursorAdapter s=new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, c, new String [] {Dynamicdatabase.KEY_BATCH,"_id"}, new int []{android.R.id.text1});
		  s.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  sp.setAdapter(s);
		  db.close();
		}
		
		catch (Exception e) 
		{
			// TODO: handle exception
			  AlertDialog al=new AlertDialog.Builder(AddStudent.this).create();
			  al.setTitle(e.toString());
			  al.show();
		}
	}

	
	
	public void  addStudent() 
	{
		AlertDialog al=new AlertDialog.Builder(this).create();
		al.setTitle("Adding Student");
		al.setMessage("Sure to insert ?");
		al.setButton(AlertDialog.BUTTON_NEUTRAL, "Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				int bid=Integer.valueOf((int)sp.getSelectedItemId());

				try {
			 flag=db.addStudent(castname.getText().toString(), castrollno.getText().toString(),bid);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					flag=-1;
				}
			
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
	{
		//Intent intback=new Intent();
		//setResult(RESULT_OK, intback);
		if(flag==-1)
			{//Toast.makeText(this, "Roll number already exists!", Toast.LENGTH_LONG).show();
			AlertDialog al=new AlertDialog.Builder(this).create();
			al.setMessage("Sorry!! Roll number already exists.");
			al.setButton(AlertDialog.BUTTON_NEUTRAL, "Okay", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
									
				}
			});
			al.show();}
		else
		Toast.makeText(this, "student added", Toast.LENGTH_LONG).show();
		castname.setText("");
		castrollno.setText("");
	}

		

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {

		// TODO Auto-generated method stub
		 batchid=Integer.valueOf((int)sp.getSelectedItemId());
		
	}	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	}
	}