package com.example.a94941.mydemo;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.example.a94941.mydemo.activitys.AmountViewActivity.AmountViewActivity;
import com.example.a94941.mydemo.activitys.Calculator.CalculatorActivity;
import com.example.a94941.mydemo.activitys.CalendarView.CalendarViewActivity;
import com.example.a94941.mydemo.activitys.EditTextActivity.EditTextActivity;
import com.example.a94941.mydemo.activitys.JUnit4Activity.JUnit4Activity;
import com.example.a94941.mydemo.activitys.Snackbar.SnackbarActivity;
import com.example.a94941.mydemo.activitys.SpannableString.SpannableStringActivity;
import com.example.a94941.mydemo.activitys.androidView.AndroidViewActivity;
import com.example.a94941.mydemo.activitys.baiduDemo.BaiduDemoActivity;
import com.example.a94941.mydemo.activitys.bluetoothDemo.BluetoothDemoActivity;
import com.example.a94941.mydemo.activitys.canvaseActivity.CanvaseActivity;
import com.example.a94941.mydemo.activitys.chartViewActivity.ChartViewActivity;
import com.example.a94941.mydemo.activitys.colorViewDemo.ColorViewDemoActivity;
import com.example.a94941.mydemo.activitys.dateDemo.DateActivity;
import com.example.a94941.mydemo.activitys.dropDownMenu.DropDownMenuActivity;
import com.example.a94941.mydemo.activitys.fourLevelLinkage.FourLevelLinkageActivity;
import com.example.a94941.mydemo.activitys.jellyToolbar.JellyToolbarActivity;
import com.example.a94941.mydemo.activitys.myView.MyViewActivity;
import com.example.a94941.mydemo.activitys.mzBanner.MzBannerActivity;
import com.example.a94941.mydemo.activitys.recyclerViewActivity.RecyclerViewActivity;
import com.example.a94941.mydemo.activitys.serviceActivity.ServiceActivity;
import com.example.a94941.mydemo.activitys.smsActivity.SmsActivity;
import com.example.a94941.mydemo.activitys.spinnerDemo.SpinnerActivity;
import com.example.a94941.mydemo.activitys.stepViewDemo.StepViewActivity;
import com.example.a94941.mydemo.activitys.sunAnimationViewActivity.SunAnimationViewActivity;
import com.example.a94941.mydemo.activitys.superTextView.SuperTextViewActivity;
import com.example.a94941.mydemo.activitys.superTextViewDemo.SuperTextViewDemoActivity;
import com.example.a94941.mydemo.activitys.tempControlView.TempControlViewActivity;
import com.example.a94941.mydemo.activitys.tickview.TickViewActivity;
import com.example.a94941.mydemo.activitys.touchEventDemo.TouchEventActivity;
import com.example.a94941.mydemo.activitys.viewPagerCards.ViewPagerCardsActivity;
import com.example.a94941.mydemo.activitys.zxingDemo.ScanActivity;
import com.example.a94941.mydemo.base.BaseAppManager;
import com.example.a94941.mydemo.base.BaseToolbarActivity;
import com.example.a94941.mydemo.utils.LogUtils;
import com.example.a94941.mydemo.utils.ToastUtils;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

public class MainActivity extends BaseToolbarActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents() {
        //隐藏toolbar返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mTvTitle.setText(R.string.shouye);

        LogUtils.e("12121212获取手机品牌", android.os.Build.BRAND);

        //        //信鸽推送
        //        initXG();
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

    public void baiduDemo(View view) {
        readyGo(BaiduDemoActivity.class);
    }

    public void bluetoothDemo(View view) {
        readyGo(BluetoothDemoActivity.class);
    }

    public void scanDemo(View view) {
        readyGo(ScanActivity.class);
    }

    public void myViewDemo(View view) {
        readyGo(MyViewActivity.class);
    }

    public void dateDemo(View view) {
        readyGo(DateActivity.class);
    }

    public void spinnerDemo(View view) {
        readyGo(SpinnerActivity.class);
    }

    public void goJUnit4Activity(View view) {
        readyGo(JUnit4Activity.class);
    }

    public void goChartViewActivity(View view) {
        readyGo(ChartViewActivity.class);
    }

    public void goRecyclerViewActivity(View view) {
        readyGo(RecyclerViewActivity.class);
    }

    public void goServiceActivity(View view) {
        readyGo(ServiceActivity.class);
    }

    public void goStepViewActivity(View view) {
        readyGo(StepViewActivity.class);
    }

    public void goTouchEventActivity(View view) {
        readyGo(TouchEventActivity.class);
    }

    public void goAndroidViewActivity(View view) {
        readyGo(AndroidViewActivity.class);
    }

    public void goSpannableStringActivity(View view) {
        readyGo(SpannableStringActivity.class);
    }

    public void goCalculatorActivity(View view) {
        readyGo(CalculatorActivity.class);
    }

    public void goAmountViewActivity(View view) {
        readyGo(AmountViewActivity.class);
    }

    public void goEditTextActivity(View view) {
        readyGo(EditTextActivity.class);
    }

    public void goCalendarViewActivity(View view) {
        readyGo(CalendarViewActivity.class);
    }

    public void goSmsActivity(View view) {
        readyGo(SmsActivity.class);
    }

    public void goSunAnimationViewActivity(View view) {
        readyGo(SunAnimationViewActivity.class);
    }

    public void goCanvaseActivity(View view) {
        readyGo(CanvaseActivity.class);
    }

    public void goSnackbarActivity(View view) {
        readyGo(SnackbarActivity.class);
    }

    public void goTickViewActivity(View view) {
        readyGo(TickViewActivity.class);
    }

    public void goMzBannerActivity(View view) {
        readyGo(MzBannerActivity.class);
    }

    public void goJellyToolbarActivity(View view) {
        readyGo(JellyToolbarActivity.class);
    }

    private void initXG() {
        XGPushConfig.enableDebug(this, true);

        XGPushManager.registerPush(this, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                //token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册成功，设备token为：" + data);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });

        //        XGPushManager.bindAccount(getApplicationContext(), "XINGE");
        XGPushManager.bindAccount(getApplicationContext(), "XINGE1");


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
