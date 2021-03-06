package com.example.a94941.mydemo.activitys.recyclerViewActivity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;
import com.example.a94941.mydemo.weight.AutoLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @创建者 94941
 * @创建时间 2018/2/28
 * @描述 ${TODO}
 */
public class RecyclerViewActivity extends BaseToolbarActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.ll_empty)
    LinearLayout mLlEmpty;

    private RV2Adapter mAdapter;
    private List<String> mList = new ArrayList<>();

    private int listSize = 5;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("RecyclerView");
        mIvRight.setVisibility(View.VISIBLE);

        initRecyclerView();
        inidata();
    }

    @Override
    protected String getContent() {
        return "RecyclerView";
    }

    private void initRecyclerView() {
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new AutoLinearLayoutManager(mContext));

        mAdapter = new RV2Adapter(R.layout.item_rv1, mList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void inidata() {

        for (int i = 0; i < listSize; i++) {
            mList.add(i + "");
        }
        mAdapter.notifyDataSetChanged();

        if (mList.size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            mLlEmpty.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.GONE);
            mLlEmpty.setVisibility(View.VISIBLE);
        }
    }
}
