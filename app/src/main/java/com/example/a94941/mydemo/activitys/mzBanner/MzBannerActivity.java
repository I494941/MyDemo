package com.example.a94941.mydemo.activitys.mzBanner;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.activitys.mzBanner.view.HeartHonorLayout;
import com.example.a94941.mydemo.base.BaseToolbarActivity;
import com.example.a94941.mydemo.utils.LogUtils;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wjf on 2018/12/24.
 */
public class MzBannerActivity extends BaseToolbarActivity {

    @BindView(R.id.banner)
    MZBannerView mMZBanner;
    @BindView(R.id.heart_layout)
    HeartHonorLayout mHeartLayout;

    private ArrayList<Integer> list;

    private Random mRandom = new Random();
    private Timer mTimer = null;
    private boolean isPouse = true;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_mzbanner;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("画廊与点赞");

        list = new ArrayList<>();

        list.add(R.drawable.banner1);
        list.add(R.drawable.banner2);
        list.add(R.drawable.banner3);
        list.add(R.drawable.banner4);

        // 设置数据
        mMZBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBanner.start();//开始轮播
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                //添加
                mHeartLayout.addHeart(randomColor());
                break;
            case R.id.btn2:
                mHeartLayout.removeAllViews();
                if (isPouse) {
                    mTimer = new Timer();
                    mTimer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            isPouse = false;
                            mHeartLayout.post(new Runnable() {
                                @Override
                                public void run() {
                                    //添加
                                    mHeartLayout.addHeart(randomColor());
                                }
                            });
                        }
                    }, 500, 250);
                } else {
                    isPouse = true;
                    if (mTimer != null)
                        mTimer.cancel();
                    mTimer = null;
                }
                break;
        }
    }

    @Override
    public void onPause() {
        mMZBanner.pause();//暂停轮播
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mTimer != null)
            mTimer.cancel();
        super.onDestroy();
    }

    private int randomColor() {
        return Color.rgb(mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255));
    }

    public static class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, final int position, Integer data) {
            // 数据绑定
            mImageView.setImageResource(data);

            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LogUtils.e("11111111111111", " = " + position);
                }
            });
        }
    }
}
