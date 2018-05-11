package com.example.a94941.mydemo.activitys.SpannableString;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

import butterknife.BindView;

/**
 * Created by wjf on 2018/5/9.
 */

public class SpannableStringActivity extends BaseToolbarActivity {

    @BindView(R.id.tv)
    TextView mTv;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_spannable_string;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("工作任务");
        setSpannableString();
    }

    private void setSpannableString() {

        SpannableStringBuilder style = new SpannableStringBuilder("很抱歉，没有找到该商品，请先去添加哦~");
        style.setSpan(new ForegroundColorSpan(Color.RED), 14, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new ForegroundColorSpan(Color.GRAY), 17, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(60);
        style.setSpan(absoluteSizeSpan, 14, 17, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        mTv.setText(style);
    }
}
