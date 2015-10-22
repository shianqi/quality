package com.imudges.quality;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import com.imudges.quality.ContentFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.os.Bundle;
import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends SlidingFragmentActivity {

	private Fragment mContent;
	private TextView myTimeTextView;
	private TextView myMoneyTextView;
	private TextView myMoney;
	private AppData appData;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		setTitle("�ʻ�"); 
		
		// check if the content frame contains the menu frame
		//����Ƿ�����˵���
		
		appData=new AppData(getApplicationContext());
		if (findViewById(R.id.menu_frame) == null) {
			setBehindContentView(R.layout.menu_frame);
			getSlidingMenu().setSlidingEnabled(true);
			getSlidingMenu()
					.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		} else {
			// add a dummy view
			//���һ������view
			View v = new View(this);
			setBehindContentView(v);
			getSlidingMenu().setSlidingEnabled(false);
			getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
		// ���������view
		if (savedInstanceState != null) {
			mContent = getSupportFragmentManager().getFragment(
					savedInstanceState, "mContent");
		}
		
		if (mContent == null) {
			mContent = new ContentFragment();
		}
		getSupportFragmentManager().beginTransaction()
			.replace(R.id.content_frame, mContent).commit();
		
		//���ú���view
		getSupportFragmentManager().beginTransaction()
			.replace(R.id.menu_frame, new MenuFragment()).commit();
		
		//�Զ���slidingmenu
		SlidingMenu sm = getSlidingMenu();
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeEnabled(false);
		sm.setBehindScrollScale(0.25f);
		sm.setFadeDegree(0.25f);

		//���ò˵�Ļ������ͼƬ
		sm.setBackgroundResource(R.drawable.menu_back);
		
		//���ò˵���ť�Ķ���Ч��
		sm.setBehindCanvasTransformer(new SlidingMenu.CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				float scale = (float) (percentOpen * 0.25 + 0.75);
				canvas.scale(scale, scale, -canvas.getWidth() / 2,
						canvas.getHeight() / 2);
			}
		});
	}
	
	
	
	public void OnClickButton01(View v){  
		mContent = new ContentFragment();
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.content_frame, mContent).commit();
		getSlidingMenu().showContent();
    }
	
	public void OnClickButton02(View v){  
		mContent = new heartFragment();
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.content_frame, mContent).commit();
		getSlidingMenu().showContent();
    }
	
	public void OnClickButton03(View v){  
		mContent = new budgetFragment();
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.content_frame, mContent).commit();
		getSlidingMenu().showContent();
    }
	
	public void setTime(View v){ 
		LayoutInflater inflater = getLayoutInflater();
		final View layout = inflater.inflate(R.layout.dialog,(ViewGroup) findViewById(R.id.dialog));
		new AlertDialog.Builder(this)
			.setTitle("������Ԥ������")
			.setIcon(android.R.drawable.ic_dialog_info)
			.setView(layout)
			.setPositiveButton("ȷ��", new OnClickListener() {
				
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
						myTimeTextView.setText("Ԥ�����ڣ� "+str1+"��");
						SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
						SharedPreferences.Editor editor=sharedPreferences.edit();
						editor.putInt("time", Integer.parseInt(str1));
						editor.commit();
					}
				}
			})
			.setNegativeButton("ȡ��", null).show();
    }
	
	public void  showEmpty(){
		Toast.makeText(this, "���벻��Ϊ��,����û���޸�",  Toast.LENGTH_SHORT).show();
	}
	
	public void  showZero(){
		Toast.makeText(this, "���벻��Ϊ0",  Toast.LENGTH_SHORT).show();
	}
	
	public void setMoney(View v){
		LayoutInflater inflater = getLayoutInflater();
		final View layout = inflater.inflate(R.layout.dialog,(ViewGroup) findViewById(R.id.dialog));
		new AlertDialog.Builder(this)
				.setTitle("�������֧����")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setView(layout)
				.setPositiveButton("ȷ��", new OnClickListener() {
					
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
							myMoneyTextView.setText("��֧���"+str1+"Ԫ");
							SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
							SharedPreferences.Editor editor=sharedPreferences.edit();
							editor.putInt("money", Integer.parseInt(str1));
							editor.commit();
						}
					}
				})
				.setNegativeButton("ȡ��", null).show();
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
		Toast.makeText(this, "��ʹ�ý��������",  Toast.LENGTH_SHORT).show();
	}
	
}
