package com.example.a94941.mydemo.activitys.tickview;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;
import com.example.a94941.mydemo.utils.ToastUtils;
import com.github.chengang.library.TickView;

import butterknife.BindView;

/**
 * Created by wjf on 2018/12/24.
 */
public class TickViewActivity extends BaseToolbarActivity {

    @BindView(R.id.tick_view_accent)
    TickView mTickView;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_tickview;
    }

    @Override
    protected void initViewsAndEvents() {

        mTickView.setOnCheckedChangeListener(new TickView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(TickView tickView, boolean isCheck) {
                //do something here
                ToastUtils.showToast(isCheck + "");
            }
        });
    }
}
