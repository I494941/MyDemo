package com.example.a94941.mydemo.base;

import android.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a94941.mydemo.R;

import butterknife.OnClick;

/**
 * Created by ws on 2017/10/26 0026.
 */

public abstract class BaseToolbarActivity extends BaseAppCompatActivity {
    protected Toolbar  mToolbar;
    protected TextView mTvTitle, mTvLeft, mTvRight;
    protected ImageView mIvRight;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = (Toolbar) findViewById(R.id.common_toolbar);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvLeft = (TextView) findViewById(R.id.tv_left);
        mTvRight = (TextView) findViewById(R.id.tv_right);
        mIvRight = (ImageView) findViewById(R.id.iv_right);

        initToolbar();
    }

    public void initToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle("");
            //getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationIcon(R.mipmap.back);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            back();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void back() {
        finish();
    }


    @OnClick(R.id.iv_right)
    void tip() {

        View view = getLayoutInflater().inflate(R.layout.dialog, null);
        TextView mTv = view.findViewById(R.id.tv_content);
        mTv.setText(getContent());

        new AlertDialog.Builder(this)
                .setTitle("简介")
                .setView(view)
                .show();
    }

    protected  String getContent(){
        return "";
    }
}
