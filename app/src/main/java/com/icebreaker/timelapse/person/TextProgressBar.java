package com.icebreaker.timelapse.person;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by Marhong on 2018/5/26.
 */

public class TextProgressBar extends ProgressBar {
    String text;
    Paint mPaint;

    public TextProgressBar(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        System.out.println("1");
        initText();
    }

    public TextProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        System.out.println("2");
        initText();
    }


    public TextProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        System.out.println("3");
        initText();
    }

    @Override
    public  void setProgress(int progress) {
        // TODO Auto-generated method stub
        super.setProgress(progress);

    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        //this.setText();
//        Rect rect = new Rect();
//        this.mPaint.getTextBounds(this.text, 0, this.text.length(), rect);
//        int x = 0;
//        int y = (getHeight() / 2) - rect.centerY();
//        canvas.drawText(this.text, x, y, this.mPaint);
    }

    //初始化，画笔
    private void initText(){
        this.mPaint = new Paint();
        this.mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(50);
    }

}
