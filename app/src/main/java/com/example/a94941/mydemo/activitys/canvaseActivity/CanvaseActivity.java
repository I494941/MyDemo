package com.example.a94941.mydemo.activitys.canvaseActivity;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

/**
 * Created by wjf on 2018/8/20.
 */

public class CanvaseActivity extends BaseToolbarActivity {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_canvase;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("Canvase");
    }
}
