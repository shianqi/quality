package com.imudges.quality;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.R.integer;
import android.R.string;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.AvoidXfermode.Mode;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.provider.ContactsContract.Contacts.Data;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.imudges.quality.R;

public class heartFragment extends Fragment {

	SQLiteDatabase db;
	ContentValues values;
	int []idList=new int[20000];
	SimpleAdapter listItemAdapter;
	ListView list;
	TextView heartMoney;
	ArrayList<HashMap<String, Object>> listItem;
	int allMoney=0;
	
	public static final String TABLENAME="itemtb";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.heart_fragment, null);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		
		list = (ListView)getActivity().findViewById(R.id.ListView01);
		listItem = new ArrayList<HashMap<String, Object>>();
		heartMoney=(TextView)getActivity().findViewById(R.id.heartMoney);
		
		//SQLite 的使用
		db=getActivity().openOrCreateDatabase("quality.db", getActivity().MODE_PRIVATE, null);
		db.execSQL("create table if not exists itemtb("
				+ "_id integer primary key autoincrement,"
				+ "title text not null,"
				+ "description text,"
				+ "time text not null , "
				+ "money int not null)");
		
		getItem();
		
		for(int i=0;i<2;i++){
			Log.i("1", ""+idList[i]);
		}
		
        listItemAdapter = new SimpleAdapter(getActivity(),listItem,//数据源 
                R.layout.list_items,//ListItem的XML实现
                //动态数组与ImageItem对应的子项        
                new String[] {"ItemImage","ItemTitle", "ItemText","ItemText2","ItemText3","ItemText4","ItemText5"}, 
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.ItemImage,R.id.ItemTitle,R.id.ItemText,R.id.ItemText2,R.id.ItemText3,R.id.ItemText4,R.id.ItemText5}
            );
        
        list.setAdapter(listItemAdapter);
        
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                Toast.makeText(getActivity(), "hello"+arg2,  Toast.LENGTH_SHORT).show();
            }
        });
        
        list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				final int ItemNumber=arg2;
				new AlertDialog.Builder(getActivity())
					.setTitle("操作：")
					.setPositiveButton("取消", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							getItem();
							// TODO Auto-generated method stub
							//Toast.makeText(getActivity(), "取消"+ItemNumber,  Toast.LENGTH_SHORT).show();
						}
					})
					.setNegativeButton("修改", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							LayoutInflater inflater = LayoutInflater.from(getActivity()); 
							
							
							final View textEntryView = inflater.inflate(R.layout.heart_add_dialog, null);  
							final EditText edtInput1=(EditText)textEntryView.findViewById(R.id.heart_add_dialog_title);
							final EditText edtInput2=(EditText)textEntryView.findViewById(R.id.heart_add_dialog_description);
							//final EditText edtInput3=(EditText)textEntryView.findViewById(R.id.heart_add_dialog_time);
							final DatePicker datePicker=(DatePicker)textEntryView.findViewById(R.id.datePacker);
							final EditText edtInput4=(EditText)textEntryView.findViewById(R.id.heart_add_dialog_money);
							//edtInput1.setText("123");
							
							
							new AlertDialog.Builder(getActivity())
								.setTitle("请修改心愿单：")
								.setView(textEntryView)
								.setIcon(android.R.drawable.ic_dialog_info)
								.setPositiveButton("确定", new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface arg0, int arg1) {
										// TODO Auto-generated method stub 
										values =new ContentValues();
										values.put("title", edtInput1.getText().toString());
										values.put("description", edtInput2.getText().toString());
										values.put("time", datePicker.getYear()+"-"+datePicker.getMonth()+"-"+datePicker.getDayOfMonth());
										values.put("money", edtInput4.getText().toString());
//										db.insert(TABLENAME, null, values);
//										values.clear();
//										
//										getItem();
//										listItemAdapter.notifyDataSetChanged();		
										
										db.update(TABLENAME, values, "_id like "+idList[ItemNumber], null);
										getItem();
										//heartMoney.setText(""+allMoney);
										listItemAdapter.notifyDataSetChanged();
									}
								})
								.setNegativeButton("取消", null).show();	
							//Toast.makeText(getActivity(), "修改"+ItemNumber,  Toast.LENGTH_SHORT).show();
						}
					})
					.setNeutralButton("删除", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							
							//Toast.makeText(getActivity(), "删除"+ItemNumber,  Toast.LENGTH_SHORT).show();
							db.delete(TABLENAME, "rowid like "+idList[ItemNumber], null);
							
							getItem();
							//heartMoney.setText(""+allMoney);
							listItemAdapter.notifyDataSetChanged();
						}
					})
					.show();
				
				//Toast.makeText(getActivity(), "长按"+arg2,  Toast.LENGTH_SHORT).show();
				// TODO Auto-generated method stub
				return true;
			}
		});
        
        setHeartAddButton();
	}
	
	public void getItem(){
		allMoney=0;
		listItem.clear();
		Cursor c = db.query(TABLENAME,null,"_id>?", new String[]{"0"},null,null,"title");
		if(c!=null){
			String []columnsStrings=c.getColumnNames();
			int i=0;
			
			
			//总金额
			while(c.moveToNext()){
				HashMap<String, Object> map = new HashMap<String, Object>();
	            map.put("ItemImage", R.drawable.img_portrait);//图像资源的ID
	            map.put("ItemTitle", c.getString(c.getColumnIndex("title")));
	            map.put("ItemText", c.getString(c.getColumnIndex("description")));
	            map.put("ItemText2", "时间："+c.getString(c.getColumnIndex("time")));
	            
	            map.put("ItemText3", "金额："+c.getString(c.getColumnIndex("money"))+"元");
	            
	            AppData appData=new AppData(getActivity());
	            map.put("ItemText4", "目标金额："+(appData.getMoney3()-appData.getUsedMoney3())+
	            		"/"+c.getString(c.getColumnIndex("money")));
	            
	            
	            
	            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            Date date = null;
	            try {
					date=sDateFormat.parse(c.getString(c.getColumnIndex("time")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            Date dateBegin=new java.util.Date();
	            String date23 = sDateFormat.format(dateBegin);
	            long betweenTime = date.getTime() - dateBegin.getTime();
	            int days = (int) (betweenTime / 1000 / 60 / 60 / 24);
	            
	            String dateString=sDateFormat.format(date);
	            
	            map.put("ItemText5", "剩余时间："+(days>=0?days+"天":"心愿已过期"));
	            
	            listItem.add(map);
	            
	            idList[i]=c.getInt(c.getColumnIndex("_id"));
	            allMoney+=c.getInt(c.getColumnIndex("money"));
	            i++;
	            
				for(String columnName:columnsStrings){
					Log.i("info", c.getString(c.getColumnIndex(columnName)));
				}
			}
			heartMoney.setText(""+allMoney);
		}
		
	}
	
	public void setHeartAddButton(){
		Button button=(Button)getView().findViewById(R.id.addList);
        button.setOnClickListener(new OnClickListener() {
        	
			@Override
			public void onClick(View arg0) { 
				
				
				LayoutInflater inflater = LayoutInflater.from(getActivity()); 
				final View textEntryView = inflater.inflate(R.layout.heart_add_dialog, null);  
				final EditText edtInput1=(EditText)textEntryView.findViewById(R.id.heart_add_dialog_title);
				final EditText edtInput2=(EditText)textEntryView.findViewById(R.id.heart_add_dialog_description);
				//final EditText edtInput3=(EditText)textEntryView.findViewById(R.id.heart_add_dialog_time);
				final DatePicker datePicker=(DatePicker)textEntryView.findViewById(R.id.datePacker);
				
				final EditText edtInput4=(EditText)textEntryView.findViewById(R.id.heart_add_dialog_money);
				new AlertDialog.Builder(getActivity())
					.setTitle("请输入心愿单：")
					.setView(textEntryView)
					.setIcon(android.R.drawable.ic_dialog_info)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub 
							values =new ContentValues();
							values.put("title", edtInput1.getText().toString());
							values.put("description", edtInput2.getText().toString());
							values.put("time", datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth());
							values.put("money", edtInput4.getText().toString());
							Log.i("XXCVXC", ""+datePicker.getMonth());
							db.insert(TABLENAME, null, values);
							values.clear();
							
							getItem();
							heartMoney.setText(""+allMoney);
							listItemAdapter.notifyDataSetChanged();			
						}
					})
					.setNegativeButton("取消", null).show();		
				}
		});
	}
	
}
