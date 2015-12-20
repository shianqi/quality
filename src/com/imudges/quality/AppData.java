package com.imudges.quality;

import android.content.Context;
import android.content.SharedPreferences;

public class AppData {
	private int usedMoney1;
	private int usedMoney2;
	private int usedMoney3;
	private int usedMoney4;
	private int usedMoney5;
	private int usedMoney6;
	
	private int money1;
	private int money2;
	private int money3;
	private int money4;
	private int money5;
	private int money6;
	
	private int value1;
	private int value2;
	private int value3;
	private int value4;
	private int value5;
	private int value6;
	
	private int dayMoney;
	private int allMoney;
	private int allDay;
	private int allValue;
	
	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;
	
	public void  initializeData(){
		editor=sharedPreferences.edit();
		
		value1=sharedPreferences.getInt("SeekBar1", 1);
		value2=sharedPreferences.getInt("SeekBar2", 1);
		value3=sharedPreferences.getInt("SeekBar3", 1);
		value4=sharedPreferences.getInt("SeekBar4", 1);
		value5=sharedPreferences.getInt("SeekBar5", 1);
		value6=sharedPreferences.getInt("SeekBar6", 1);
		
		allMoney=sharedPreferences.getInt("money", 1000);
		allDay=sharedPreferences.getInt("time", 1);
		
		dayMoney=(allMoney/allDay)/7;
		allValue=value1+value2+value3+value4+value5+value6;
	}
	
	public AppData(Context context){
		sharedPreferences= context.getSharedPreferences("userConfig",context.MODE_PRIVATE);
	}
	public int getAllDay() {
		initializeData();
		return allDay;
	}
	public void setAllDay(int allDay) {
		this.allDay = allDay;
	}
	public int getMoney1() {
		initializeData();
		money1=(dayMoney*value1)/allValue;
		return money1;
	}
	public void setMoney1(int money1) {
		this.money1 = money1;
	}
	public int getMoney2() {
		initializeData();
		money2=(dayMoney*value2)/allValue;
		return money2;
	}
	public void setMoney2(int money2) {
		this.money2 = money2;
	}
	public int getMoney3() {
		initializeData();
		money3=(dayMoney*value3)/allValue;
		return money3;
	}
	public void setMoney3(int money3) {
		this.money3 = money3;
	}
	public int getMoney4() {
		initializeData();
		money4=(dayMoney*value4)/allValue;
		return money4;
	}
	public void setMoney4(int money4) {
		this.money4 = money4;
	}
	public int getMoney5() {
		initializeData();
		money5=(dayMoney*value5)/allValue;
		return money5;
	}
	public void setMoney5(int money5) {
		this.money5 = money5;
	}
	public int getMoney6() {
		initializeData();
		money6=(dayMoney*value6)/allValue;
		return money6;
	}
	public void setMoney6(int money6) {
		this.money6 = money6;
	}
	public int getValue1() {
		initializeData();
		return value1;
	}
	public void setValue1(int value1) {
		this.value1 = value1;
	}
	public int getValue2() {
		initializeData();
		return value2;
	}
	public void setValue2(int value2) {
		this.value2 = value2;
	}
	public int getValue3() {
		initializeData();
		return value3;
	}
	public void setValue3(int value3) {
		this.value3 = value3;
	}
	public int getValue4() {
		initializeData();
		return value4;
	}
	public void setValue4(int value4) {
		this.value4 = value4;
	}
	public int getValue5() {
		initializeData();
		return value5;
	}
	public void setValue5(int value5) {
		this.value5 = value5;
	}
	public int getValue6() {
		initializeData();
		return value6;
	}
	public void setValue6(int value6) {
		this.value6 = value6;
	}
	public int getDayMoney() {
		initializeData();
		return dayMoney;
	}
	public void setDayMoney(int dayMoney) {
		this.dayMoney = dayMoney;
	}
	public int getAllMoney() {
		initializeData();
		return allMoney;
	}
	public void setAllMoney(int allMoney) {
		this.allMoney = allMoney;
	}
	
	
	
	public int getUsedMoney1() {
		usedMoney1=sharedPreferences.getInt("usedMoney1", 0);
		return usedMoney1;
	}

	public void setUsedMoney1(int usedMoney1) {
		initializeData();
		editor.putInt("usedMoney1", usedMoney1);
		editor.commit();
		this.usedMoney1 = usedMoney1;
	}

	public int getUsedMoney2() {
		usedMoney2=sharedPreferences.getInt("usedMoney2", 0);
		return usedMoney2;
	}

	public void setUsedMoney2(int usedMoney2) {
		initializeData();
		editor.putInt("usedMoney2", usedMoney2);
		editor.commit();
		this.usedMoney2 = usedMoney2;
	}

	public int getUsedMoney3() {
		usedMoney3=sharedPreferences.getInt("usedMoney3", 0);
		return usedMoney3;
	}

	public void setUsedMoney3(int usedMoney3) {
		initializeData();
		editor.putInt("usedMoney3", usedMoney3);
		editor.commit();
		this.usedMoney3 = usedMoney3;
	}

	public int getUsedMoney4() {
		usedMoney4=sharedPreferences.getInt("usedMoney4", 0);
		return usedMoney4;
	}

	public void setUsedMoney4(int usedMoney4) {
		initializeData();
		editor.putInt("usedMoney4", usedMoney4);
		editor.commit();
		this.usedMoney4 = usedMoney4;
	}

	public int getUsedMoney5() {
		usedMoney5=sharedPreferences.getInt("usedMoney5", 0);
		return usedMoney5;
	}

	public void setUsedMoney5(int usedMoney5) {
		initializeData();
		editor.putInt("usedMoney5", usedMoney5);
		editor.commit();
		this.usedMoney5 = usedMoney5;
	}

	public int getUsedMoney6() {
		usedMoney6=sharedPreferences.getInt("usedMoney6", 0);
		return usedMoney6;
	}

	public void setUsedMoney6(int usedMoney6) {
		initializeData();
		editor.putInt("usedMoney6", usedMoney6);
		editor.commit();
		this.usedMoney6 = usedMoney6;
	}
}
