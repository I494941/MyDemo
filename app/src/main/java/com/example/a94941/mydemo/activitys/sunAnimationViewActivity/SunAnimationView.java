package com.example.a94941.mydemo.activitys.sunAnimationViewActivity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.utils.ScreenUtil;

import java.text.DecimalFormat;

/**
 * Created by wjf on 2018/8/20.
 */
public class SunAnimationView extends View {

    private int mWidth; //屏幕宽度
    private int mHeight; //View 高度
    private int marginTop = 20;//离顶部的高度
    private int mCircleColor;  //圆弧颜色
    private int mFontColor;  //字体颜色
    private int mRadius;  //圆的半径
    private int mPositionColor;  //太阳的颜色

    private float mCurrentAngle; //当前旋转的角度
    private float mTotalMinute; //总时间(日落时间减去日出时间的总分钟数)
    private float mNeedMinute; //当前时间减去日出时间后的总分钟数
    private float mPercentage; //根据所给的时间算出来的百分占比
    private float positionX, positionY; //太阳图片的x、y坐标
    private float positionXStart, positionYStart; //太阳图片的x、y坐标  起点
    private float positionXEnd, positionYEnd; //太阳图片的x、y坐标  终点
    private float mFontSize;  //字体颜色

    private String mStartTime; //开始时间(日出时间)
    private String mEndTime; //结束时间（日落时间）
    private String mCurrentTime; //当前时间

    private Paint mPaint1; //画笔  虚线
    private Paint mPaint2; //画笔  实线
    private Paint mPaint3; //画笔  太阳
    private Paint mPaint4; //画笔  阴影
    private RectF mRectF; //半圆弧所在的矩形

    //内弧填充渐变
    private Shader shader;

    public SunAnimationView(Context context) {
        this(context, null);
    }

    public SunAnimationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SunAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, @Nullable AttributeSet attrs) {

        TypedArray type = context.obtainStyledAttributes(attrs, R.styleable.SunAnimationView);
        mCircleColor = type.getColor(R.styleable.SunAnimationView_sun_circle_color, getContext().getResources().getColor(R.color.orange));
        mFontColor = type.getColor(R.styleable.SunAnimationView_sun_font_color, getContext().getResources().getColor(R.color.orange));
        mPositionColor = type.getColor(R.styleable.SunAnimationView_sun_color, getContext().getResources().getColor(R.color.gold));
        mFontSize = type.getDimension(R.styleable.SunAnimationView_sun_font_size, ScreenUtil.dip2px(getContext(), 12));
        type.recycle();

        mPaint1 = new Paint();
        mPaint2 = new Paint();
        mPaint3 = new Paint();
        mPaint4 = new Paint();
    }

    public void setTimes(String startTime, String endTime, String currentTime) {
        mStartTime = startTime;
        mEndTime = endTime;
        mCurrentTime = currentTime;

        if (!isTime(mStartTime) || !isTime(mEndTime) || !isTime(mCurrentTime))
            mPercentage = 0;
        else if (!(checkTime(mStartTime, mEndTime) == 2) || !(checkTime(mStartTime, mCurrentTime) == 2))
            mPercentage = 0;
        else if (!(checkTime(mCurrentTime, mEndTime) == 2))
            mPercentage = 1;
        else {
            mTotalMinute = calculateTime(mStartTime, mEndTime);//计算总时间，单位：分钟
            mNeedMinute = calculateTime(mStartTime, mCurrentTime);//计算当前所给的时间 单位：分钟
            mPercentage = Float.parseFloat(formatTime(mTotalMinute, mNeedMinute));//当前时间的总分钟数占日出日落总分钟数的百分比
        }

        mCurrentAngle = 30 + 120 * mPercentage;
        setAnimation(30, mCurrentAngle, 100);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }
        mRadius = mWidth / 2 - 20;
        mHeight = mRadius / 2 + 40;

        positionXStart = mWidth / 2 - (float) (mRadius * Math.cos((30) * Math.PI / 180));
        positionYStart = mRadius - (float) (mRadius * Math.sin((30) * Math.PI / 180)) + 20;

        positionXEnd = mWidth / 2 + (float) (mRadius * Math.cos((30) * Math.PI / 180));
        positionYEnd = mRadius - (float) (mRadius * Math.sin((30) * Math.PI / 180)) + 20;

        positionX = positionXStart;
        positionY = positionYStart;

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //第一步：画半圆
        drawSemicircle(canvas);

        //第二步：绘制太阳的初始位置 以及 后面在动画中不断的更新太阳的X，Y坐标来改变太阳图片在视图中的显示
        drawSunPosition(canvas);

        //起点、终点
        drawPoint(canvas);

        //第三部：绘制图上的文字
        drawFont(canvas);
    }

    /**
     * 绘制半圆
     */
    private void drawSemicircle(Canvas canvas) {
        mRectF = new RectF(mWidth / 2 - mRadius, marginTop, mWidth / 2 + mRadius, mRadius * 2 + marginTop);

        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setAntiAlias(true);
        mPaint1.setDither(true);//防止抖动
        mPaint1.setStrokeWidth(5);
        mPaint1.setColor(mCircleColor);
        //绘制长度为10的实线后再绘制长度为10的空白区域，0位间隔
        mPaint1.setPathEffect(new DashPathEffect(new float[]{10, 10}, 0));
        canvas.drawArc(mRectF, 210, 120, false, mPaint1);
    }

    /**
     * 绘制起始点、结束点
     */
    private void drawPoint(Canvas canvas) {
        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setAntiAlias(true);
        mPaint2.setStrokeWidth(5);
        mPaint2.setColor(mCircleColor);
        canvas.drawCircle(positionXStart, positionYStart, 10, mPaint2);

        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setAntiAlias(true);
        mPaint2.setStrokeWidth(5);
        mPaint2.setColor(mCircleColor);
        canvas.drawCircle(positionXEnd, positionYEnd, 10, mPaint2);
    }

    /**
     * 绘制太阳的位置
     */
    private void drawSunPosition(Canvas canvas) {
        if (mCurrentAngle >= 30 && mCurrentAngle <= 150) {
            mPaint3.setStyle(Paint.Style.STROKE);
            mPaint3.setAntiAlias(true);
            mPaint3.setDither(true);//防止抖动
            mPaint3.setStrokeWidth(5);
            mPaint3.setColor(mCircleColor);
            canvas.drawArc(mRectF, 210, mCurrentAngle - 30, false, mPaint3);

            mPaint4.setStyle(Paint.Style.FILL_AND_STROKE);
            shader = new LinearGradient(mWidth / 2, marginTop, mWidth / 2, mWidth / 4, mCircleColor,
                    getResources().getColor(R.color.gray), Shader.TileMode.CLAMP);
            //绘制填充色 onDraw绘制
            mPaint4.setShader(shader);

            //画弧
            canvas.drawArc(mRectF, 210, mCurrentAngle - 30, false, mPaint4);

            //画弧下面的三角形
            Path path = new Path();
            path.moveTo(positionX, positionY);// 此点为多边形的起点
            path.lineTo(positionXStart, positionYStart);
            path.lineTo(positionX, positionYStart);
            path.close(); // 使这些点构成封闭的多边形
            canvas.drawPath(path, mPaint4);

            //画连接线
            canvas.drawLine(positionX, positionY, positionXStart, positionYStart, mPaint4);
        }

        mPaint3.setStyle(Paint.Style.FILL);
        mPaint3.setAntiAlias(true);
        mPaint3.setStrokeWidth(5);
        mPaint3.setColor(mPositionColor);

        canvas.drawCircle(positionX, positionY, 15, mPaint3);
    }

    /**
     * 绘制底部左右边的日出时间和日落时间
     *
     * @param canvas
     */
    private void drawFont(Canvas canvas) {
        mPaint2.setColor(mFontColor);
        mPaint2.setTextSize(mFontSize);
        String startTime = TextUtils.isEmpty(mStartTime) ? "" : mStartTime;
        String endTime = TextUtils.isEmpty(mEndTime) ? "" : mEndTime;
        String sunrise = "日出:" + startTime;
        String sunset = "日落:" + endTime;
        canvas.drawText(sunrise, mWidth / 2 - (float) (mRadius * Math.cos((30) * Math.PI / 180)) + 20,
                mRadius - (float) (mRadius * Math.sin((30) * Math.PI / 180)) + 20,
                mPaint2);
        canvas.drawText(sunset, mWidth / 2 + (float) (mRadius * Math.cos((30) * Math.PI / 180)) - 20 - getTextWidth(mPaint2, sunset),
                mRadius - (float) (mRadius * Math.sin((30) * Math.PI / 180)) + 20,
                mPaint2);
    }

    /**
     * 精确计算文字宽度
     *
     * @param paint 画笔
     * @param str   字符串文本
     * @return
     */
    public static int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }

    /**
     * 根据time1与time2相差的时间:单位为分钟
     *
     * @param time1
     * @param time2
     * @return
     */
    private float calculateTime(String time1, String time2) {

        String startTimes[] = time1.split(":");
        String endTimes[] = time2.split(":");

        float startHour = Float.parseFloat(startTimes[0]);
        float startMinute = Float.parseFloat(startTimes[1]);

        float endHour = Float.parseFloat(endTimes[0]);
        float endMinute = Float.parseFloat(endTimes[1]);

        float totalTime = (endHour - startHour) * 60 - startMinute + endMinute;
        return totalTime;
    }

    /**
     * 对所给的时间做一下简单的数据校验
     *
     * @param time
     * @return false:时间有误  true:无误
     */
    private boolean isTime(String time) {
        if (TextUtils.isEmpty(time) || !time.contains(":"))
            return false;

        String times[] = time.split(":");
        float hour = Float.parseFloat(times[0]);
        float minute = Float.parseFloat(times[1]);

        return !(hour < 0 || hour > 23 || minute < 0 || minute > 60);
    }

    /**
     * 对所给的时间做一下简单的数据校验
     *
     * @param time1
     * @param time2
     * @return 0:time2早于time1  1:time1 = time2  2:time2晚于time1
     */
    private int checkTime(String time1, String time2) {

        String startTimes[] = time1.split(":");
        String endTimes[] = time2.split(":");

        float startHour = Float.parseFloat(startTimes[0]);
        float startMinute = Float.parseFloat(startTimes[1]);

        float endHour = Float.parseFloat(endTimes[0]);
        float endMinute = Float.parseFloat(endTimes[1]);

        //如果所给的time2时间(hour)小于time1时间（hour）
        if (startHour > endHour)
            return 0;
        //如果所给的time2时间(minute)小于time1时间（minute）
        if (startHour == endHour && startMinute > endMinute)
            return 0;
        //如果所给的time2时间等于time1时间
        if (startHour == endHour && startMinute == endMinute)
            return 1;
        //如果所给的time2时间(minute)大于time1时间(minute)
        if (startHour == endHour && startMinute < endMinute)
            return 2;
        //如果所给的time2时间(hour)大于time1时间(hour)
        if (startHour < endHour)
            return 2;

        return 0;
    }

    /**
     * 根据具体的时间、日出日落的时间差值 计算出所给时间的百分占比
     *
     * @param totalTime 日出日落的总时间差
     * @param needTime  当前时间与日出时间差
     * @return
     */
    private String formatTime(float totalTime, float needTime) {
        if (totalTime == 0)
            return "0.00";
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//保留2位小数，构造方法的字符格式这里如果小数不足2位,会以0补足.
        return decimalFormat.format(needTime / totalTime);//format 返回的是字符串
    }

    private void setAnimation(float startAngle, float currentAngle, int duration) {
        ValueAnimator sunAnimator = ValueAnimator.ofFloat(startAngle, currentAngle);
        sunAnimator.setDuration(duration);
        sunAnimator.setTarget(currentAngle);
        sunAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //每次要绘制的圆弧角度
                mCurrentAngle = (float) animation.getAnimatedValue();
                invalidateView();
            }

        });
        sunAnimator.start();
    }

    private void invalidateView() {
        //绘制太阳的x坐标和y坐标
        positionX = mWidth / 2 - (float) (mRadius * Math.cos((mCurrentAngle) * Math.PI / 180));
        positionY = mWidth / 2 - (float) (mRadius * Math.sin((mCurrentAngle) * Math.PI / 180));

        invalidate();
    }
}