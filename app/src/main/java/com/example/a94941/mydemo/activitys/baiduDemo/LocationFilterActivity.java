package com.example.a94941.mydemo.activitys.baiduDemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClientOption;
import com.baidu.location.service.LocationService;
import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.app.MyApplication;
import com.example.a94941.mydemo.base.BaseToolbarActivity;
import com.example.a94941.mydemo.utils.CommonUtils;
import com.example.a94941.mydemo.utils.FileUtils;
import com.example.a94941.mydemo.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @创建者 94941
 * @创建时间 2018/1/11
 * @描述 ${TODO}
 */
public class LocationFilterActivity extends BaseToolbarActivity {

    @BindView(R.id.rv)
    RecyclerView mRv;

    private List<PositionBean> mList = new ArrayList<>();
    private PositionAdapter mAdapter;
    private LocationService locService;
    //保存TXT
    private String fileName = "MyPosition.txt";

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_location_filter;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("连续定位");

        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PositionAdapter(R.layout.item_baidu_demo, mList);
        mRv.setAdapter(mAdapter);

        locService = ((MyApplication) getApplication()).locationService;
        LocationClientOption mOption = locService.getDefaultLocationClientOption();
        mOption.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        mOption.setCoorType("bd09ll");
        mOption.setScanSpan(10000);//时间
        locService.setLocationOption(mOption);
        locService.registerListener(listener);
        //不写入TXT的话，下面这句就可以
        //        locService.start();
        //动态获取读写权限
        requestReadAndWritePermission();
    }

    /***
     * 定位结果回调，在此方法中处理定位结果
     */
    BDAbstractLocationListener listener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub

            if (location != null && (location.getLocType() == 161 || location.getLocType() == 66)) {

                PositionBean positionBean = new PositionBean();
                positionBean.latitude = String.valueOf(location.getLatitude());
                positionBean.lontitude = String.valueOf(location.getLongitude());

                mList.add(positionBean);
                mAdapter.notifyDataSetChanged();

                // 写入文件 TXT
                String bluetooth = "latitude:" + positionBean.latitude +
                        ",lontitude:" + positionBean.lontitude +
                        ",time:" + CommonUtils.getTime();
                FileUtils.writeFileToSDCard(bluetooth.getBytes(), null, fileName, true, true);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        locService.unregisterListener(listener);
        locService.stop();
    }

    private static final int REQUEST_PERMISSION = 10;

    private void requestReadAndWritePermission() {
        //判断系统版本
        if (Build.VERSION.SDK_INT >= 23) {
            //检测当前app是否拥有某个权限
            int checkCallPhonePermission = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            //判断这个权限是否已经授权过
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
            } else {
                locService.start();
            }
        } else {
            locService.start();
        }
    }


    /**
     * 动态权限申请
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locService.start();
                } else {
                    ToastUtils.showToast("You denied the permission！");
                }
            default:
                break;
        }
    }
}
