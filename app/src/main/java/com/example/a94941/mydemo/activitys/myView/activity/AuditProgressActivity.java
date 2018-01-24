package com.example.a94941.mydemo.activitys.myView.activity;

import android.view.View;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.activitys.myView.view.AuditProgressView;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @创建者 94941
 * @创建时间 2018/1/22
 * @描述 ${TODO}
 */
public class AuditProgressActivity extends BaseToolbarActivity {

    @BindView(R.id.av)
    AuditProgressView mAv;

    private ArrayList<String> textList = new ArrayList<>();
    private ArrayList<String> timeList = new ArrayList<>();

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_audit_progress;
    }

    @Override
    protected void initViewsAndEvents() {

        initView();
    }

    private void initView() {

        textList.add("提交成功");
        textList.add("提交成功");
        textList.add("提交成功");
        textList.add("提交成功");
        textList.add("提交成功");

        timeList.add("12-18 11:20");
        timeList.add("12-19 11:20");
        timeList.add("12-20 11:20");
        timeList.add("12-21 11:20");
        timeList.add("12-21 11:20");

        mAv.setDate(textList, timeList, 3, true);
    }

    @OnClick({R.id.btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                mAv.setDate(textList, timeList, (int) (Math.random() * 6), true);
                break;
        }
    }
}
