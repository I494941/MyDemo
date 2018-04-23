package com.example.a94941.mydemo.activitys.touchEventDemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.a94941.mydemo.utils.LogUtils;

/**
 * @创建者 94941
 * @创建时间 2018/4/8
 * @描述 ${TODO}
 */
public class ViewC extends View {

    public ViewC(Context context) {
        super(context);
    }

    public ViewC(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewC(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        LogUtils.e("ViewC", "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        LogUtils.e("ViewC", "onTouchEvent");
        return super.onTouchEvent(event);
    }
}
