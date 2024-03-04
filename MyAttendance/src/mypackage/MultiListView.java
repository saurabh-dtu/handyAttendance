package mypackage;

import java.util.ArrayList;


import com.example.myattendance.Dynamicdatabase;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myattendance.*;


public class MultiListView extends Activity implements OnClickListener,OnItemSelectedListener
{
	 ArrayList alist;
	 ListView mListView;
	 Button btn_selectDone,btn_back;
	 ArrayList<Product> mProducts;
	 MultiSelectionAdapter<Product> mAdapter;
	 Cursor cursor;
	 Dynamicdatabase dbhelper;
	 int bcid;
	 int month,year;
	 Spinner bspin;
	@Override
	public void onCreate(Bundle savedInstanceState)
	 {

	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.multilistview);
	       
	        Intent intp=this.getIntent();
	        bcid=intp.getExtras().getInt("bid");
	        month=intp.getExtras().getInt("mon");
	           bindComponents();
	           addListeners();
	}
	
   private void bindComponents() 
   {

	  // TODO Auto-generated method stub
         TextView tv=(TextView)findViewById(R.id.heading);
         tv.setText("Attendance List");
	     mListView = (ListView) findViewById(android.R.id.list);
	     btn_selectDone = (Button) findViewById(R.id.btn_add_save);
	    
	     
	     btn_back= (Button) findViewById(R.id.btn_back);
         btn_back.setOnClickListener(new View.OnClickListener()
         {			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				back();
			}
		});
	 }  

private void init()
	 {
	
	  // TODO Auto-generated method stub

	     mProducts = new ArrayList<Product>();
		 	try
		 	{
		 		 cursor =dbhelper.mark(bcid);
		 	
		 		startManagingCursor(cursor);
			 	if(cursor.moveToFirst())
			 	{
			 		do
			 		{	String srno=cursor.getString(0).toString();
			 		    String name=cursor.getString(1).toString();
			 			mProducts.add(new Product(srno,name));
			 	    
			 		}
			 		while(cursor.moveToNext());	
			    }	 	     
			 	
			 	   mAdapter = new MultiSelectionAdapter<Product>(this, mProducts);
			       mListView.setAdapter(mAdapter);
		 		dbhelper.close();
		 	}
	 	catch(Exception e)
	 	{
	 		AlertDialog al=new AlertDialog.Builder(this).create();
	 		al.setMessage(e.toString());
	 		al.show();	 		
	 	}	 
	 }
   private void addListeners() 
	{
	  // TODO Auto-generated method stub
      btn_selectDone.setOnClickListener(this);
	}
   
   public void onStart()
	{
		super.onStart();
		dbhelper=new Dynamicdatabase(this);
		init();
	}
   
   
	 @Override
	 public void onClick(View v) 
	 {
	  // TODO Auto-generated method stub
		 doneMultiselect();
		
	 }
	 
	 public void Addblocklist(String contentname, String num)
		{
			dbhelper = new Dynamicdatabase(this);
			try
			{		     
				dbhelper.updatePcount(num,bcid,month+1);
				dbhelper.close();
			}
			catch (Exception e) 
			{
				// TODO: handle exception
				AlertDialog.Builder al = new AlertDialog.Builder(this);
				al.setMessage(e.toString());
				al.show();

			}
		}
	 
	 public void back()
	 {   Intent backint=new Intent();
			setResult(RESULT_OK,backint);
			finish();
		
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		init();
	}




	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void  doneMultiselect() 
	{
		AlertDialog al=new AlertDialog.Builder(this).create();
		al.setTitle("Attendance");
		al.setMessage("Done ?");
		al.setButton(AlertDialog.BUTTON_NEUTRAL, "Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				multiSelect();
				updatecount();
			}

			public void updatecount() {
				// TODO Auto-generated method stub
			dbhelper.updatetotalcount(bcid, month+1);	
			}
		});
		dbhelper.close();
		
       al.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
       al.show();
	}
	
	
public void multiSelect()
{
	 Product pro=new Product(); 
	  if(mAdapter != null) 
	  {
	    ArrayList<Product> mArrayProducts = mAdapter.getCheckedItems();	  
		  
	   int count=mAdapter.getCount();
	   alist=mAdapter.getCheckedItemsList();
	   
	  for(int i=0;i<alist.size();i++)
	   {		  		   Product p=(Product)alist.get(i);
	   
	   //Toast.makeText(this, p.getNo(),Toast.LENGTH_LONG).show();
		   Addblocklist("name",p.getNo());
	   }

	  }
	    back();
}
@Override
protected void onStop() {
	// TODO Auto-generated method stub
	super.onStop();
	cursor.close();
}
	}
