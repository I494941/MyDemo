package com.example.a94941.mydemo.activitys.touchEventDemo;

import android.view.View;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

/**
 * @创建者 94941
 * @创建时间 2018/4/8
 * @描述 ${TODO}
 */
public class TouchEventActivity extends BaseToolbarActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_touch_event;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("TouchEvent");
        mIvRight.setVisibility(View.VISIBLE);
    }

    @Override
    protected String getContent() {
        return "dispatchTouchEvent、onIntercaptTouchEvent、onTouchEvent";
    }
}
