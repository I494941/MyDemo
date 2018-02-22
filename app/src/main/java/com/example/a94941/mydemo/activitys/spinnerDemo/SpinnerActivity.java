package com.example.a94941.mydemo.activitys.spinnerDemo;

import android.view.View;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

import butterknife.OnClick;

/**
 * @创建者 94941
 * @创建时间 2018/2/6
 * @描述 ${TODO}
 */
public class SpinnerActivity extends BaseToolbarActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_spinner;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("工作任务");
        mTvRight.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_right:
                readyGo(RadioGroupActivity.class);
                break;
        }
    }
}
