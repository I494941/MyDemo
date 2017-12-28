package com.example.a94941.mydemo.activitys.superTextView;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;
import com.example.a94941.mydemo.utils.LogUtils;

/**
 * Created by 94941 on 2017/12/26.
 */

public class Type0Activity extends BaseToolbarActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_type0;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("所有组合");
    }
}
