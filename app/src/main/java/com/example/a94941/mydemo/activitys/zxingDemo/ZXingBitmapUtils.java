package com.example.a94941.mydemo.activitys.zxingDemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.utils.LogUtils;
import com.example.a94941.mydemo.utils.ToastUtils;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @创建者 94941
 * @创建时间 2018/1/26
 * @描述 ${TODO}
 */
public class ZXingBitmapUtils {

    private Activity mActivity;

    public ZXingBitmapUtils(Activity activity) {
        mActivity = activity;
    }

    //通过zxing读取图片，判断是否有二维码
    public void longClick(ImageView imageView) {
        //通过zxing读取图片，判断是否有二维码
        Bitmap obmp = ((BitmapDrawable) (imageView).getDrawable()).getBitmap();
        int width = obmp.getWidth();
        int height = obmp.getHeight();
        int[] data = new int[width * height];
        obmp.getPixels(data, 0, width, 0, 0, width, height);
        RGBLuminanceSource source = new RGBLuminanceSource(width, height, data);
        BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        Result re = null;
        try {
            re = reader.decode(bitmap1);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (ChecksumException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
        if (re == null) {
            showAlert(obmp);
        } else {
            showSelectAlert(obmp, re.getText());
        }
    }


    private void showAlert(final Bitmap bitmap) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

        View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_bitmap_longclick, null);

        TextView mTvSave = view.findViewById(R.id.tv_save);
        TextView mTvRead = view.findViewById(R.id.tv_read);
        mTvRead.setVisibility(View.GONE);

        mTvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBitmap(bitmap);
            }
        });
        builder.setView(view);
        builder.show();
    }

    private void showSelectAlert(final Bitmap bitmap, final String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

        View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_bitmap_longclick, null);

        TextView mTvSave = view.findViewById(R.id.tv_save);
        TextView mTvRead = view.findViewById(R.id.tv_read);

        mTvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBitmap(bitmap);
            }
        });
        mTvRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResultHandler(bitmap, url);
            }
        });
        builder.setView(view);
        builder.show();
    }

    /**
     * 处理扫描结果
     *
     * @param resultString
     * @param
     */
    private void onResultHandler(Bitmap bitmap, String resultString) {
        if (TextUtils.isEmpty(resultString)) {
            ToastUtils.showToast("扫描失败!");
            return;
        }

        // 处理扫描结果
        LogUtils.e("12121212", resultString);
        ToastUtils.showToast(resultString);

        if (mActivity != null) {
            mActivity.finish();
        }
    }

    //保存Bitmap
    public void saveBitmap(Bitmap temBitmap) {
        SimpleDateFormat df = new SimpleDateFormat("yyyymmddhhmmss");
        String time = df.format(new Date());
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/tsingpro", time + ".png");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            try {
                FileOutputStream fos = new FileOutputStream(file);
                temBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
                ToastUtils.showToast("图片保存成功！");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //这种方法状态栏是空白，显示不了状态栏的信息
    public void saveCurrentScreen(Activity activity) {
        //获取当前屏幕的大小
        int width = activity.getWindow().getDecorView().getRootView().getWidth();
        int height = activity.getWindow().getDecorView().getRootView().getHeight();
        //生成相同大小的图片
        Bitmap temBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //找到当前页面的根布局
        View view = activity.getWindow().getDecorView().getRootView();
        //设置缓存
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //从缓存中获取当前屏幕的图片
        temBitmap = view.getDrawingCache();
        SimpleDateFormat df = new SimpleDateFormat("yyyymmddhhmmss");
        String time = df.format(new Date());
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/tsingpro", time + ".png");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            try {
                FileOutputStream fos = new FileOutputStream(file);
                temBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
                ToastUtils.showToast("图片保存成功！");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
