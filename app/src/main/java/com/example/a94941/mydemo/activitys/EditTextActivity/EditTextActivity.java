package com.example.a94941.mydemo.activitys.EditTextActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;
import com.example.a94941.mydemo.utils.LogUtils;

import butterknife.BindView;

/**
 * Created by wjf on 2018/5/11.
 */

public class EditTextActivity extends BaseToolbarActivity {

    @BindView(R.id.et)
    EditText mEt;
    @BindView(R.id.et0)
    EditText mEt0;
    @BindView(R.id.et1)
    EditText mEt1;
    @BindView(R.id.et2)
    EditText mEt2;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_edittext;
    }

    @Override
    protected void initViewsAndEvents() {

        mTvTitle.setText("EditText.addTextChangedListener 用它来监听用户输入状态");
        mEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                //text  输入框中改变后的字符串信息
                //start 输入框中改变后的字符串的起始位置
                //before 输入框中改变前的字符串的位置 默认为0
                //count 输入框中改变后的一共输入字符串的数量
                mEt0.setText("输入后字符串 [ " + text.toString() + " ] 起始光标 [ " + start + " ] 输入数量 [ " + count + " ]");
                LogUtils.e("111111111111111111111",text.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
                //text  输入框中改变前的字符串信息
                //start 输入框中改变前的字符串的起始位置
                //count 输入框中改变前后的字符串改变数量一般为0
                //after 输入框中改变后的字符串与起始位置的偏移量
                System.out.println(text.toString());
                mEt1.setText("输入前字符串 [ " + text.toString() + " ]起始光标 [ " + start + " ]结束偏移量  [" + after + " ]");
            }

            @Override
            public void afterTextChanged(Editable edit) {
                //edit  输入结束呈现在输入框中的信息
                mEt2.setText("输入结束后的内容为 [" + edit.toString() + " ] 即将显示在屏幕上");
            }
        });
    }
}
