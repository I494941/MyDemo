package com.example.a94941.mydemo.activitys.sunAnimationViewActivity;

import android.view.View;
import android.widget.Button;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;
import com.example.a94941.mydemo.utils.DateUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wjf on 2018/8/9.
 */

public class SunAnimationViewActivity extends BaseToolbarActivity {

    @BindView(R.id.sun_view)
    SunAnimationView mSunView;
    @BindView(R.id.btn_set_time)
    Button mBtnSetTime;

    private String mCurrentTime;
    private int pos = 0;
    private ArrayList<String> mList = new ArrayList<>();

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_sunanimationview;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("日出日落");
        initView();

        mCurrentTime = DateUtil.getTime3();
        mSunView.setTimes("06:00", "18:00", mCurrentTime);
        mBtnSetTime.setText("当前时间：" + mCurrentTime);
    }

    private void initView() {

        mList.clear();
        mList.add("05:00");
        mList.add("06:00");
        mList.add("09:00");
        mList.add("12:00");
        mList.add("15:00");
        mList.add("18:00");
        mList.add("20:00");
    }

    @OnClick({R.id.btn_set_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_set_time:
                pos = pos % 7;
                mCurrentTime = mList.get(pos);
                pos++;
                mSunView.setTimes("06:00", "18:00", mCurrentTime);
                mBtnSetTime.setText("当前时间：" + mCurrentTime);
                break;
        }
    }
}

