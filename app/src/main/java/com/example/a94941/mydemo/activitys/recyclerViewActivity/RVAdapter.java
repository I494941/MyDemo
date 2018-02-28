package com.example.a94941.mydemo.activitys.recyclerViewActivity;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.weight.AutoLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 94941
 * @创建时间 2018/2/28
 * @描述 ${TODO}
 */
public class RVAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    public RVAdapter(int layoutResId, @Nullable List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        helper.setText(R.id.tv_title, item + 0 + "")
                .setText(R.id.tv_time, item + 1 + "")
                .setText(R.id.tv_name, item + 2 + "");

        RecyclerView rv = helper.getView(R.id.rv_1);

        rv.setNestedScrollingEnabled(false);
        rv.setLayoutManager(new AutoLinearLayoutManager(mContext));

        List<Integer> mList = new ArrayList<>();

        for (int i = 0; i < item + 1; i++) {
            mList.add(i);
        }
        RV1Adapter mAdapter = new RV1Adapter(R.layout.item_rv1, mList);
        rv.setAdapter(mAdapter);
    }
}
