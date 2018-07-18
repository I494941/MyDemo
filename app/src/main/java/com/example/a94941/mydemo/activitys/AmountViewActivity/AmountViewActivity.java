package com.example.a94941.mydemo.activitys.AmountViewActivity;

import android.view.View;
import android.widget.Toast;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

import butterknife.BindView;

/**
 * Created by wjf on 2018/5/11.
 */

public class AmountViewActivity extends BaseToolbarActivity {

    @BindView(R.id.amount_view)
    AmountView mAmountView;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_amountview;
    }

    @Override
    protected void initViewsAndEvents() {

        mTvTitle.setText("AmountView");
        mAmountView.setGoods_storage(50);
        mAmountView.setAmount(10);
        mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                Toast.makeText(getApplicationContext(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
