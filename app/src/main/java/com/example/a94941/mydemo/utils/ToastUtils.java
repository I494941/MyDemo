package com.example.a94941.mydemo.utils;

import android.widget.Toast;

import com.example.a94941.mydemo.app.MyApplication;


public class ToastUtils {
    private static Toast toast;

    /**
     * 强大的吐司，能够连续弹的吐司
     *
     * @param message
     */
    public static void showToast(String message) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getAppContext(), message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);//如果不为空，则直接改变当前toast的文本
        }
        toast.show();
    }
}
