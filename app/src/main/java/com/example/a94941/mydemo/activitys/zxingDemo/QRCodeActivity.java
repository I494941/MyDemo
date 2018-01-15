package com.example.a94941.mydemo.activitys.zxingDemo;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;
import com.example.a94941.mydemo.utils.CommonUtils;
import com.google.zxing.WriterException;

import butterknife.BindView;

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
        mTvTitle.setText(R.string.erweima);
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            Bitmap bitmap = MakeQRCodeUtils.makeQRImage("苍井空闪嫁 曾遭抹黑“终身不孕”崩溃痛哭",
                    CommonUtils.getScreenWidth(mContext), CommonUtils.getScreenWidth(mContext));
            mIv.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
