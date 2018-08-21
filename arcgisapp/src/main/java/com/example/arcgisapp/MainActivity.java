package com.example.arcgisapp;

import android.view.View;

import com.example.arcgisapp.arcGIS.ArcGISActivity;
import com.example.arcgisapp.base.BaseToolbarActivity;

public class MainActivity extends BaseToolbarActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("首页");
    }

    public void goArcGISActivity(View view) {
        readyGo(ArcGISActivity.class);
    }
}
