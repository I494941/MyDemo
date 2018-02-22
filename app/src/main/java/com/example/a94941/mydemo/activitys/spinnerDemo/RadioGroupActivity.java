package com.example.a94941.mydemo.activitys.spinnerDemo;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

/**
 * @创建者 94941
 * @创建时间 2018/2/6
 * @描述 ${TODO}
 */
public class RadioGroupActivity extends BaseToolbarActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_radiogroup;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("工作任务");
    }
}
