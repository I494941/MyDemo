package com.example.a94941.mydemo.activitys.viewPagerCards;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by 94941 on 2017/12/27.
 */

public class ViewPagerCardsActivity extends BaseToolbarActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.cardTypeBtn)
    Button    mButton;
    @BindView(R.id.checkBox)
    CheckBox  mCheckBox;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private CardPagerAdapter         mCardAdapter;
    private ShadowTransformer        mCardShadowTransformer;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer        mFragmentCardShadowTransformer;

    private boolean mShowingFragments = false;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_viewpager_cards;
    }

    @Override
    protected void initViewsAndEvents() {

        mTvTitle.setText("ViewPagerCards");
        mIvRight.setVisibility(View.VISIBLE);

        initCardAdapter();
        initFragmentCardAdapter();
    }

    @Override
    protected String getContent() {
        return "Viewpagercard 一个超炫的Viewpager滑动";
    }

    private void initCardAdapter() {

        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem(R.string.title_1, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_3, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
    }

    private void initFragmentCardAdapter() {

        mFragmentCardAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(),
                dpToPixels(2, this));

        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @OnCheckedChanged(R.id.checkBox)
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mCardShadowTransformer.enableScaling(b);
        mFragmentCardShadowTransformer.enableScaling(b);
    }

    @OnClick(R.id.cardTypeBtn)
    public void onClick() {

        if (!mShowingFragments) {
            mButton.setText("Views");
            mViewPager.setAdapter(mFragmentCardAdapter);
            mViewPager.setPageTransformer(false, mFragmentCardShadowTransformer);
        } else {
            mButton.setText("Fragments");
            mViewPager.setAdapter(mCardAdapter);
            mViewPager.setPageTransformer(false, mCardShadowTransformer);
        }

        mShowingFragments = !mShowingFragments;
    }
}
