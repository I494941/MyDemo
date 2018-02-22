package com.example.a94941.mydemo.activitys.dateDemo;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @创建者 94941
 * @创建时间 2018/2/2
 * @描述 ${TODO}
 */
public class DateUtil {

    private String mYear;
    private String mMonth;
    private String mDay;
    private String mWay;

    //  农历月份
    private static String[] lunarMonth = {"正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "冬月", "腊月"};
    //  农历日
    private static String[] lunarDay   = {"初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十",
            "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十",
            "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十"};

    /**
     * 获取前n天日期、后n天日期
     *
     * @param distanceDay 前几天 如获取前7天日期则传-7即可；如果后7天则传7
     * @return
     */
    public static String getOldDate(int distanceDay) {

        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("AAA", "前7天==" + dft.format(endDate));
        return dft.format(endDate);
    }

    public static void getCurrentDate() {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
    }


    public String StringData() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        Log.d("AAA", "StringData: ==========" + mYear + "年" + mMonth + "月" + mDay + "日" + "/星期" + mWay);
        //        return mYear + "年" + mMonth + "月" + mDay + "日" + "/星期" + mWay;
        return mDay + "/" + mWay;
    }

    /**
     * 获取农历月份
     *
     * @return
     */
    public static String getLunarMonth() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int[] lunarDate = LunarCalendar.solarToLunar(year, month, day);
        Log.d("AAA", "year: -========" + calendar.get(Calendar.YEAR));
        Log.d("AAA", "month: -========" + month);
        Log.d("AAA", "day: -========" + day);
        Log.d("AAA", "getLunarMonth: -========" + lunarMonth[lunarDate[1] - 1]);
        return lunarMonth[lunarDate[1] - 1];
    }

    /**
     * 获取农历日
     *
     * @return
     */
    public static String getLunarDay() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int[] lunarDate = LunarCalendar.solarToLunar(year, month, day);
        Log.d("AAA", "getLunarDay: -========" + lunarDay[lunarDate[2] - 1]);
        return lunarDay[lunarDate[2] - 1];
    }
}
