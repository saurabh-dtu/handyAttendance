package mypackage;

import android.widget.BaseAdapter;

import java.util.ArrayList;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.example.myattendance.*;

public class MultiSelectionAdapter<T> extends BaseAdapter{


	 Context mContext;
	 LayoutInflater mInflater;
	 ArrayList<T> mList;
	 SparseBooleanArray mSparseBooleanArray;

	 public MultiSelectionAdapter(Context context, ArrayList<T> list)
	 {

	// TODO Auto-generated constructor stub

	  this.mContext = context;
	  mInflater = LayoutInflater.from(mContext);
	  mSparseBooleanArray = new SparseBooleanArray();
	  mList = new ArrayList<T>();
	  this.mList = list;
	 }
	 

 public ArrayList<T> getCheckedItems()
  {
	  ArrayList<T> mTempArry = new ArrayList<T>();
	  for(int i=0;i<mList.size();i++) 
	  {
	   if(mSparseBooleanArray.get(i))
	   {
	    mTempArry.add(mList.get(i));

	   }

	  }
	  return mTempArry;
	 }
 
 public ArrayList getCheckedItemsList()
 {	  
	  //return mList;
		 
	 ArrayList mTempArry = new ArrayList();
	  for(int i=0;i<mList.size();i++) 
	  {
	   if(mSparseBooleanArray.get(i))
	   {
	    mTempArry.add(mList.get(i));
	   }

	  }
	  return mTempArry;
	 }

	 @Override
	 public int getCount() 
	 {
	  // TODO Auto-generated method stub
	  return mList.size();
	 }

	 @Override
	 public Object getItem(int position) 
	 {

	  // TODO Auto-generated method stub
	  return mList.get(position);

	 }


	 @Override
	 public long getItemId(int position)
	 {
	  // TODO Auto-generated method stub

	  return position;

	 }
	 @Override
	 public View getView(final int position, View convertView, ViewGroup parent) {

	  // TODO Auto-generated method stub

	  if(convertView == null) 
	  {
	   convertView = mInflater.inflate(R.layout.raw, null);

	  }

	  TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
	  

	  tvTitle.setText(mList.get(position).toString());
	  
	  
	  CheckBox mCheckBox = (CheckBox) convertView.findViewById(R.id.chkEnable);

	  mCheckBox.setTag(position);

	  mCheckBox.setChecked(mSparseBooleanArray.get(position));


	  mCheckBox.setOnCheckedChangeListener(mCheckedChangeListener);
	  return convertView;


	 }


	 OnCheckedChangeListener mCheckedChangeListener = new OnCheckedChangeListener() {

	  @Override
	  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
	  {

	   // TODO Auto-generated method stub

	   mSparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);

	  }

	 };

	}

