package com.tsingpro.androidzxing;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mylhyl.zxing.scanner.ScannerView;

/**
 * @创建者 94941
 * @创建时间 2018/1/30
 * @描述 ${TODO}
 */
public class ScanningActivity extends AppCompatActivity {

    private Context mContext;

    public static final int REQUESTCODE_READ_EXTERNAL_STORAGE = 2;

    private ScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);

        mContext = this;

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //权限还没有授予，需要在这里写申请权限的代码
                    ActivityCompat.requestPermissions(ScanningActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUESTCODE_READ_EXTERNAL_STORAGE);
                } else {

                }
            }
        });

        mScannerView = findViewById(R.id.scanner_view);
//        mScannerView.setOnScannerCompletionListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUESTCODE_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //权限已经被授予，在这里直接写要执行的相应方法即可

                } else {
                    Toast.makeText(mContext, "You denied the permission！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
