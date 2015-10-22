package com.imudges.quality;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.imudges.quality.R;

public class ContentFragment extends Fragment {

	
	private TextView myMoney;
	private AppData appData;
	private LinearLayout layout;
	private DrawView view=null; 
	Context context=this.getActivity();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		layout=(LinearLayout)getActivity().findViewById(R.id.LinearLayout1);  
		return inflater.inflate(R.layout.layout_main, null);
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		initTitleMoney();
	    init2();
	}
	
	public void initTitleMoney(){
		SharedPreferences sharedPreferences= getActivity().getSharedPreferences("userConfig",getActivity().MODE_PRIVATE);
		int localTime=sharedPreferences.getInt("time", 1);
		int localMoney=sharedPreferences.getInt("money", 1000);
		AppData data=new AppData(getActivity());
		int AllUsedMoney=data.getUsedMoney1()+data.getUsedMoney2()+data.getUsedMoney3()+
				data.getUsedMoney4()+data.getUsedMoney5()+data.getUsedMoney6();
		
		TextView textView1 = (TextView) getView().findViewById(R.id.moneytext);  
	    textView1.setText(""+(localMoney/(localTime*7)-AllUsedMoney));  
	    textView1.setTextColor(Color.BLACK);
	    if((localMoney/(localTime*7)-AllUsedMoney)<=0){
	    	textView1.setTextColor(Color.RED);
	    }
	}
	
	public void init2(){
		appData=new AppData(getActivity());
		
		TextView text1 = (TextView) getView().findViewById(R.id.textview1);
		text1.setText(""+(appData.getUsedMoney1())+"/"+appData.getMoney1());
		TextView text2 = (TextView) getView().findViewById(R.id.textview2);
		text2.setText(""+(appData.getUsedMoney2())+"/"+appData.getMoney2());
		TextView text3 = (TextView) getView().findViewById(R.id.textview3);
		text3.setText(""+(appData.getUsedMoney3())+"/"+appData.getMoney3());
		TextView text4 = (TextView) getView().findViewById(R.id.textview4);
		text4.setText(""+(appData.getUsedMoney4())+"/"+appData.getMoney4());
		TextView text5 = (TextView) getView().findViewById(R.id.textview5);
		text5.setText(""+(appData.getUsedMoney5())+"/"+appData.getMoney5());
		TextView text6 = (TextView) getView().findViewById(R.id.textview6);
		text6.setText(""+(appData.getUsedMoney6())+"/"+appData.getMoney6());
		
		text1.setTextColor(Color.BLACK);
		text2.setTextColor(Color.BLACK);
		text3.setTextColor(Color.BLACK);
		text4.setTextColor(Color.BLACK);
		text5.setTextColor(Color.BLACK);
		text6.setTextColor(Color.BLACK);
		
		if(appData.getUsedMoney1()>appData.getMoney1()){
			text1.setTextColor(Color.RED);
		}
		if(appData.getUsedMoney2()>appData.getMoney2()){
			text2.setTextColor(Color.RED);
		}
		if(appData.getUsedMoney3()>appData.getMoney3()){
			text3.setTextColor(Color.RED);
		}
		if(appData.getUsedMoney4()>appData.getMoney4()){
			text4.setTextColor(Color.RED);
		}
		if(appData.getUsedMoney5()>appData.getMoney5()){
			text5.setTextColor(Color.RED);
		}
		if(appData.getUsedMoney6()>appData.getMoney6()){
			text6.setTextColor(Color.RED);
		}
		
		LinearLayout layout1=(LinearLayout)getView().findViewById(R.id.LinearLayout1);
		layout1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = LayoutInflater.from(getActivity()); 
				final View textEntryView = inflater.inflate(R.layout.dialog, null);  
				final EditText edtInput=(EditText)textEntryView.findViewById(R.id.myTimeText);
				new AlertDialog.Builder(getActivity())
					.setTitle("请输入饮食使用金额：")
					.setView(textEntryView)
					.setIcon(android.R.drawable.ic_dialog_info)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub 
							int data =0;
							if(edtInput!=null){
								String str1 = edtInput.getText().toString();
								if(str1!=null&&str1.length()>0){
									data = Integer.parseInt(str1);
								}
							}
							AppData appData1=new AppData(getActivity());
							appData1.setUsedMoney1(appData1.getUsedMoney1()+data);
							initTitleMoney();
							init2();
						}
					})
					.setNegativeButton("取消", null).show();
			}
		});
		
		LinearLayout layout2=(LinearLayout)getView().findViewById(R.id.LinearLayout2);
		layout2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = LayoutInflater.from(getActivity()); 
				final View textEntryView = inflater.inflate(R.layout.dialog, null);  
				final EditText edtInput=(EditText)textEntryView.findViewById(R.id.myTimeText);
				new AlertDialog.Builder(getActivity())
					.setTitle("请输入学习使用金额：")
					.setView(textEntryView)
					.setIcon(android.R.drawable.ic_dialog_info)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub 
							int data =0;
							if(edtInput!=null){
								String str2 = edtInput.getText().toString();
								if(str2!=null&&str2.length()>0){
									data = Integer.parseInt(str2);
								}
							}
							AppData appData2=new AppData(getActivity());
							appData2.setUsedMoney2(appData2.getUsedMoney2()+data);
							initTitleMoney();
							init2();
						}
					})
					.setNegativeButton("取消", null).show();
			}
		});
		
		
		LinearLayout layout3=(LinearLayout)getView().findViewById(R.id.LinearLayout3);
		layout3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = LayoutInflater.from(getActivity()); 
				final View textEntryView = inflater.inflate(R.layout.dialog, null);  
				final EditText edtInput=(EditText)textEntryView.findViewById(R.id.myTimeText);
				new AlertDialog.Builder(getActivity())
					.setTitle("请输入心愿单使用金额：")
					.setView(textEntryView)
					.setIcon(android.R.drawable.ic_dialog_info)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub 
							int data =0;
							if(edtInput!=null){
								String str1 = edtInput.getText().toString();
								if(str1!=null&&str1.length()>0){
									data = Integer.parseInt(str1);
								}
							}
							AppData appData1=new AppData(getActivity());
							appData1.setUsedMoney3(appData1.getUsedMoney3()+data);
							initTitleMoney();
							init2();
						}
					})
					.setNegativeButton("取消", null).show();
			}
		});
		
		
		LinearLayout layout4=(LinearLayout)getView().findViewById(R.id.LinearLayout4);
		layout4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = LayoutInflater.from(getActivity()); 
				final View textEntryView = inflater.inflate(R.layout.dialog, null);  
				final EditText edtInput=(EditText)textEntryView.findViewById(R.id.myTimeText);
				new AlertDialog.Builder(getActivity())
					.setTitle("请输入娱乐使用金额：")
					.setView(textEntryView)
					.setIcon(android.R.drawable.ic_dialog_info)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub 
							int data =0;
							if(edtInput!=null){
								String str1 = edtInput.getText().toString();
								if(str1!=null&&str1.length()>0){
									data = Integer.parseInt(str1);
								}
							}
							AppData appData1=new AppData(getActivity());
							appData1.setUsedMoney4(appData1.getUsedMoney4()+data);
							initTitleMoney();
							init2();
						}
					})
					.setNegativeButton("取消", null).show();
			}
		});
		
		
		LinearLayout layout5=(LinearLayout)getView().findViewById(R.id.LinearLayout5);
		layout5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = LayoutInflater.from(getActivity()); 
				final View textEntryView = inflater.inflate(R.layout.dialog, null);  
				final EditText edtInput=(EditText)textEntryView.findViewById(R.id.myTimeText);
				new AlertDialog.Builder(getActivity())
					.setTitle("请输入交通使用金额：")
					.setView(textEntryView)
					.setIcon(android.R.drawable.ic_dialog_info)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub 
							int data =0;
							if(edtInput!=null){
								String str1 = edtInput.getText().toString();
								if(str1!=null&&str1.length()>0){
									data = Integer.parseInt(str1);
								}
							}
							AppData appData1=new AppData(getActivity());
							appData1.setUsedMoney5(appData1.getUsedMoney5()+data);
							initTitleMoney();
							init2();
						}
					})
					.setNegativeButton("取消", null).show();
			}
		});
		
		LinearLayout layout6=(LinearLayout)getView().findViewById(R.id.LinearLayout6);
		layout6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = LayoutInflater.from(getActivity()); 
				final View textEntryView = inflater.inflate(R.layout.dialog, null);  
				final EditText edtInput=(EditText)textEntryView.findViewById(R.id.myTimeText);
				new AlertDialog.Builder(getActivity())
					.setTitle("请输入存款金额：")
					.setView(textEntryView)
					.setIcon(android.R.drawable.ic_dialog_info)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub 
							int data =0;
							if(edtInput!=null){
								String str1 = edtInput.getText().toString();
								if(str1!=null&&str1.length()>0){
									data = Integer.parseInt(str1);
								}
							}
							AppData appData1=new AppData(getActivity());
							appData1.setUsedMoney6(appData1.getUsedMoney6()+data);
							initTitleMoney();
							init2();
						}
					})
					.setNegativeButton("取消", null).show();
			}
		});
	}
	

	public void init(){
		appData=new AppData(getActivity());
		
        
		Toast.makeText(getActivity(), ""+appData.getValue1(),Toast.LENGTH_SHORT).show();
		if(view==null){
			view=new DrawView(getActivity());
		}
        view.setValue((appData.getMoney1()-appData.getUsedMoney1())*100/(appData.getMoney1()>0?appData.getMoney1():1),appData.getMoney1()-appData.getUsedMoney1());
        //通知view组件重绘  
        view.invalidate();
        Log.w("init", "diaoyong") ; 
        
        layout.invalidate();
        layout.addView(view);
        view.invalidate();
        
        LinearLayout layout2=(LinearLayout)getActivity().findViewById(R.id.LinearLayout2);  
        final DrawView view2=new DrawView(getActivity());  
        view2.setMinimumHeight(100);  
        view2.setMinimumWidth(100);  
        //通知view组件重绘   
        view2.setValue((appData.getMoney2()-appData.getUsedMoney2())*100/(appData.getMoney2()>0?appData.getMoney2():1),appData.getMoney2()-appData.getUsedMoney2());
        view2.invalidate();
        layout2.addView(view2); 

        
        LinearLayout layout3=(LinearLayout)getActivity().findViewById(R.id.LinearLayout3);  
        final DrawView view3=new DrawView(getActivity());  
        view3.setMinimumHeight(100);  
        view3.setMinimumWidth(100);  
        //通知view组件重绘   
        view3.setValue((appData.getMoney3()-appData.getUsedMoney3())*100/(appData.getMoney3()>0?appData.getMoney3():1),appData.getMoney3()-appData.getUsedMoney3());
        view3.invalidate();
        layout3.addView(view3); 
        
        LinearLayout layout4=(LinearLayout)getActivity().findViewById(R.id.LinearLayout4);  
        final DrawView view4=new DrawView(getActivity());  
        view4.setMinimumHeight(100);  
        view4.setMinimumWidth(100);  
        //通知view组件重绘   
        view4.setValue((appData.getMoney4()-appData.getUsedMoney4())*100/(appData.getMoney4()>0?appData.getMoney4():1),appData.getMoney4()-appData.getUsedMoney4());
        view4.invalidate();
        layout4.addView(view4); 
        
        LinearLayout layout5=(LinearLayout)getActivity().findViewById(R.id.LinearLayout5);  
        final DrawView view5=new DrawView(getActivity());  
        view5.setMinimumHeight(100);  
        view5.setMinimumWidth(100);  
        //通知view组件重绘   
        view5.setValue((appData.getMoney5()-appData.getUsedMoney5())*100/(appData.getMoney5()>0?appData.getMoney5():1),appData.getMoney5()-appData.getUsedMoney5());
        view5.invalidate();
        layout5.addView(view5); 
        
        LinearLayout layout6=(LinearLayout)getActivity().findViewById(R.id.LinearLayout6);  
        final DrawView view6=new DrawView(getActivity());  
        view6.setMinimumHeight(100);  
        view6.setMinimumWidth(100);  
        //通知view组件重绘   
        view6.setValue((appData.getMoney6()-appData.getUsedMoney6())*100/(appData.getMoney6()>0?appData.getMoney6():1),appData.getMoney6()-appData.getUsedMoney6());
        view6.invalidate();
        layout6.addView(view6); 
	}
	
}
