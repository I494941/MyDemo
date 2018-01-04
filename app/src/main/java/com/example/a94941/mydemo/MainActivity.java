package com.example.a94941.mydemo;

import android.view.KeyEvent;
import android.view.View;

import com.example.a94941.mydemo.activitys.colorViewDemo.ColorViewDemoActivity;
import com.example.a94941.mydemo.activitys.dropDownMenu.DropDownMenuActivity;
import com.example.a94941.mydemo.activitys.fourLevelLinkage.FourLevelLinkageActivity;
import com.example.a94941.mydemo.activitys.superTextView.SuperTextViewActivity;
import com.example.a94941.mydemo.activitys.superTextViewDemo.SuperTextViewDemoActivity;
import com.example.a94941.mydemo.activitys.tempControlView.TempControlViewActivity;
import com.example.a94941.mydemo.activitys.viewPagerCards.ViewPagerCardsActivity;
import com.example.a94941.mydemo.base.BaseAppManager;
import com.example.a94941.mydemo.base.BaseToolbarActivity;
import com.example.a94941.mydemo.utils.ToastUtils;

public class MainActivity extends BaseToolbarActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents() {
        //隐藏toolbar返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mTvTitle.setText("首页");
    }

    public void colorViewDemo(View view) {
        readyGo(ColorViewDemoActivity.class);
    }

    public void superTextView(View view) {
        readyGo(SuperTextViewActivity.class);
    }

    public void superTextViewDemo(View view) {
        readyGo(SuperTextViewDemoActivity.class);
    }

    public void viewPagerCards(View view) {
        readyGo(ViewPagerCardsActivity.class);
    }

    public void dropDownMenu(View view) {
        readyGo(DropDownMenuActivity.class);
    }

    public void fourLevelLinkage(View view) {
        readyGo(FourLevelLinkageActivity.class);
    }

    public void tempControlView(View view) {
        readyGo(TempControlViewActivity.class);
    }

    private long firstTime = 0;

    /**
     * 双击返回键退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                ToastUtils.showToast("再按一次退出");
                firstTime = secondTime;
                return true;
            } else {
                BaseAppManager.getInstance().clear();
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
