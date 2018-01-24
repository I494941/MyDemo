package com.example.a94941.mydemo.activitys.myView.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.a94941.mydemo.R;

import java.util.ArrayList;

/**
 * @创建者 94941
 * @创建时间 2018/1/22
 * @描述 ${TODO}
 */
public class AuditProgressView extends View {

    private Context mContext;

    private int progressCount = 4; // 进度个数
    private int progress      = 2; //  当前进度
    private boolean isProgress;// 当前进度是成功还是失败

    private int   textColor; // 进度文字的颜色
    private int   lineColor; // 线的颜色
    private int   timeTextColor; // 时间文字的颜色
    private float imageWidth; // 图片宽高
    private float lineHight; // 线的高度
    private float textSize; // 进度文字展示
    private float timeTextSize; //时间 文字

    private RectF imageRectF; // 图片绘制的区域
    private Paint paint; // 画笔

    /**
     * 第一个图的坐标
     */
    private float startX;
    private float startY;

    private ArrayList<String> tTextList; // 时间文字list
    private ArrayList<String> textList; // 进度文字list

    /*
       * view的高度
       */
    private int height;
    private int width;

    //  确定绘制的图片
    private Bitmap auditDrawBitmap;

    public AuditProgressView(Context context) {
        super(context);
    }

    public AuditProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        getAtt(attrs);
        initPaint();
    }

    public AuditProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void getAtt(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.AuditProgressView);
        progressCount = typedArray.getInt(R.styleable.AuditProgressView_progressCount, progressCount);
        textColor = typedArray.getColor(R.styleable.AuditProgressView_textColor, getResources().getColor(R.color.red));
        lineColor = typedArray.getColor(R.styleable.AuditProgressView_lineColor, getResources().getColor(R.color.green));
        timeTextColor = typedArray.getColor(R.styleable.AuditProgressView_timeTextColor, getResources().getColor(R.color.blue));
        imageWidth = typedArray.getDimension(R.styleable.AuditProgressView_imageWidth, 100);
        lineHight = typedArray.getDimension(R.styleable.AuditProgressView_lineHight, 10);
        textSize = typedArray.getDimension(R.styleable.AuditProgressView_textSize, 10);
        timeTextSize = typedArray.getDimension(R.styleable.AuditProgressView_timeTextSize, 10);
        typedArray.recycle();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        imageRectF = new RectF();
        paint = new Paint();
        // 抗锯齿
        paint.setAntiAlias(true);
        // 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        paint.setDither(true);
        // 文字居中
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        startX = imageWidth + imageWidth / 2;
        startY = imageWidth + imageWidth / 2;
        paint.setTextSize(textSize);
        if (tTextList.size() > 0 && textList.size() > 0) {
            drawLine(canvas);
            drawImage(canvas);
            drawText(canvas);
            drawTimeText(canvas);
        }
    }

    // 画线
    private void drawLine(Canvas canvas) {
        for (int i = 0; i < progressCount - 1; i++) {
            if (i <= progress - 2) {
                paint.setColor(lineColor);
            } else {
                paint.setColor(Color.BLACK);
            }
            canvas.drawLine(2 * imageWidth / 3 + startX + i * (width - 2 * (imageWidth + imageWidth / 2)) / (progressCount - 1),
                    startY, startX - 2 * imageWidth / 3 + (i + 1) * (width - 2 * (imageWidth + imageWidth / 2)) / (  - 1), startY, paint);
        }
    }

    // 画图片
    private void drawImage(Canvas canvas) {
        for (int i = 0; i < progressCount; i++) {
            if (i <= progress - 1) {
                auditDrawBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.green);
                if (i == progress - 1 && !isProgress) {
                    auditDrawBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.red);
                }
            } else {
                auditDrawBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.gray);
            }
            // 绘制图片
            imageRectF.set((float) (startX - imageWidth / 2 + i * (width - 2 * (imageWidth + imageWidth / 2)) / (progressCount - 1)),
                    (float) (startY - imageWidth / 2), (float) (startX + imageWidth / 2 + i * (width - 2 * (imageWidth + imageWidth / 2)) / (progressCount - 1)), (float) (startY + imageWidth / 2));
            canvas.drawBitmap(auditDrawBitmap, null, imageRectF, null);
        }
    }

    // 画时间文本
    private void drawTimeText(Canvas canvas) {
        paint.setColor(timeTextColor);
        for (int i = 0; i < progressCount; i++) {
            canvas.drawText(textList.get(i), startX + i * (width - 2 * (imageWidth + imageWidth / 2)) / (progressCount - 1), 2 * startY, paint);
        }
    }

    // 画文本
    private void drawText(Canvas canvas) {
        paint.setColor(textColor);
        for (int i = 0; i < progressCount; i++) {
            canvas.drawText(tTextList.get(i), startX + i * (width - 2 * (imageWidth + imageWidth / 2)) / (progressCount - 1), 3 * startY, paint);
        }
    }

    public void setDate(ArrayList<String> mtextlist, ArrayList<String> mttextlist, int mProgress, boolean isprogress) {
        textList = mtextlist;
        tTextList = mttextlist;
        progress = mProgress;
        isProgress = isprogress;
        invalidate();
    }
}
