package com.example.a94941.mydemo.activitys.bluetoothDemo;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.activitys.baiduDemo.BleAdapter;
import com.example.a94941.mydemo.activitys.baiduDemo.BleBean;
import com.example.a94941.mydemo.base.BaseToolbarActivity;
import com.example.a94941.mydemo.utils.CommonUtils;
import com.example.a94941.mydemo.utils.FileUtils;
import com.example.a94941.mydemo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @创建者 94941
 * @创建时间 2018/1/12
 * @描述 ${TODO}
 */
public class BluetoothDemoActivity extends BaseToolbarActivity {

    @BindView(R.id.rv_yilianjie)
    RecyclerView mRvYilianjie;
    @BindView(R.id.rv_weilianjie)
    RecyclerView mRvWeilianjie;

    private List<BleBean> mYLJList = new ArrayList<>();
    private BleAdapter mYLJAdapter;
    private List<BleBean> mWLJList = new ArrayList<>();
    private BleAdapter mWLJAdapter;

    private BluetoothAdapter mBluetoothAdapter;
    private String fileName = "MyBluetooth.txt";

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_bluetooth_demo;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("蓝牙");

        initRecyclerView();

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.enable(); //开启
        //mBluetoothAdapter.disable(); //关闭

        requestBluetoothPermission();
    }

    private void initRecyclerView() {

        mRvYilianjie.setNestedScrollingEnabled(false);
        mRvYilianjie.setLayoutManager(new LinearLayoutManager(mContext));
        mYLJAdapter = new BleAdapter(R.layout.item_bluetooth, mYLJList);
        mRvYilianjie.setAdapter(mYLJAdapter);

        mRvWeilianjie.setNestedScrollingEnabled(false);
        mRvWeilianjie.setLayoutManager(new LinearLayoutManager(mContext));
        mWLJAdapter = new BleAdapter(R.layout.item_bluetooth, mWLJList);
        mRvWeilianjie.setAdapter(mWLJAdapter);
    }

    private void search() {

        mBluetoothAdapter.setName(CommonUtils.getTime());
        mBluetoothAdapter.startDiscovery();
        // 设置广播信息过滤
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);//每搜索到一个设备就会发送一个该广播
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);//当全部搜索完后发送该广播
        filter.setPriority(Integer.MAX_VALUE);//设置优先级
        // 注册蓝牙搜索广播接收者，接收并处理搜索结果
        this.registerReceiver(receiver, filter);
    }

    @OnClick({R.id.btn_search, R.id.btn_claer})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                if (mBluetoothAdapter.isDiscovering()) {
                    mBluetoothAdapter.cancelDiscovery();
                }
                mBluetoothAdapter.setName(CommonUtils.getTime());
                mBluetoothAdapter.startDiscovery();
                break;
            case R.id.btn_claer:
                mWLJList.clear();
                mWLJAdapter.notifyDataSetChanged();
                break;
        }
    }

    /**
     * 定义广播接收器
     */
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {

                    //信号强度。
                    short rssi = intent.getExtras().getShort(
                            BluetoothDevice.EXTRA_RSSI);

                    BleBean bean = new BleBean();
                    bean.name = device.getName();
                    bean.add = device.getAddress();
                    bean.rssi = rssi + "";
                    bean.timeStart = mBluetoothAdapter.getName();
                    bean.timeEnd = CommonUtils.getTime();
                    mWLJList.add(bean);
                    mWLJAdapter.notifyDataSetChanged();

                    String bluetooth = "name:" + bean.name +
                            ",add:" + bean.add +
                            ",rssi:" + bean.rssi +
                            ",timeStart:" + bean.timeStart +
                            ",timeEnd:" + bean.timeEnd;

                    LogUtils.e("12121212bluetooth", bluetooth);

                    FileUtils.writeFileToSDCard(bluetooth.getBytes(), null, fileName, true, true);
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //已搜索完成
                mBluetoothAdapter.cancelDiscovery();
                mBluetoothAdapter.setName(CommonUtils.getTime());
                mBluetoothAdapter.startDiscovery();
            }
        }
    };

    private static final int REQUEST_BLUETOOTH_PERMISSION = 94;

    private void requestBluetoothPermission() {
        //判断系统版本
        if (Build.VERSION.SDK_INT >= 23) {
            //检测当前app是否拥有某个权限
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION);
            //判断这个权限是否已经授权过
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_BLUETOOTH_PERMISSION);
            } else {
                search();
            }
        } else {
            search();
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
            case REQUEST_BLUETOOTH_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    search();
                } else {
                    Toast.makeText(mContext, "You denied the permission！", Toast.LENGTH_SHORT).show();
                }
            default:
                break;
        }
    }
}
