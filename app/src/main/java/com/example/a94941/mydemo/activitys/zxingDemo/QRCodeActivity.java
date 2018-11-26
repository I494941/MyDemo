package com.example.a94941.mydemo.activitys.zxingDemo;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;
import com.example.a94941.mydemo.utils.ScreenUtil;
import com.google.zxing.WriterException;

import butterknife.BindView;
import butterknife.OnLongClick;

/**
 * Created by 94941 on 2018/1/3.
 */

public class QRCodeActivity extends BaseToolbarActivity {

    @BindView(R.id.iv)
    ImageView mIv;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_qrcode;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("智电众安");
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
//            Bitmap bitmap = MakeQRCodeUtils.makeQRImage("苍井空闪嫁 曾遭抹黑“终身不孕”崩溃痛哭",
            Bitmap bitmap = MakeQRCodeUtils.makeQRImage("http://101.132.44.241:38080/tsingpro.apk",
                    ScreenUtil.getWidth(mContext), ScreenUtil.getHeight(mContext));
            mIv.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @OnLongClick({R.id.iv})    //设置一个长按事件
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.iv:

                new ZXingBitmapUtils(this).longClick(mIv);
                break;
        }
        return true;
    }
}
