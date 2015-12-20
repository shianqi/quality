package com.imudges.quality;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends SlidingFragmentActivity {

	private Fragment mContent;
	private TextView myTimeTextView;
	private TextView myMoneyTextView;
	private AppData appData;
	private SlidingMenu sm;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		setTitle("质活"); 
		
		// check if the content frame contains the menu frame
		//检查是否包含菜单栏
		
		appData=new AppData(getApplicationContext());
		if (findViewById(R.id.menu_frame) == null) {
			setBehindContentView(R.layout.menu_frame);
			getSlidingMenu().setSlidingEnabled(true);
			getSlidingMenu()
					.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		} else {
			// add a dummy view
			//添加一个虚拟view
			View v = new View(this);
			setBehindContentView(v);
			getSlidingMenu().setSlidingEnabled(false);
			getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
		// 设置上面的view
		if (savedInstanceState != null) {
			mContent = getSupportFragmentManager().getFragment(
					savedInstanceState, "mContent");
		}
		
		if (mContent == null) {
			mContent = new ContentFragment();
		}
		getSupportFragmentManager().beginTransaction()
			.replace(R.id.content_frame, mContent).commit();
		
		//设置后面view
		getSupportFragmentManager().beginTransaction()
			.replace(R.id.menu_frame, new MenuFragment()).commit();
		
		//自定义slidingmenu
		sm = getSlidingMenu();
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeEnabled(false);
		sm.setBehindScrollScale(0.25f);
		sm.setFadeDegree(0.25f);

		//设置菜单幕布背景图片
		sm.setBackgroundResource(R.drawable.menu_back);
		
		//设置菜单按钮的动画效果
		sm.setBehindCanvasTransformer(new SlidingMenu.CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				float scale = (float) (percentOpen * 0.25 + 0.75);
				canvas.scale(scale, scale, -canvas.getWidth() / 2,
						canvas.getHeight() / 2);
			}
		});
	}
	
	
	/*
	 *跳转到主页面，并将Fragment切换到第一页
	 */
	public void OnClickButton01(View v){
		getSlidingMenu().showContent();
		mContent = new ContentFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, mContent).commit();
    }
	
	public void OnClickButton02(View v){
		getSlidingMenu().showContent();
		mContent = new heartFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, mContent).commit();
    }
	
	public void OnClickButton03(View v){
		getSlidingMenu().showContent();
		mContent = new budgetFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, mContent).commit();
    }
	
	public void setTime(View v){ 
		LayoutInflater inflater = getLayoutInflater();
		final View layout = inflater.inflate(R.layout.dialog,(ViewGroup) findViewById(R.id.dialog));
		new AlertDialog.Builder(this)
			.setTitle("请输入预算周期")
			.setIcon(android.R.drawable.ic_dialog_info)
			.setView(layout)
			.setPositiveButton("确定", new OnClickListener(){
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					myTimeTextView=(TextView)findViewById(R.id.textTime);
					EditText editText1 =(EditText) layout.findViewById(R.id.myTimeText);  
					String str1="";
					if(editText1.length()==0){
						showEmpty();
					}else if(editText1.getText().toString().equals("0")){
						showZero();
					}else{
						str1=editText1.getText().toString();
					}
						
					if(str1==null||str1.equals("")){
						
					}else{
						myTimeTextView.setText("预算周期： "+str1+"周");
						SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
						SharedPreferences.Editor editor=sharedPreferences.edit();
						editor.putInt("time", Integer.parseInt(str1));
						editor.commit();
					}
				}
			})
			.setNegativeButton("取消", null).show();
    }

	//判断是否为空
	public void  showEmpty(){
		Toast.makeText(this, "输入不能为空,数据没有修改",  Toast.LENGTH_SHORT).show();
	}

	//判断为零
	public void  showZero(){
		Toast.makeText(this, "输入不能为0",  Toast.LENGTH_SHORT).show();
	}



	public void setMoney(View v){
		LayoutInflater inflater = getLayoutInflater();
		final View layout = inflater.inflate(R.layout.dialog,(ViewGroup) findViewById(R.id.dialog));
		new AlertDialog.Builder(this)
				.setTitle("请输入可支配金额")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setView(layout)
				.setPositiveButton("确定", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						myMoneyTextView=(TextView)findViewById(R.id.textMoney);
						EditText editText2 =(EditText) layout.findViewById(R.id.myTimeText);  
						String str1=""; 
						if(editText2.length()==0){
							showEmpty();
						}else if(editText2.getText().toString().equals("0")){
							showZero();
						}else{
							str1=editText2.getText().toString();
						}
							
						if(str1==null||str1.equals("")){
							
						}else{
							str1=editText2.getText().toString();
							myMoneyTextView.setText("可支配金额："+str1+"元");
							SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
							SharedPreferences.Editor editor=sharedPreferences.edit();
							editor.putInt("money", Integer.parseInt(str1));
							editor.commit();
						}
					}
				})
				.setNegativeButton("取消", null).show();
    }
	
	public void recoverUsedMoney(View v){
		SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
		SharedPreferences.Editor editor=sharedPreferences.edit();
		editor.putInt("usedMoney1", 0);
		editor.putInt("usedMoney2", 0);
		editor.putInt("usedMoney3", 0);
		editor.putInt("usedMoney4", 0);
		editor.putInt("usedMoney5", 0);
		editor.putInt("usedMoney6", 0);
		editor.commit();
		Toast.makeText(this, "已使用金额已重置",  Toast.LENGTH_SHORT).show();
	}
	
}
