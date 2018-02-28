package com.example.a94941.mydemo.activitys.chartViewActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.a94941.mydemo.utils.LogUtils;

/**
 * @创建者 94941
 * @创建时间 2018/2/24
 * @描述 ${TODO}
 */
public class ChartView extends View {

    // 画笔
    private Paint paint;
    // 刻度之间的距离
    private int   degreeSpace;
    // 模拟数据
    private float[] data    = {3.2f, 4.3f, 2.5f, 3.2f, 3.8f, 7.1f, 1.3f, 5.6f};
    // 当前显示的数据数量
    private int     showNum = 1;

    /**
     * 构造函数
     */
    public ChartView(Context context) {
        super(context);
        initWork();
    }

    /**
     * 构造函数
     */
    public ChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initWork();
    }

    /**
     * 构造函数
     */
    public ChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWork();
    }

    /**
     * 初始化工作
     */
    private void initWork() {
        initPaint();

        // 开启线程，没隔0.5秒showNum加一
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (showNum<data.length){
                        showNum++;
                    }else {
                        showNum=1;
                    }
                    // 重绘
                    postInvalidate();
                    // 休眠0.5秒
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 画笔设置
     */
    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 画笔样式为填充
        paint.setStyle(Paint.Style.FILL);
        // 颜色设为红色
        paint.setColor(Color.RED);
        // 宽度为3像素
        paint.setStrokeWidth(3);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //        // 画圆
        //        canvas.drawCircle(300, 300, 100, paint);

        // 控件上下左右边界四至及控件的宽度(同时也是高度!)
        int left = getLeft();
        int right = getRight();
        int top = getTop();
        int bottom = getBottom();
        int w = getWidth();

        // 图表距离控件边缘的距离
        int graphPadding = w / 10;
        // 图表上下左右四至
        int graphLeft = left + graphPadding;
        int graphBottom = bottom - graphPadding;
        int graphRight = right - graphPadding;
        int graphTop = top + graphPadding;
        // 图表宽度(也等同高度奥~)
        int graphW = graphRight - graphLeft;
        // 刻度之间的距离
        degreeSpace = graphW / 8;

        // 背景
        canvas.drawColor(Color.LTGRAY);

        // 画笔设置样式为STROKE样式，即只划线不填充
        paint.setStyle(Paint.Style.STROKE);

        // 坐标系绘制
        Path pivotPath = new Path();
        //Y轴
        pivotPath.moveTo(graphLeft, graphBottom);
        pivotPath.lineTo(graphLeft, graphTop);
        //Y轴箭头
        pivotPath.lineTo(graphLeft - 12, graphTop + 20);
        pivotPath.moveTo(graphLeft, graphTop);
        pivotPath.lineTo(graphLeft + 12, graphTop + 20);
        //X轴
        pivotPath.moveTo(graphLeft, graphBottom);
        pivotPath.lineTo(graphRight, graphBottom);
        //X轴箭头
        pivotPath.lineTo(graphRight - 20, graphBottom + 12);
        pivotPath.moveTo(graphRight, graphBottom);
        pivotPath.lineTo(graphRight - 20, graphBottom - 12);
        canvas.drawPath(pivotPath, paint);

        // Y轴刻度虚线
        for (int i = 1; i < 8; i++) {
            Path yKeduPath = new Path();
            // 线
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(1);
            paint.setStyle(Paint.Style.STROKE);
            paint.setPathEffect(new DashPathEffect(new float[]{5, 5}, 0));
            yKeduPath.moveTo(graphLeft, graphBottom - i * degreeSpace);
            yKeduPath.lineTo(graphRight, graphBottom - i * degreeSpace);
            canvas.drawPath(yKeduPath, paint);
            // 数字
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(25);
            paint.setPathEffect(null);
            canvas.drawText(i + "", graphPadding / 2, graphBottom - i * degreeSpace, paint);
        }
        // X轴刻度虚线
        for (int i = 1; i < 8; i++) {
            Path xKeduPath = new Path();
            // 线
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(1);
            paint.setPathEffect(new DashPathEffect(new float[]{5, 5}, 0));
            xKeduPath.moveTo(graphLeft + i * degreeSpace, graphBottom);
            xKeduPath.lineTo(graphLeft + i * degreeSpace, graphTop);
            canvas.drawPath(xKeduPath, paint);
            // 数字
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(25);
            paint.setPathEffect(null);
            canvas.drawText(i + "", graphLeft + i * degreeSpace, graphBottom + graphPadding / 2, paint);
        }
        // 折线
        Path linePath = new Path();
        for (int i = 0; i < showNum; i++) {
            int toPointX = graphLeft + i * degreeSpace;
            int toPointY = graphBottom - ((int) (data[i] * degreeSpace));
            paint.setColor(Color.YELLOW);
            paint.setStyle(Paint.Style.STROKE);
            if (i == 0) {
                linePath.moveTo(toPointX, toPointY);
            } else {
                linePath.lineTo(toPointX, toPointY);
            }
            // 节点圆圈
            canvas.drawCircle(toPointX, toPointY, 10, paint);
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(toPointX, toPointY, 7, paint);
        }
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        canvas.drawPath(linePath, paint);
    }
}
