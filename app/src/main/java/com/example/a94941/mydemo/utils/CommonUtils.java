package com.example.a94941.mydemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @创建者 94941
 * @创建时间 2018/1/11
 * @描述 ${TODO}
 */
public class CommonUtils {

    //获取当前时间  "HH:mm:ss"
    public static String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }
}
