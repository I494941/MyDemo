package com.example.a94941.mydemo.activitys.baiduDemo;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a94941.mydemo.R;

import java.util.List;


/**
 * @创建者 94941
 * @创建时间 2018/1/11
 * @描述 ${TODO}
 */
public class PositionAdapter extends BaseQuickAdapter<PositionBean, BaseViewHolder> {

    public PositionAdapter(int layoutResId, @Nullable List<PositionBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PositionBean item) {
        helper.setText(R.id.tv_latitude, item.latitude)
                .setText(R.id.tv_lontitude, item.lontitude);
    }
}
