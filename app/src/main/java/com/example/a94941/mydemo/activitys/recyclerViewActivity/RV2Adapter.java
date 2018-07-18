package com.example.a94941.mydemo.activitys.recyclerViewActivity;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a94941.mydemo.R;

import java.util.List;

/**
 * Created by wjf on 2018/5/19.
 */

public class RV2Adapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public RV2Adapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_1, item);
    }
}
