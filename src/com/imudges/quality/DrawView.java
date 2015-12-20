package com.imudges.quality;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

public class DrawView extends View{
	private int dataValue;
	private int money;
	private Paint p =null; 
	private AppData appData;
	private Context context1;
	
	public DrawView(Context context) {  
        super(context); 
        p=new Paint(); 
        appData=new AppData(getContext());
    }  
  
	public void setValue(int Data,int money){
		dataValue=Data;
		this.money=money;
	}
	
    @Override  
    protected void onDraw(Canvas canvas) {
    	super.onDraw(canvas);
 
    	Log.w("DrawView", "DrawView is onDraw ") ; 
    	
    	int textSize=60;
    	int a=canvas.getHeight()<canvas.getWidth()?canvas.getHeight():canvas.getWidth();
        
        canvas.drawColor(Color.WHITE); 
        p.setColor(Color.rgb(230,230,230)); 
        p.setStyle(Paint.Style.STROKE);
        p.setAntiAlias(true);  //消除锯齿   
        p.setStrokeWidth(20);
        
        a-=30;
        RectF oval2 = new RectF(30, 30, a, a);// 设置个新的长方形，扫描测量  
        canvas.drawArc(oval2, 0, 360, false, p); 
        
        int colorR=0;
		int colorG=0;
		if(dataValue>=50){
			
			colorG=255;
			colorR=(120-dataValue)*2*255/100;
		}else {
			colorR=255;
			colorG=(dataValue*2)*255/100;
		}
		p.setColor(Color.rgb(colorR,colorG,0));// 设置
		
		
        double valueDouble=3.6*(double)dataValue;
        canvas.drawArc(oval2, (int)(270-valueDouble), (int)valueDouble, false, p);  
         
        p.setTextSize(textSize); 
        p.setStrokeWidth(10);
        canvas.drawText(""+money, (a-textSize)/2, (a+textSize)/2, p);
    }

}
