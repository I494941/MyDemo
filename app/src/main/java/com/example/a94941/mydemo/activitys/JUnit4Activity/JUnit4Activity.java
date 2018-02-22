package com.example.a94941.mydemo.activitys.JUnit4Activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

/**
 * @创建者 94941
 * @创建时间 2018/2/22
 * @描述 ${TODO}
 */
public class JUnit4Activity extends BaseToolbarActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_junit4;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("JUnit4");
        mIvRight.setVisibility(View.VISIBLE);
    }

    public void sayHello(View v) {
        TextView textView = (TextView) findViewById(R.id.textView);
        EditText editText = (EditText) findViewById(R.id.editText);
        textView.setText("Hello, " + editText.getText().toString() + "!");
    }

    @Override
    protected String getContent() {
        return "单元测试，运行androidTest目录下JUnit4ActivityTest";
    }
}
