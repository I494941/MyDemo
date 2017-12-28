package com.example.a94941.mydemo.activitys.superTextView;

import android.view.View;
import android.widget.Button;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 94941 on 2017/12/26.
 */

public class SuperTextViewActivity extends BaseToolbarActivity {

    @BindView(R.id.button0)
    Button mButton0;
    @BindView(R.id.button1)
    Button mButton1;
    @BindView(R.id.button2)
    Button mButton2;
    @BindView(R.id.button3)
    Button mButton3;
    @BindView(R.id.button4)
    Button mButton4;
    @BindView(R.id.button5)
    Button mButton5;
    @BindView(R.id.button6)
    Button mButton6;
    @BindView(R.id.button7)
    Button mButton7;
    @BindView(R.id.button8)
    Button mButton8;
    @BindView(R.id.list_button)
    Button mListButton;
    @BindView(R.id.click_button)
    Button mClickButton;
    @BindView(R.id.super_button)
    Button mSuperButton;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_super_textview;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("SuperTextView");
        mIvRight.setVisibility(View.VISIBLE);
    }

    @Override
    protected String getContent() {
        return "\n" +
                "    SuperTextView是一个功能强大的View，可以满足日常大部分布局样式，开发者可已自行组合属性配置出属于自己风格的样式!可能描述起来没有概念，还是直接看效果图吧！\n" +
                "\n" +
                "    SuperButton拥有shape文件的大部分属性，从此写shape属性变得非常简单\n" +
                "\n" +
                "    CommonTextView只是SuperTextView的逻辑简化，其实功能并不差少哦，有兴趣的可以看看\n";
    }

    @OnClick({R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
            R.id.button6, R.id.button7, R.id.button8, R.id.list_button, R.id.click_button, R.id.super_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button0:
                readyGo(Type0Activity.class);
                break;
            case R.id.button1:
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
            case R.id.button5:
                break;
            case R.id.button6:
                break;
            case R.id.button7:
                break;
            case R.id.button8:
                break;
            case R.id.list_button:
                break;
            case R.id.click_button:
                break;
            case R.id.super_button:
                break;
        }
    }
}
