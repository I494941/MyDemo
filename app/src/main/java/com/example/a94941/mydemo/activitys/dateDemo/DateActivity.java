package com.example.a94941.mydemo.activitys.dateDemo;

import android.graphics.Color;
import android.widget.TextView;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

import butterknife.BindView;

/**
 * @创建者 94941
 * @创建时间 2018/2/2
 * @描述 ${TODO}
 */
public class DateActivity extends BaseToolbarActivity {

    @BindView(R.id.tv_day1)
    TextView mTvDay1;
    @BindView(R.id.tv_day2)
    TextView mTvDay2;
    @BindView(R.id.tv_day3)
    TextView mTvDay3;
    @BindView(R.id.tv_day4)
    TextView mTvDay4;
    @BindView(R.id.tv_day5)
    TextView mTvDay5;
    @BindView(R.id.tv_day6)
    TextView mTvDay6;
    @BindView(R.id.tv_day7)
    TextView mTvDay7;
    @BindView(R.id.tv_lunarday1)
    TextView mTvLunarday1;
    @BindView(R.id.tv_lunarday2)
    TextView mTvLunarday2;
    @BindView(R.id.tv_lunarday3)
    TextView mTvLunarday3;
    @BindView(R.id.tv_lunarday4)
    TextView mTvLunarday4;
    @BindView(R.id.tv_lunarday5)
    TextView mTvLunarday5;
    @BindView(R.id.tv_lunarday6)
    TextView mTvLunarday6;
    @BindView(R.id.tv_lunarday7)
    TextView mTvLunarday7;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_date;
    }

    @Override
    protected void initViewsAndEvents() {

        if (1 == DateUtils.getWeek(0)) {
            mTvDay1.setBackgroundColor(Color.BLUE);
            mTvLunarday1.setBackgroundColor(Color.BLUE);
            mTvDay1.setText(DateUtils.getDay(0) + "");
            mTvDay2.setText(DateUtils.getDay(1) + "");
            mTvDay3.setText(DateUtils.getDay(2) + "");
            mTvDay4.setText(DateUtils.getDay(3) + "");
            mTvDay5.setText(DateUtils.getDay(4) + "");
            mTvDay6.setText(DateUtils.getDay(5) + "");
            mTvDay7.setText(DateUtils.getDay(6) + "");
            mTvLunarday1.setText(DateUtils.getLunarDay(0) + "");
            mTvLunarday2.setText(DateUtils.getLunarDay(1) + "");
            mTvLunarday3.setText(DateUtils.getLunarDay(2) + "");
            mTvLunarday4.setText(DateUtils.getLunarDay(3) + "");
            mTvLunarday5.setText(DateUtils.getLunarDay(4) + "");
            mTvLunarday6.setText(DateUtils.getLunarDay(5) + "");
            mTvLunarday7.setText(DateUtils.getLunarDay(6) + "");
        } else if (2 == DateUtils.getWeek(0)) {
            mTvDay2.setBackgroundColor(Color.BLUE);
            mTvLunarday2.setBackgroundColor(Color.BLUE);
            mTvDay2.setText(DateUtils.getDay(0) + "");
            mTvDay3.setText(DateUtils.getDay(1) + "");
            mTvDay4.setText(DateUtils.getDay(2) + "");
            mTvDay5.setText(DateUtils.getDay(3) + "");
            mTvDay6.setText(DateUtils.getDay(4) + "");
            mTvDay7.setText(DateUtils.getDay(5) + "");
            mTvDay1.setText(DateUtils.getDay(-1) + "");
            mTvLunarday2.setText(DateUtils.getLunarDay(0) + "");
            mTvLunarday3.setText(DateUtils.getLunarDay(1) + "");
            mTvLunarday4.setText(DateUtils.getLunarDay(2) + "");
            mTvLunarday5.setText(DateUtils.getLunarDay(3) + "");
            mTvLunarday6.setText(DateUtils.getLunarDay(4) + "");
            mTvLunarday7.setText(DateUtils.getLunarDay(5) + "");
            mTvLunarday1.setText(DateUtils.getLunarDay(-1) + "");
        } else if (3 == DateUtils.getWeek(0)) {
            mTvDay3.setBackgroundColor(Color.BLUE);
            mTvLunarday3.setBackgroundColor(Color.BLUE);
            mTvDay3.setText(DateUtils.getDay(0) + "");
            mTvDay4.setText(DateUtils.getDay(1) + "");
            mTvDay5.setText(DateUtils.getDay(2) + "");
            mTvDay6.setText(DateUtils.getDay(3) + "");
            mTvDay7.setText(DateUtils.getDay(4) + "");
            mTvDay1.setText(DateUtils.getDay(-2) + "");
            mTvDay2.setText(DateUtils.getDay(-1) + "");
            mTvLunarday3.setText(DateUtils.getLunarDay(0) + "");
            mTvLunarday4.setText(DateUtils.getLunarDay(1) + "");
            mTvLunarday5.setText(DateUtils.getLunarDay(2) + "");
            mTvLunarday6.setText(DateUtils.getLunarDay(3) + "");
            mTvLunarday7.setText(DateUtils.getLunarDay(4) + "");
            mTvLunarday1.setText(DateUtils.getLunarDay(-2) + "");
            mTvLunarday2.setText(DateUtils.getLunarDay(-1) + "");
        } else if (4 == DateUtils.getWeek(0)) {
            mTvDay4.setBackgroundColor(Color.BLUE);
            mTvLunarday4.setBackgroundColor(Color.BLUE);
            mTvDay4.setText(DateUtils.getDay(0) + "");
            mTvDay5.setText(DateUtils.getDay(1) + "");
            mTvDay6.setText(DateUtils.getDay(2) + "");
            mTvDay7.setText(DateUtils.getDay(3) + "");
            mTvDay1.setText(DateUtils.getDay(-3) + "");
            mTvDay2.setText(DateUtils.getDay(-2) + "");
            mTvDay3.setText(DateUtils.getDay(-1) + "");
            mTvLunarday4.setText(DateUtils.getLunarDay(0) + "");
            mTvLunarday5.setText(DateUtils.getLunarDay(1) + "");
            mTvLunarday6.setText(DateUtils.getLunarDay(2) + "");
            mTvLunarday7.setText(DateUtils.getLunarDay(3) + "");
            mTvLunarday1.setText(DateUtils.getLunarDay(-3) + "");
            mTvLunarday2.setText(DateUtils.getLunarDay(-2) + "");
            mTvLunarday3.setText(DateUtils.getLunarDay(-1) + "");
        } else if (5 == DateUtils.getWeek(0)) {
            mTvDay5.setBackgroundColor(Color.BLUE);
            mTvLunarday5.setBackgroundColor(Color.BLUE);
            mTvDay5.setText(DateUtils.getDay(0) + "");
            mTvDay6.setText(DateUtils.getDay(1) + "");
            mTvDay7.setText(DateUtils.getDay(2) + "");
            mTvDay1.setText(DateUtils.getDay(-4) + "");
            mTvDay2.setText(DateUtils.getDay(-3) + "");
            mTvDay3.setText(DateUtils.getDay(-2) + "");
            mTvDay4.setText(DateUtils.getDay(-1) + "");
            mTvLunarday5.setText(DateUtils.getLunarDay(0) + "");
            mTvLunarday6.setText(DateUtils.getLunarDay(1) + "");
            mTvLunarday7.setText(DateUtils.getLunarDay(2) + "");
            mTvLunarday1.setText(DateUtils.getLunarDay(-4) + "");
            mTvLunarday2.setText(DateUtils.getLunarDay(-3) + "");
            mTvLunarday3.setText(DateUtils.getLunarDay(-2) + "");
            mTvLunarday4.setText(DateUtils.getLunarDay(-1) + "");
        } else if (6 == DateUtils.getWeek(0)) {
            mTvDay6.setBackgroundColor(Color.BLUE);
            mTvLunarday6.setBackgroundColor(Color.BLUE);
            mTvDay6.setText(DateUtils.getDay(0) + "");
            mTvDay7.setText(DateUtils.getDay(1) + "");
            mTvDay1.setText(DateUtils.getDay(-5) + "");
            mTvDay2.setText(DateUtils.getDay(-4) + "");
            mTvDay3.setText(DateUtils.getDay(-3) + "");
            mTvDay4.setText(DateUtils.getDay(-2) + "");
            mTvDay5.setText(DateUtils.getDay(-1) + "");
            mTvLunarday6.setText(DateUtils.getLunarDay(0) + "");
            mTvLunarday7.setText(DateUtils.getLunarDay(1) + "");
            mTvLunarday1.setText(DateUtils.getLunarDay(-5) + "");
            mTvLunarday2.setText(DateUtils.getLunarDay(-4) + "");
            mTvLunarday3.setText(DateUtils.getLunarDay(-3) + "");
            mTvLunarday4.setText(DateUtils.getLunarDay(-2) + "");
            mTvLunarday5.setText(DateUtils.getLunarDay(-1) + "");
        } else {
            mTvDay7.setBackgroundColor(Color.BLUE);
            mTvLunarday7.setBackgroundColor(Color.BLUE);
            mTvDay7.setText(DateUtils.getDay(0) + "");
            mTvDay1.setText(DateUtils.getDay(-6) + "");
            mTvDay2.setText(DateUtils.getDay(-5) + "");
            mTvDay3.setText(DateUtils.getDay(-4) + "");
            mTvDay4.setText(DateUtils.getDay(-3) + "");
            mTvDay5.setText(DateUtils.getDay(-2) + "");
            mTvDay6.setText(DateUtils.getDay(-1) + "");
            mTvLunarday7.setText(DateUtils.getLunarDay(0) + "");
            mTvLunarday1.setText(DateUtils.getLunarDay(-6) + "");
            mTvLunarday2.setText(DateUtils.getLunarDay(-5) + "");
            mTvLunarday3.setText(DateUtils.getLunarDay(-4) + "");
            mTvLunarday4.setText(DateUtils.getLunarDay(-3) + "");
            mTvLunarday5.setText(DateUtils.getLunarDay(-2) + "");
            mTvLunarday6.setText(DateUtils.getLunarDay(-1) + "");
        }
    }
}
