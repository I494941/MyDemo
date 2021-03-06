package com.example.a94941.mydemo.activitys.serviceActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

/**
 * @创建者 94941
 * @创建时间 2018/3/20
 * @描述 ${TODO}
 */
public class ServiceActivity extends BaseToolbarActivity {

    private MyConn    conn;
    private Intent    intent;
    private IMyBinder myBinder;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_service;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("bindService");

    }

    //开启服务按钮的点击事件
    public void start(View view) {
        intent = new Intent(this, MyService.class);
        conn = new MyConn();
        //绑定服务，
        // 第一个参数是intent对象，表面开启的服务。
        // 第二个参数是绑定服务的监听器
        // 第三个参数一般为BIND_AUTO_CREATE常量，表示自动创建bind
        bindService(intent, conn, BIND_AUTO_CREATE);
    } //调用服务方法按钮的点击事件

    public void invoke(View view) {
        myBinder.invokeMethodInMyService();
    }

    private class MyConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //iBinder为服务里面onBind()方法返回的对象，所以可以强转为IMyBinder类型
            myBinder = (IMyBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }
}
