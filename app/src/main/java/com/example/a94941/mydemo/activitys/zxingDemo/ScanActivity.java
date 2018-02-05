package com.example.a94941.mydemo.activitys.zxingDemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;
import com.example.a94941.mydemo.utils.ToastUtils;

import butterknife.OnClick;

/**
 * @创建者 94941
 * @创建时间 2018/1/15
 * @描述 ${TODO}
 */
public class ScanActivity extends BaseToolbarActivity {


    private static final int REQUESTCODE_ZXING = 94;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_scan;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText(R.string.zxing);

    }

    @OnClick({R.id.ll_scan, R.id.ll_qrcode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_qrcode:
                readyGo(QRCodeActivity.class);
                break;
            case R.id.ll_scan:
                requestPermission();
                break;
        }
    }

    private void requestPermission() {
        //判断系统版本
        if (Build.VERSION.SDK_INT >= 23) {
            //检测当前app是否拥有某个权限
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA);
            //判断这个权限是否已经授权过
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUESTCODE_ZXING);
            } else {
                readyGo(ScanningActivity.class);
            }
        } else {
            readyGo(ScanningActivity.class);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUESTCODE_ZXING:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readyGo(ScanningActivity.class);
                } else {
                    ToastUtils.showToast("You denied the permission");
                }
                break;
            default:
        }
    }
}
