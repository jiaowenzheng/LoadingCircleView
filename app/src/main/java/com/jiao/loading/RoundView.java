package com.jiao.loading;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;

public class RoundView extends View {

    private Paint mPaint;
    private int mFirstColor;
    private int mSecondColor;
    private int mThreeColor;

    private int width;
    private int height;

    public RoundView(Context context) {
        super(context);
        init();
    }

    public RoundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mFirstColor = getResources().getColor(R.color.color_ACD120);
        mSecondColor = getResources().getColor(R.color.color_FFB03F);
        mThreeColor = getResources().getColor(R.color.color_ED4A42);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mFirstColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        this.width = getMeasuredWidth();
        this.height = getMeasuredHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.width = w;
        this.height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.i("jiao","width "+width+" height "+height);

//        mPaint.setColor(mFirstColor);
        canvas.drawCircle(width / 2,height / 2,height / 2,mPaint);

    }

    public void setPaintColor(@ColorRes int color){
        this.mFirstColor = getResources().getColor(color);
        mPaint.setColor(mFirstColor);
    }
}
