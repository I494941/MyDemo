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
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.utils.CommonUtils;

import java.text.DecimalFormat;

/**
 * Created by wjf on 2018/8/20.
 */

public class SunAnimationView extends View {

    private int mWidth; //屏幕宽度
    private int mHight; //View 高度
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
    private WindowManager wm;

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
        mCircleColor = type.getColor(R.styleable.SunAnimationView_sun_circle_color, getContext().getResources().getColor(R.color.red));
        mFontColor = type.getColor(R.styleable.SunAnimationView_sun_font_color, getContext().getResources().getColor(R.color.green));
        mPositionColor = type.getColor(R.styleable.SunAnimationView_sun_color, getContext().getResources().getColor(R.color.yellow));
        mFontSize = type.getDimension(R.styleable.SunAnimationView_sun_font_size, CommonUtils.dp2px(getContext(), 12));
        mFontSize = CommonUtils.dp2px(getContext(), mFontSize);
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

        mTotalMinute = calculateTime(mStartTime, mEndTime);//计算总时间，单位：分钟
        mNeedMinute = calculateTime(mStartTime, mCurrentTime);//计算当前所给的时间 单位：分钟

        if (-1 == mNeedMinute) {
            mPercentage = 1;
        } else
            mPercentage = Float.parseFloat(formatTime(mTotalMinute, mNeedMinute));//当前时间的总分钟数占日出日落总分钟数的百分比
        mCurrentAngle = 30 + 120 * mPercentage;

        setAnimation(30, mCurrentAngle, 100);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //获取SingleTouchView所在父布局的中心点
        ViewGroup mViewGroup = (ViewGroup) getParent();
        if (null != mViewGroup) {
            mWidth = mViewGroup.getWidth();
        } else {
            wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            mWidth = wm.getDefaultDisplay().getWidth();
        }
        mRadius = mWidth / 2 - 20;
        mHight = mRadius / 2 + 40;

        positionXStart = mWidth / 2 - (float) (mRadius * Math.cos((30) * Math.PI / 180));
        positionYStart = mRadius - (float) (mRadius * Math.sin((30) * Math.PI / 180)) + 20;

        positionXEnd = mWidth / 2 + (float) (mRadius * Math.cos((30) * Math.PI / 180));
        positionYEnd = mRadius - (float) (mRadius * Math.sin((30) * Math.PI / 180)) + 20;

        positionX = positionXStart;
        positionY = positionYStart;

        setMeasuredDimension(mWidth, mHight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, mWidth / 2 - mRadius, marginTop, mWidth / 2 + mRadius, mRadius + marginTop);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //第一步：画半圆
        drawSemicircle(canvas);
        canvas.save();

        //第二步：绘制太阳的初始位置 以及 后面在动画中不断的更新太阳的X，Y坐标来改变太阳图片在视图中的显示
        drawSunPosition(canvas);

        //起点、终点
        drawPoint(canvas);

        //第三部：绘制图上的文字
        drawFont(canvas);

        super.onDraw(canvas);
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
                    getResources().getColor(R.color.white), Shader.TileMode.CLAMP);
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
     * 根据日出和日落时间计算出一天总共的时间:单位为分钟
     *
     * @param startTime 日出时间
     * @param endTime   日落时间
     * @return
     */
    private float calculateTime(String startTime, String endTime) {

        if (1 == checkTime(startTime, endTime)) {
            String startTimes[] = startTime.split(":");
            String endTimes[] = endTime.split(":");

            float startHour = Float.parseFloat(startTimes[0]);
            float startMinute = Float.parseFloat(startTimes[1]);

            float endHour = Float.parseFloat(endTimes[0]);
            float endMinute = Float.parseFloat(endTimes[1]);

            float needTime = (endHour - startHour - 1) * 60 + (60 - startMinute) + endMinute;
            return needTime;
        } else if (2 == checkTime(startTime, endTime)) {
            return -1;
        }
        return 0;
    }

    /**
     * 对所给的时间做一下简单的数据校验
     *
     * @param startTime
     * @param endTime
     * @return 0:早于日出 或时间有误  1:在日出日落之间  2:晚于日落
     */
    private int checkTime(String startTime, String endTime) {
        if (TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime)
                || !startTime.contains(":") || !endTime.contains(":")) {
            return 0;
        }

        String startTimes[] = startTime.split(":");
        String endTimes[] = endTime.split(":");
        float startHour = Float.parseFloat(startTimes[0]);
        float startMinute = Float.parseFloat(startTimes[1]);

        float endHour = Float.parseFloat(endTimes[0]);
        float endMinute = Float.parseFloat(endTimes[1]);

        //如果所给的时间(hour)小于日出时间（hour）
        if ((startHour < Float.parseFloat(mStartTime.split(":")[0]))) {
            return 0;
        }
        //如果所给的时间(hour)大于日落时间（hour）
        if ((endHour > Float.parseFloat(mEndTime.split(":")[0]))) {
            return 2;
        }

        //如果所给时间与日出时间：hour相等，minute小于日出时间
        if ((startHour == Float.parseFloat(mStartTime.split(":")[0]))
                && (startMinute < Float.parseFloat(mStartTime.split(":")[1]))) {
            return 0;
        }

        //如果所给时间与日落时间：hour相等，minute大于日落时间
        if ((startHour == Float.parseFloat(mEndTime.split(":")[0]))
                && (endMinute > Float.parseFloat(mEndTime.split(":")[1]))) {
            return 2;
        }

        if (startHour < 0 || endHour < 0
                || startHour > 23 || endHour > 23
                || startMinute < 0 || endMinute < 0
                || startMinute > 60 || endMinute > 60) {
            return 0;
        }
        return 1;
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
