package com.example.a94941.mydemo.activitys.stepViewDemo;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

import butterknife.BindView;

/**
 * @创建者 94941
 * @创建时间 2018/4/4
 * @描述 ${TODO}
 */
public class StepViewActivity extends BaseToolbarActivity {

    @BindView(R.id.click1)
    CheckBox mClick1;
    @BindView(R.id.text1)
    CheckBox mText1;
    @BindView(R.id.step1)
    StepView mStep1;
    @BindView(R.id.click2)
    CheckBox mClick2;
    @BindView(R.id.text2)
    CheckBox mText2;
    @BindView(R.id.step2)
    StepView mStep2;
    @BindView(R.id.click3)
    CheckBox mClick3;
    @BindView(R.id.text3)
    CheckBox mText3;
    @BindView(R.id.step3)
    StepView mStep3;
    @BindView(R.id.click4)
    CheckBox mClick4;
    @BindView(R.id.text4)
    CheckBox mText4;
    @BindView(R.id.step4)
    StepView mStep4;
    @BindView(R.id.click5)
    CheckBox mClick5;
    @BindView(R.id.text5)
    CheckBox mText5;
    @BindView(R.id.step5)
    StepView mStep5;
    @BindView(R.id.click6)
    CheckBox mClick6;
    @BindView(R.id.text6)
    CheckBox mText6;
    @BindView(R.id.step6)
    StepView mStep6;

    private String[] texts = {"确认身份信息", "确认入住信息", "选择房型", "支付押金", "完成入住"};

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_stepview;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("StepView");
        mIvRight.setVisibility(View.VISIBLE);

        mStep1.setDescription(texts);
        mStep2.setDescription(texts);
        mStep3.setDescription(texts);
        mStep4.setDescription(texts);
        mStep5.setDescription(texts);
        mStep6.setDescription(texts);

        mStep1.setStep(StepView.Step.ONE);
        mStep2.setStep(StepView.Step.TWO);
        mStep3.setStep(StepView.Step.THREE);
        mStep4.setStep(StepView.Step.FOUR);
        mStep5.setStep(StepView.Step.FIVE);
        mStep6.setStep(StepView.Step.FOUR);

        clickableChaged(mClick1, mStep1);
        clickableChaged(mClick2, mStep2);
        clickableChaged(mClick3, mStep3);
        clickableChaged(mClick4, mStep4);
        clickableChaged(mClick5, mStep5);
        clickableChaged(mClick6, mStep6);

        textLocationChanged(mText1, mStep1);
        textLocationChanged(mText2, mStep2);
        textLocationChanged(mText3, mStep3);
        textLocationChanged(mText4, mStep4);
        textLocationChanged(mText5, mStep5);
        textLocationChanged(mText6, mStep6);
    }

    @Override
    protected String getContent() {
        return "设置横屏，竖屏时字体显示不全";
    }

    private void clickableChaged(CheckBox check, final StepView step) {
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                step.setClickable(isChecked);
            }
        });
    }

    private void textLocationChanged(CheckBox check, final StepView step) {
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                step.setTextBelowLine(!isChecked);
            }
        });
    }
}

