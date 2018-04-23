package com.example.a94941.mydemo.activitys.touchEventDemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.a94941.mydemo.utils.LogUtils;

/**
 * @创建者 94941
 * @创建时间 2018/4/8
 * @描述 ${TODO}
 */
public class ViewGroupA extends ViewGroup {

    public ViewGroupA(Context context) {
        super(context);
    }

    public ViewGroupA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupA(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int height = 0;
        int count = getChildCount();
        View child;

        for (int j = 0; j < count; j++) {
            child = getChildAt(j);
            child.layout(0, height, child.getMeasuredWidth(), height + child.getMeasuredHeight());
            height += child.getMeasuredHeight();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        LogUtils.e("ViewGroupA", "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        LogUtils.e("ViewGroupA", "onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        LogUtils.e("ViewGroupA", "onTouchEvent");
//        return super.onTouchEvent(event);
        return true;
    }
}
