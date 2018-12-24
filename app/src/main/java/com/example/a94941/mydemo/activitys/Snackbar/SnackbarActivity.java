package com.example.a94941.mydemo.activitys.Snackbar;

import android.view.View;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

/**
 * Created by wjf on 2018/12/5.
 */
public class SnackbarActivity extends BaseToolbarActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_snackbar;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("Snackbar");
    }

    public void goRelativeLayout(View view) {
        readyGo(SnackbarRlActivity.class);
    }

    public void goCoordinatorLayout(View view) {
        readyGo(SnackbarClActivity.class);
    }
}
