package com.example.a94941.mydemo.activitys.baiduDemo;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a94941.mydemo.R;

import java.util.List;


/**
 * @创建者 94941
 * @创建时间 2018/1/10
 * @描述 ${TODO}
 */
public class BleAdapter extends BaseQuickAdapter<BleBean, BaseViewHolder> {

    public BleAdapter(int layoutResId, @Nullable List<BleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BleBean item) {

        helper.setText(R.id.tv_name, item.name)
                .setText(R.id.tv_add, item.add)
                .setText(R.id.tv_rssi, item.rssi)
                .setText(R.id.tv_time_start, item.timeStart)
                .setText(R.id.tv_time_end, item.timeEnd);
    }
}
