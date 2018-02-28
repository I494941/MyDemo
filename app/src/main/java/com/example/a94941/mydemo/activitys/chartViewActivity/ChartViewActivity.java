package com.example.a94941.mydemo.activitys.chartViewActivity;

import android.view.View;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

/**
 * @创建者 94941
 * @创建时间 2018/2/24
 * @描述 ${TODO}
 */
public class ChartViewActivity extends BaseToolbarActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_chartview;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("ChartView");
        mIvRight.setVisibility(View.VISIBLE);
    }

    @Override
    protected String getContent() {
        return "自定义控件之折线图";
    }
}
