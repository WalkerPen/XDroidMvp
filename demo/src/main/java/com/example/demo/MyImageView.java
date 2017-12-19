package com.example.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Pen on 2017/8/10.
 */

public class MyImageView extends AppCompatImageView {
    private float width;
    private float height;
    private float scale;
    private ArrayList<Float> points = new ArrayList<>();
    private Paint mPaint;

    public MyImageView(Context context) {
        this(context, null);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (width == 0) {
            width = getWidth();
        }
        if (height == 0) {
            height = getHeight();
        }
        if (scale == 0) {
            scale = width * 1f / 293;
            Log.d("日志", width + "..." + height + "..." + scale);
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int downX = (int) event.getX();
                int downY = (int) event.getY();
                points.add(downX * 1f);
                points.add(downY * 1f);
                Log.d("日志", points.toString());
                invalidate();
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(int i = 0; i < points.size(); i += 2) {
            RectF oval = new RectF(points.get(i) - 20, points.get(i + 1) - 20, points.get(i) + 20, points.get(i + 1) + 20);
            canvas.drawOval(oval, mPaint);
        }
    }

    public String getPointString() {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < points.size(); i ++) {
            if(i % 2 == 0) {
                sb.append((int)(points.get(i) / scale));
            }else {
                sb.append((int)(points.get(i) / scale) - 30);
            }
            if(i != points.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public void clearPoint() {
        points.clear();
        invalidate();
    }
}
