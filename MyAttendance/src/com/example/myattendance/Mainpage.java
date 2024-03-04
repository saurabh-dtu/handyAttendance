package com.example.myattendance;


import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mainpage extends Activity
{
	Button bt_addstudent,bt_markatt,bt_delstudent,bt_addbatch,bt_delbatch,bt_viewatt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainpage);
		
		bt_addstudent=(Button)findViewById(R.id.bt1);
		bt_markatt=(Button)findViewById(R.id.bt2);
		bt_viewatt=(Button)findViewById(R.id.bt3);
		bt_delstudent=(Button)findViewById(R.id.bt4);
		bt_addbatch=(Button)findViewById(R.id.bt5);
		bt_delbatch=(Button)findViewById(R.id.bt6);
		
		bt_addstudent.setOnClickListener(new View.OnClickListener()
		{			
			@Override
			public void onClick(View v) 
			{
			
				Intent in1= new Intent(Mainpage.this,AddStudent.class);
				startActivity(in1);
										}
			}
		);
		
		bt_markatt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		
				Intent in1= new Intent(Mainpage.this,Markattendance.class);
				startActivity(in1);
			}
		});

		bt_viewatt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				Intent in3= new Intent(Mainpage.this , View_Report.class);
				startActivity(in3);
			}
		});
   bt_delstudent.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {

		Intent in5= new Intent(Mainpage.this ,Deletestudent.class);
		startActivity(in5);
		
		
	}
});
bt_addbatch.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {

		Intent in3= new Intent(Mainpage.this , Addbatch.class);
		startActivity(in3);
	}
});

bt_delbatch.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {

		Intent in5= new Intent(Mainpage.this ,Deletebatch.class);
		startActivity(in5);
	}
});
}

	

	}
