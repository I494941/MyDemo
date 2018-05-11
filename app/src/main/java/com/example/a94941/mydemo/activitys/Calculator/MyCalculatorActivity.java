package com.example.a94941.mydemo.activitys.Calculator;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wjf on 2018/5/10.
 */

public class MyCalculatorActivity extends BaseToolbarActivity {

    @BindView(R.id.et_result)
    EditText mEtResult;
    @BindView(R.id.num_clear)
    TextView mNumClear;
    @BindView(R.id.num_del)
    TextView mNumDel;
    @BindView(R.id.num_plus)
    TextView mNumPlus;
    @BindView(R.id.num_1)
    TextView mNum1;
    @BindView(R.id.num_2)
    TextView mNum2;
    @BindView(R.id.num_3)
    TextView mNum3;
    @BindView(R.id.num_subtract)
    TextView mNumSubtract;
    @BindView(R.id.num_4)
    TextView mNum4;
    @BindView(R.id.num_5)
    TextView mNum5;
    @BindView(R.id.num_6)
    TextView mNum6;
    @BindView(R.id.num_multiply)
    TextView mNumMultiply;
    @BindView(R.id.num_7)
    TextView mNum7;
    @BindView(R.id.num_8)
    TextView mNum8;
    @BindView(R.id.num_9)
    TextView mNum9;
    @BindView(R.id.num_divide)
    TextView mNumDivide;
    @BindView(R.id.num_dot)
    TextView mNumDot;
    @BindView(R.id.num_0)
    TextView mNum0;
    @BindView(R.id.num_equal)
    TextView mNumEqual;
    @BindView(R.id.num_ok)
    TextView mNumOk;
    @BindView(R.id.ll2)
    LinearLayout mLl2;

    private String str1 = "", str2 = "";
    private String strOperator = "";
    private String strRes = "";
    private String strPreRes = "";
    private boolean isSecond;
    private boolean hasPreRes;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_my_calculator;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("自定义计算器");

    }

    @OnClick({R.id.num_clear, R.id.num_del, R.id.num_plus, R.id.num_1, R.id.num_2, R.id.num_3,
            R.id.num_subtract, R.id.num_4, R.id.num_5, R.id.num_6,
            R.id.num_multiply, R.id.num_7, R.id.num_8, R.id.num_9,
            R.id.num_divide, R.id.num_dot, R.id.num_0, R.id.num_equal, R.id.num_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.num_clear:
                str1 = "";
                str2 = "";
                strOperator = "";
                strRes = "";
                isSecond = false;
                strPreRes = "";
                hasPreRes = false;
                mEtResult.setText("0");
                break;
            case R.id.num_del:
                if (TextUtils.isEmpty(strRes)) {
                    if (!TextUtils.isEmpty(str2)) {
                        str2 = str2.substring(0, str2.length() - 1);
                        isSecond = true;
                    } else if (!TextUtils.isEmpty(strOperator)) {
                        strOperator = "";
                        isSecond = false;
                    } else if (!TextUtils.isEmpty(str1)) {
                        str1 = str1.substring(0, str1.length() - 1);
                        isSecond = false;
                    }
                    mEtResult.setText(str1 + strOperator + str2);

                    if (TextUtils.isEmpty(str1)) {
                        mEtResult.setText("0");
                    }
                }
                break;
            case R.id.num_1:
                appendNum(1);
                break;
            case R.id.num_2:
                appendNum(2);
                break;
            case R.id.num_3:
                appendNum(3);
                break;
            case R.id.num_4:
                appendNum(4);
                break;
            case R.id.num_5:
                appendNum(5);
                break;
            case R.id.num_6:
                appendNum(6);
                break;
            case R.id.num_7:
                appendNum(7);
                break;
            case R.id.num_8:
                appendNum(8);
                break;
            case R.id.num_9:
                appendNum(9);
                break;
            case R.id.num_0:
                appendNum(0);
                break;
            case R.id.num_dot:
                appendDot();
                break;
            case R.id.num_plus:
                appendOperator("+");
                break;
            case R.id.num_subtract:
                appendOperator("-");
                break;
            case R.id.num_multiply:
                appendOperator("x");
                break;
            case R.id.num_divide:
                appendOperator("÷");
                break;
            case R.id.num_equal:
                if (TextUtils.isEmpty(strRes)) {
                    if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(strOperator) && !TextUtils.isEmpty(str1)) {
                        getResult();
                    }
                }
                break;
            case R.id.num_ok:
                break;
        }
    }

    private boolean checkNum(String money) {
        int index = money.indexOf(".");
        if (index == -1) {
            return true;
        } else {
            if (money.length() - index <= 2)
                return true;
        }
        return false;
    }

    private boolean checkDot(String money) {
        if (money.contains("."))
            return true;

        return false;
    }

    private void appendNum(int i) {

        if (!isSecond) {
            if (checkNum(str1))
                str1 = str1 + i;
        } else {
            if (checkNum(str2))
                str2 = str2 + i;
        }
        mEtResult.setText(str1 + strOperator + str2);
    }

    private void appendDot() {

        if (!isSecond) {
            if (!checkDot(str1)) {
                str1 = str1 + ".";
                if (str1.length() == 1)
                    str1 = "0.";
            }
        } else {
            if (!checkDot(str2)) {
                str2 = str2 + ".";
                if (str2.length() == 1)
                    str2 = "0.";
            }
        }
        mEtResult.setText(str1 + strOperator + str2);
    }

    private void appendOperator(String s) {

        if (!TextUtils.isEmpty(strRes)) {
            setNull();
        } else if (!TextUtils.isEmpty(str2)) {
            getResult();
        } else if (!TextUtils.isEmpty(str1)) {
            strOperator = s;
            isSecond = true;
            mEtResult.setText(str1 + strOperator);
        } else {
            if (hasPreRes) {
                str1 = strPreRes;
                strOperator = s;
                isSecond = true;
                mEtResult.setText(str1 + strOperator);
            }
        }
    }

    private void getResult() {
        double d1 = Double.parseDouble(str1);
        double d2 = Double.parseDouble(str2);

        if ("+".equals(strOperator)) {
            strRes = String.valueOf(d1 + d2);
        } else if ("-".equals(strOperator)) {
            strRes = String.valueOf(d1 - d2);
        } else if ("x".equals(strOperator)) {
            strRes = String.valueOf(d1 * d2);
        } else if ("÷".equals(strOperator)) {
            strRes = String.valueOf(d1 / d2);
        }
        //先  格式化为十进制普通表示方式 ，取6位
        //再正则显示整数
        strRes = subZeroAndDot(String.format("%f", Double.parseDouble(strRes)));
        strPreRes = strRes;
        hasPreRes = true;
        mEtResult.setText(str1 + strOperator + str2 + "=" + strRes);
        setNull();
    }

    private void setNull() {
        str1 = "";
        str2 = "";
        strOperator = "";
        isSecond = false;
        strRes = "";
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s 传入的字符串
     * @return 修改之后的字符串
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
}
