package com.example.a94941.mydemo.activitys.tempControlView;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;
import com.example.a94941.mydemo.utils.ToastUtils;

import butterknife.BindView;

/**
 * Created by 94941 on 2018/1/3.
 */

public class TempControlViewActivity extends BaseToolbarActivity {

    @BindView(R.id.temp_control)
    TempControlView mTempControl;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_tempcontrol_view;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("TempControlView");

        // 设置三格代表温度1度
        mTempControl.setAngleRate(3);
        mTempControl.setTemp(16, 37, 16);

        mTempControl.setOnTempChangeListener(new TempControlView.OnTempChangeListener() {
            @Override
            public void change(int temp) {
                //                Toast.makeText(mContext, temp + "°", Toast.LENGTH_SHORT).show();
                ToastUtils.showToast(temp + "°");
            }
        });

        mTempControl.setOnClickListener(new TempControlView.OnClickListener() {
            @Override
            public void onClick(int temp) {
                //                Toast.makeText(mContext, temp + "°", Toast.LENGTH_SHORT).show();
                ToastUtils.showToast(temp + "°");
            }
        });
    }
}
