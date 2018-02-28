package com.example.a94941.mydemo.activitys.recyclerViewActivity;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a94941.mydemo.R;

import java.util.List;

/**
 * @创建者 94941
 * @创建时间 2018/2/28
 * @描述 ${TODO}
 */
public class RV1Adapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    public RV1Adapter(int layoutResId, @Nullable List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        helper.setText(R.id.tv_1, item + "");
    }
}
