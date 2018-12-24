package com.example.a94941.mydemo.activitys.jellyToolbar;

import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.LayoutInflater;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseAppCompatActivity;
import com.yalantis.jellytoolbar.listener.JellyListener;
import com.yalantis.jellytoolbar.widget.JellyToolbar;

import butterknife.BindView;

/**
 * Created by wjf on 2018/12/24.
 */
public class JellyToolbarActivity extends BaseAppCompatActivity {

    @BindView(R.id.toolbar)
    JellyToolbar toolbar;

    private AppCompatEditText editText;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_jellytoolbar;
    }

    @Override
    protected void initViewsAndEvents() {

//        toolbar.getToolbar().setNavigationIcon(R.mipmap.ic_menu);
        toolbar.setJellyListener(jellyListener);

        editText = (AppCompatEditText) LayoutInflater.from(this).inflate(R.layout.edit_text, null);
        editText.setBackgroundResource(R.color.transparent);
        toolbar.setContentView(editText);
    }

    private JellyListener jellyListener = new JellyListener() {
        @Override
        public void onCancelIconClicked() {
            if (TextUtils.isEmpty(editText.getText())) {
                toolbar.collapse();
            } else {
                editText.getText().clear();
            }
        }
    };
}
