package com.example.a94941.mydemo.activitys.recyclerViewActivity;

import android.support.v7.widget.RecyclerView;
import android.view.View;

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

    private RVAdapter mAdapter;
    private List<Integer> mList = new ArrayList<>();

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("RecyclerView");
        mIvRight.setVisibility(View.VISIBLE);

        for (int i = 0; i < 5; i++) {
            mList.add(i );
        }

        initRecyclerView();
    }

    @Override
    protected String getContent() {
        return "RecyclerView";
    }

    private void initRecyclerView() {
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new AutoLinearLayoutManager(mContext));

        mAdapter = new RVAdapter(R.layout.item_rv, mList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
