package com.example.a94941.mydemo.activitys.baiduDemo;

import android.view.View;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

/**
 * @创建者 94941
 * @创建时间 2018/1/11
 * @描述 ${TODO}
 */
public class BaiduDemoActivity extends BaseToolbarActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_baidu_demo;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("BaiduDemo");
    }

    public void locationActivity(View view) {
        readyGo(LocationActivity.class);
    }

    public void locationFilter(View view) {
        readyGo(LocationFilterActivity.class);
    }
}