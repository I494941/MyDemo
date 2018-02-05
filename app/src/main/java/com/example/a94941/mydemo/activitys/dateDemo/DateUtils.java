package com.example.a94941.mydemo.activitys.dateDemo;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @创建者 94941
 * @创建时间 2018/2/2
 * @描述 ${TODO}
 */
public class DateUtils {

    private static int mYear;
    private static int mMonth;
    private static int mDay;
    private static int mWeek;

    //  农历月份
    private static String[] lunarMonth = {"正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "冬月", "腊月"};

    //  农历日
    private static String[] lunarDay = {"初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十",
            "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十",
            "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十"};

    public static int getYear(int distanceDay) {

        Calendar c = getCalendar(distanceDay);
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = c.get(Calendar.YEAR);
        return mYear;
    }

    public static int getMonth(int distanceDay) {

        Calendar c = getCalendar(distanceDay);
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mMonth = c.get(Calendar.MONTH) + 1;
        return mMonth;
    }

    public static int getDay(int distanceDay) {

        Calendar c = getCalendar(distanceDay);
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mDay = c.get(Calendar.DAY_OF_MONTH);
        return mDay;
    }

    public static int getWeek(int distanceDay) {

        Calendar c = getCalendar(distanceDay);
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mWeek = c.get(Calendar.DAY_OF_WEEK);
        return mWeek;
    }

    /**
     * 获取前n天日期、后n天日期
     *
     * @param distanceDay 前几天 如获取前7天日期则传-7即可；如果后7天则传7
     * @return
     */
    public static Calendar getCalendar(int distanceDay) {

        Date beginDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + distanceDay);
        return calendar;
    }

    /**
     * 获取农历日
     *
     * @return
     */
    public static String getLunarDay(int distanceDay) {

        Calendar c = getCalendar(distanceDay);
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int[] lunarDate = LunarCalendar.solarToLunar(year, month, day);
        return lunarDay[lunarDate[2] - 1];
    }

    /**
     * 获取农历月份
     *
     * @return
     */
    public static String getLunarMonth(int distanceDay) {

        Calendar c = getCalendar(distanceDay);
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int[] lunarDate = LunarCalendar.solarToLunar(year, month, day);
        return lunarMonth[lunarDate[1] - 1];
    }
}
