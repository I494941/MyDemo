package com.example.a94941.mydemo.activitys.superTextViewDemo;

import android.graphics.Color;
import android.view.View;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.activitys.superTextViewDemo.Adjuster.MoveEffectAdjuster;
import com.example.a94941.mydemo.activitys.superTextViewDemo.Adjuster.OpportunityDemoAdjuster;
import com.example.a94941.mydemo.activitys.superTextViewDemo.Adjuster.RippleAdjuster;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

import butterknife.BindView;

/**
 * Created by 王金飞 on 2017/8/7 0007.
 */

public class SuperTextViewDemoActivity extends BaseToolbarActivity {

    @BindView(R.id.stv_17)
    SuperTextView stv_17;
    @BindView(R.id.stv_18)
    SuperTextView stv_18;
    @BindView(R.id.stv_19)
    SuperTextView stv_19;
    @BindView(R.id.stv_20)
    SuperTextView stv_20;
    @BindView(R.id.stv_21)
    SuperTextView stv_21;
    @BindView(R.id.stv_next)
    SuperTextView stv_next;
    @BindView(R.id.stv)
    SuperTextView stv;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_super_textview_demo;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("SuperTextViewDemo");
        mIvRight.setVisibility(View.VISIBLE);
        initView();
    }

    @Override
    protected String getContent() {
        return "抛弃Selector文件，直接在布局文件中实现，文字颜色，背景颜色，边框圆角，背景图片，前景图片的配置";
    }

    private void initView() {

        stv_17.setAdjuster(new MoveEffectAdjuster())
                .setAutoAdjust(true)
                .startAnim();

        stv_18.setAdjuster(new RippleAdjuster(getResources().getColor(R.color.red)));

        OpportunityDemoAdjuster opportunityDemoAdjuster1 = new OpportunityDemoAdjuster();
        opportunityDemoAdjuster1.setOpportunity(SuperTextView.Adjuster.Opportunity.BEFORE_DRAWABLE);
        stv_19.setAdjuster(opportunityDemoAdjuster1);
        stv_19.setAutoAdjust(true);

        OpportunityDemoAdjuster opportunityDemoAdjuster2 = new OpportunityDemoAdjuster();
        opportunityDemoAdjuster2.setOpportunity(SuperTextView.Adjuster.Opportunity.BEFORE_TEXT);
        stv_20.setAdjuster(opportunityDemoAdjuster2);
        stv_20.setAutoAdjust(true);

        OpportunityDemoAdjuster opportunityDemoAdjuster3 = new OpportunityDemoAdjuster();
        opportunityDemoAdjuster3.setOpportunity(SuperTextView.Adjuster.Opportunity.AT_LAST);
        stv_21.setAdjuster(opportunityDemoAdjuster3);
        stv_21.setAutoAdjust(true);

        stv_next.setFrameRate(60);
        stv_next.setShaderStartColor(Color.RED);
    }
}
