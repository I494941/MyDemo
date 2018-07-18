package com.example.a94941.mydemo.activitys.androidView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.SearchView;
import android.widget.Switch;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;
import com.example.a94941.mydemo.utils.LogUtils;
import com.example.a94941.mydemo.utils.ToastUtils;

import butterknife.BindView;

/**
 * @创建者 94941
 * @创建时间 2018/4/20
 * @描述 ${TODO}
 */
public class AndroidViewActivity extends BaseToolbarActivity {

    @BindView(R.id.searchview)
    SearchView           mSearchView;
    @BindView(R.id.switchs)
    Switch               mSwitch;
    @BindView(R.id.autocompletetextview)
    AutoCompleteTextView mAutoCompleteTextView;

    final String[] arr = {"1001", "1002", "1003", "1004"};

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_android_view;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("AndroidView");
        mIvRight.setVisibility(View.VISIBLE);

        initSearchView();
        initSwitch();
        initAutoCompleteTextView();
    }

    @Override
    protected String getContent() {
        return "Android中那些你忽略的控件：\n" +
                "\n" +
                "1、SearchView\n" +
                "\n" +
                "2、Switch\n" +
                "\n" +
                "3、AutoCompleteTextView\n" +
                "\n" +
                "4、CalendarViewActivity\n" +
                "\n" +
                "5、DatePicker\n" +
                "\n" +
                "6、Chronomet\n" +
                "\n" +
                "7、ExpandableListView\n" +
                "\n" +
                "8、ViewSwitcher";
    }

    public void initSearchView() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //点击搜索
            @Override
            public boolean onQueryTextSubmit(String query) {
                LogUtils.e("点击搜索", query);
                return false;
            }

            //搜索内容改变
            @Override
            public boolean onQueryTextChange(String newText) {
                LogUtils.e("搜索内容改变", newText);
                return false;
            }
        });
    }

    public void initSwitch() {
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    //开
                    ToastUtils.showToast("开");
                } else {
                    //关
                    ToastUtils.showToast("关");
                }

            }
        });
    }

    public void initAutoCompleteTextView() {

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, arr);
        //设置Adapter
        mAutoCompleteTextView.setAdapter(arrayAdapter);
        //监听item选择
        mAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showToast(arr[position]);
            }
        });
    }
}
