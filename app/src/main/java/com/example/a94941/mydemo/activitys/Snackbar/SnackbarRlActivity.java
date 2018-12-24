package com.example.a94941.mydemo.activitys.Snackbar;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

import butterknife.OnClick;

/**
 * Created by wjf on 2018/12/5.
 */
public class SnackbarRlActivity extends BaseToolbarActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_snackbar_rl;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @OnClick({R.id.btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:
                Snackbar.make(view, "已删除一个会话", Snackbar.LENGTH_SHORT).setAction("撤销", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SnackbarRlActivity.this, "撤销了删除", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
        }
    }
}
