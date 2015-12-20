package com.imudges.quality;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class budgetFragment extends Fragment {

	
	
	
	private TextView myTimeTextView;
	private TextView myMoneyTextView;
	private SeekBar playseekBar1;
	private SeekBar playseekBar2;
	private SeekBar playseekBar3;
	private SeekBar playseekBar4;
	private SeekBar playseekBar5;
	private SeekBar playseekBar6;
	
	private TextView textView1;
	private TextView textView2;
	private TextView textView3;
	private TextView textView4;
	private TextView textView5;
	private TextView textView6;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.setting, null);
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		SharedPreferences sharedPreferences= getActivity().getSharedPreferences("userConfig",getActivity().MODE_PRIVATE);
		
		int localTime=sharedPreferences.getInt("time", 1);
		int localMoney=sharedPreferences.getInt("money", 1000);
		
		myTimeTextView=(TextView)getView().findViewById(R.id.textTime);
		myTimeTextView.setText("预算周期： "+localTime+"周");
		
		myMoneyTextView=(TextView)getView().findViewById(R.id.textMoney);
		myMoneyTextView.setText("可支配金额："+localMoney+"元");
		
		playseekBar1=(SeekBar)getView().findViewById(R.id.seekBar1);
		playseekBar2=(SeekBar)getView().findViewById(R.id.seekBar2);
		playseekBar3=(SeekBar)getView().findViewById(R.id.seekBar3);
		playseekBar4=(SeekBar)getView().findViewById(R.id.seekBar4);
		playseekBar5=(SeekBar)getView().findViewById(R.id.seekBar5);
		playseekBar6=(SeekBar)getView().findViewById(R.id.seekBar6);
		
		textView1=(TextView)getView().findViewById(R.id.text1);
		textView2=(TextView)getView().findViewById(R.id.text2);
		textView3=(TextView)getView().findViewById(R.id.text3);
		textView4=(TextView)getView().findViewById(R.id.text4);
		textView5=(TextView)getView().findViewById(R.id.text5);
		textView6=(TextView)getView().findViewById(R.id.text6);
				
		int SeekBar1=sharedPreferences.getInt("SeekBar1", 50);
		int SeekBar2=sharedPreferences.getInt("SeekBar2", 50);
		int SeekBar3=sharedPreferences.getInt("SeekBar3", 50);
		int SeekBar4=sharedPreferences.getInt("SeekBar4", 50);
		int SeekBar5=sharedPreferences.getInt("SeekBar5", 50);
		int SeekBar6=sharedPreferences.getInt("SeekBar6", 50);
		
		playseekBar1.setProgress(SeekBar1);
		playseekBar2.setProgress(SeekBar2);
		playseekBar3.setProgress(SeekBar3);
		playseekBar4.setProgress(SeekBar4);
		playseekBar5.setProgress(SeekBar5);
		playseekBar6.setProgress(SeekBar6);
		
		textView1.setText("饮食:"+SeekBar1);
		textView2.setText("学习:"+SeekBar2);
		textView3.setText("心愿单:"+SeekBar3);
		textView4.setText("娱乐:"+SeekBar4);
		textView5.setText("交通:"+SeekBar5);
		textView6.setText("存款:"+SeekBar6);
		
		playseekBar1.setOnSeekBarChangeListener(new SeekBarListener1());
		playseekBar2.setOnSeekBarChangeListener(new SeekBarListener2());
		playseekBar3.setOnSeekBarChangeListener(new SeekBarListener3());
		playseekBar4.setOnSeekBarChangeListener(new SeekBarListener4());
		playseekBar5.setOnSeekBarChangeListener(new SeekBarListener5());
		playseekBar6.setOnSeekBarChangeListener(new SeekBarListener6());
		
		final ToggleButton toggleButton=(ToggleButton)getView().findViewById(R.id.toggleButton1);
		toggleButton.setChecked(true);
		playseekBar1.setEnabled(false);
		playseekBar2.setEnabled(false);
		playseekBar3.setEnabled(false);
		playseekBar4.setEnabled(false);
		playseekBar5.setEnabled(false);
		playseekBar6.setEnabled(false);
		toggleButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!toggleButton.isChecked()){
					playseekBar1.setEnabled(true);
					playseekBar2.setEnabled(true);
					playseekBar3.setEnabled(true);
					playseekBar4.setEnabled(true);
					playseekBar5.setEnabled(true);
					playseekBar6.setEnabled(true);
				}else {
					playseekBar1.setEnabled(false);
					playseekBar2.setEnabled(false);
					playseekBar3.setEnabled(false);
					playseekBar4.setEnabled(false);
					playseekBar5.setEnabled(false);
					playseekBar6.setEnabled(false);
				}
			}
		});
	}
	 private class SeekBarListener1 implements SeekBar.OnSeekBarChangeListener
	    {
		 SharedPreferences sharedPreferences= getActivity().getSharedPreferences("userConfig",getActivity().MODE_PRIVATE);
		 SharedPreferences.Editor editor=sharedPreferences.edit();
	        @Override
	        public void onProgressChanged(SeekBar seekBar, int progress,
	                boolean fromUser) {
	            // TODO Auto-generated method stub
	        	editor.putInt("SeekBar1", playseekBar1.getProgress());
	        	editor.commit(); 
	        	textView1.setText("饮食:"+ playseekBar1.getProgress());
	        }

	        @Override
	        public void onStartTrackingTouch(SeekBar seekBar) {
	            // TODO Auto-generated method stub
	        	editor.putInt("SeekBar1", playseekBar1.getProgress());
	        	editor.commit();
	        	textView1.setText("饮食:"+ playseekBar1.getProgress());
	        }

	        @Override
	        public void onStopTrackingTouch(SeekBar seekBar) {
	            // TODO Auto-generated method stub
	        	editor.putInt("SeekBar1", playseekBar1.getProgress());
	        	editor.commit();
	        	textView1.setText("饮食:"+ playseekBar1.getProgress());
	    }
	 }
	 private class SeekBarListener2 implements SeekBar.OnSeekBarChangeListener
	    {
		 SharedPreferences sharedPreferences= getActivity().getSharedPreferences("userConfig",getActivity().MODE_PRIVATE);
		 SharedPreferences.Editor editor=sharedPreferences.edit();
	        @Override
	        public void onProgressChanged(SeekBar seekBar, int progress,
	                boolean fromUser) {
	            // TODO Auto-generated method stub
	        	editor.putInt("SeekBar2", playseekBar2.getProgress());
	        	editor.commit();
	        	textView2.setText("学习:"+ playseekBar2.getProgress());
	        }

	        @Override
	        public void onStartTrackingTouch(SeekBar seekBar) {
	            // TODO Auto-generated method stub
	        	editor.putInt("SeekBar2", playseekBar2.getProgress());
	        	editor.commit();
	        	textView2.setText("学习:"+ playseekBar2.getProgress());
	        }

	        @Override
	        public void onStopTrackingTouch(SeekBar seekBar) {
	            // TODO Auto-generated method stub
	        	editor.putInt("SeekBar2", playseekBar2.getProgress());
	        	editor.commit();
	        	textView2.setText("学习:"+ playseekBar2.getProgress());
	        }
	       
	    }
	 
	 private class SeekBarListener3 implements SeekBar.OnSeekBarChangeListener
	    {
		 SharedPreferences sharedPreferences= getActivity().getSharedPreferences("userConfig",getActivity().MODE_PRIVATE);
		 SharedPreferences.Editor editor=sharedPreferences.edit();
	        @Override
	        public void onProgressChanged(SeekBar seekBar, int progress,
	                boolean fromUser) {
	            // TODO Auto-generated method stub
	        	editor.putInt("SeekBar3", playseekBar3.getProgress());
	        	editor.commit();
	        	textView3.setText("心愿单:"+ playseekBar3.getProgress());
	        }

	        @Override
	        public void onStartTrackingTouch(SeekBar seekBar) {
	            // TODO Auto-generated method stub
	        	editor.putInt("SeekBar3", playseekBar3.getProgress());
	        	editor.commit();
	        	textView3.setText("心愿单:"+ playseekBar3.getProgress());
	        }

	        @Override
	        public void onStopTrackingTouch(SeekBar seekBar) {
	            // TODO Auto-generated method stub
	        	editor.putInt("SeekBar3", playseekBar3.getProgress());
	        	editor.commit();
	        	textView3.setText("心愿单:"+ playseekBar3.getProgress());
	        }
	       
	    }
	 
	 private class SeekBarListener4 implements SeekBar.OnSeekBarChangeListener
	    {
		 SharedPreferences sharedPreferences= getActivity().getSharedPreferences("userConfig",getActivity().MODE_PRIVATE);
		 SharedPreferences.Editor editor=sharedPreferences.edit();
	        @Override
	        public void onProgressChanged(SeekBar seekBar, int progress,
	                boolean fromUser) {
	            // TODO Auto-generated method stub
	        	editor.putInt("SeekBar4", playseekBar4.getProgress());
	        	editor.commit();
	        	textView4.setText("娱乐:"+ playseekBar4.getProgress());
	        }

	        @Override
	        public void onStartTrackingTouch(SeekBar seekBar) {
	            // TODO Auto-generated method stub
	        	editor.putInt("SeekBar4", playseekBar4.getProgress());
	        	editor.commit();
	        	textView4.setText("娱乐:"+ playseekBar4.getProgress());
	        }

	        @Override
	        public void onStopTrackingTouch(SeekBar seekBar) {
	            // TODO Auto-generated method stub
	        	editor.putInt("SeekBar4", playseekBar4.getProgress());
	        	editor.commit();
	        	textView4.setText("娱乐:"+ playseekBar4.getProgress());
	        }
	       
	    }
	 
	 private class SeekBarListener5 implements SeekBar.OnSeekBarChangeListener
	    {
		 SharedPreferences sharedPreferences= getActivity().getSharedPreferences("userConfig",getActivity().MODE_PRIVATE);
		 SharedPreferences.Editor editor=sharedPreferences.edit();
	        @Override
	        public void onProgressChanged(SeekBar seekBar, int progress,
	                boolean fromUser) {
	            // TODO Auto-generated method stub
	        	editor.putInt("SeekBar5", playseekBar5.getProgress());
	        	editor.commit();
	        	textView5.setText("交通:"+ playseekBar5.getProgress());
	        }

	        @Override
	        public void onStartTrackingTouch(SeekBar seekBar) {
	            // TODO Auto-generated method stub
	        	editor.putInt("SeekBar5", playseekBar5.getProgress());
	        	editor.commit();
	        	textView5.setText("交通:"+ playseekBar5.getProgress());
	        }

	        @Override
	        public void onStopTrackingTouch(SeekBar seekBar) {
	            // TODO Auto-generated method stub
	        	editor.putInt("SeekBar5", playseekBar5.getProgress());
	        	editor.commit();
	        	textView5.setText("交通:"+ playseekBar5.getProgress());
	        }
	       
	    }
	 
	 private class SeekBarListener6 implements SeekBar.OnSeekBarChangeListener
	    {
		 SharedPreferences sharedPreferences= getActivity().getSharedPreferences("userConfig",getActivity().MODE_PRIVATE);
		 SharedPreferences.Editor editor=sharedPreferences.edit();
	        @Override
	        public void onProgressChanged(SeekBar seekBar, int progress,
	                boolean fromUser) {
	            // TODO Auto-generated method stub
	        	editor.putInt("SeekBar6", playseekBar6.getProgress());
	        	editor.commit();
	        	textView6.setText("储蓄:"+ playseekBar6.getProgress());
	        }

	        @Override
	        public void onStartTrackingTouch(SeekBar seekBar) {
	            // TODO Auto-generated method stub
	        	editor.putInt("SeekBar6", playseekBar6.getProgress());
	        	editor.commit();
	        	textView6.setText("储蓄:"+ playseekBar6.getProgress());
	        }

	        @Override
	        public void onStopTrackingTouch(SeekBar seekBar) {
	            // TODO Auto-generated method stub
	        	editor.putInt("SeekBar6", playseekBar6.getProgress());
	        	editor.commit();
	        	textView6.setText("储蓄:"+ playseekBar6.getProgress());
	        }
	    }
}
