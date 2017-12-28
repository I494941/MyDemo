package com.example.a94941.mydemo.activitys.colorViewDemo;

import android.view.View;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

/**
 * Created by 94941 on 2017/12/26.
 */

public class ColorViewDemoActivity extends BaseToolbarActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_colorviewdemo;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("ColorViewDemo");
        mIvRight.setVisibility(View.VISIBLE);
    }

    @Override
    protected String getContent() {
        return "抛弃Selector文件，直接在布局文件中实现，文字颜色，背景颜色，边框圆角，背景图片，前景图片的配置";
    }
}
