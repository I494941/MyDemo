package com.example.a94941.mydemo.activitys.myView;

import android.view.View;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.activitys.myView.activity.AuditProgressActivity;
import com.example.a94941.mydemo.activitys.myView.activity.ClockActivity;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

import butterknife.OnClick;

/**
 * @创建者 94941
 * @创建时间 2018/1/22
 * @描述 ${TODO}
 */
public class MyViewActivity extends BaseToolbarActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_myview;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("自定义控件");
    }

    @OnClick({R.id.btn_audit_progress, R.id.btn_clock})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_audit_progress:
                readyGo(AuditProgressActivity.class);
                break;
            case R.id.btn_clock:
                readyGo(ClockActivity.class);
                break;
        }
    }
}
