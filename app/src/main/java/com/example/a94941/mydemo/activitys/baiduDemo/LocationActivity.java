package com.example.a94941.mydemo.activitys.baiduDemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.service.LocationService;
import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.app.MyApplication;
import com.example.a94941.mydemo.base.BaseToolbarActivity;
import com.example.a94941.mydemo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @创建者 94941
 * @创建时间 2018/1/11
 * @描述 ${TODO}
 */
public class LocationActivity extends BaseToolbarActivity {

    @BindView(R.id.rv)
    RecyclerView mRv;

    private List<PositionBean> mList = new ArrayList<>();
    private PositionAdapter mAdapter;

    private LocationService locationService;

    public Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {  //接收消息，刷新数据到UI
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (locationService != null) {
                        locationService.stop();
                    }
                    locationService.start();// 定位SDK
                    sendEmptyMessageDelayed(1, 2000);
                    break;
            }
        }

    };

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_baidu_location;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("基础定位");

        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PositionAdapter(R.layout.item_baidu_demo, mList);
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // -----------location config ------------
        locationService = ((MyApplication) getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        int type = getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }

        mHandler.sendEmptyMessage(1);
    }

    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }

    /*****
     *
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     *
     */
    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {

                PositionBean positionBean = new PositionBean();
                positionBean.latitude = String.valueOf(location.getLatitude());
                positionBean.lontitude = String.valueOf(location.getLongitude());

                mList.add(positionBean);
                mAdapter.notifyDataSetChanged();

                LogUtils.e("12121212latitude", positionBean.latitude);
                LogUtils.e("12121212lontitude", positionBean.lontitude);
            }
        }
    };
}