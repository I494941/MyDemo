package com.example.a94941.mydemo.activitys.smsActivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a94941.mydemo.R;
import com.example.a94941.mydemo.base.BaseToolbarActivity;
import com.example.a94941.mydemo.utils.LogUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wjf on 2018/8/9.
 */

public class SmsActivity extends BaseToolbarActivity {

    @BindView(R.id.edittext1)
    EditText mEt1;
    @BindView(R.id.edittext2)
    EditText mEt2;

    //处理返回的发送状态
    String SENT_SMS_ACTION = "SENT_SMS_ACTION";
    //处理返回的接收状态
    String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";

    String KEY_PHONENUM = "KEY_PHONENUM";

    private ArrayList<String> contactList = new ArrayList<>();
    SmsManager smsManager;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_sms;
    }

    @Override
    protected void initViewsAndEvents() {
        mTvTitle.setText("短信");

        initBroadcastReceiver();

        contactList.add("16653172880");
        contactList.add("18717870519");

        smsManager = SmsManager.getDefault();
    }

    @OnClick({R.id.button1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:

                for (int i = 0; i < contactList.size(); i++) { // contactList 发送人列表
                    Intent itSend = new Intent(SENT_SMS_ACTION); // 自定义 Intent  Action
                    itSend.putExtra(KEY_PHONENUM, contactList.get(i)); // KEY_PHONENUM： 携带数据，收件人KEY 。
                    PendingIntent sentPI = PendingIntent.getBroadcast(getApplicationContext(), i, itSend, PendingIntent.FLAG_ONE_SHOT);
                    //这里requestCode和flag的设置很重要，影响数据KEY_PHONENUM的传递。用循环I值作为了requestCode。
                    //                    String content = mContext.getString(R.string.test); // 短信内容
                    //                    String message = "865494249"; // 短信内容
                    String message = "一二三四五六七八九零" +
                            "一二三四五六七八九零" +
                            "一二三四五六七八九零" +
                            "一二三四五六七八九零" +
                            "一二三四五六七八九零" +
                            "一二三四五六七八九零" +
                            "一二三四五六七八九零" +
                            "一二三四五六七八九零"; // 短信内容
                    //                    smsManager.sendTextMessage(contactList.get(i), null, message, mSendPI, null); //smsManager 发送短信Manager

                    if (message.length() > 70) {
                        ArrayList<String> msgs = smsManager.divideMessage(message);
                        ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>();

                        for (int j = 0; j < msgs.size(); j++) {
                            sentIntents.add(sentPI);
                        }
                        smsManager.sendMultipartTextMessage(contactList.get(i), null, msgs, sentIntents, null);
                    } else {
                        smsManager.sendTextMessage(contactList.get(i), null, message, sentPI, null);
                    }
                }
                break;
        }
    }

    private void initBroadcastReceiver() {

        //发送短信状态
        IntentFilter sendIntentFilter = new IntentFilter();
        sendIntentFilter.addAction(SENT_SMS_ACTION);
        BroadcastReceiver sendBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                System.out.println("发送短信状态");
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(context, intent.getStringExtra(KEY_PHONENUM) + "短信发送成功", Toast.LENGTH_SHORT).show();
                        LogUtils.e("11111111111111111111", intent.getStringExtra(KEY_PHONENUM) + "短信发送成功");
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        break;
                }
            }
        };
        registerReceiver(sendBroadcastReceiver, sendIntentFilter);


        //接收短信状态
        IntentFilter deliverIntentFilter = new IntentFilter();
        deliverIntentFilter.addAction(DELIVERED_SMS_ACTION);
        BroadcastReceiver deliverBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                System.out.println("接收短信状态");
                Toast.makeText(context, "收信人已经成功接收", Toast.LENGTH_SHORT).show();
            }
        };
        registerReceiver(deliverBroadcastReceiver, deliverIntentFilter);

    }

    protected void sendMessageByInterface2(String phoneNumber, String message) {

        SmsManager sms = SmsManager.getDefault();

        Intent sentIntent = new Intent(SENT_SMS_ACTION);
        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, sentIntent, 0);

        Intent deliverIntent = new Intent(DELIVERED_SMS_ACTION);
        PendingIntent deliverPI = PendingIntent.getBroadcast(this, 0, deliverIntent, 0);

        if (message.length() > 70) {
            ArrayList<String> msgs = sms.divideMessage(message);
            ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>();

            for (int i = 0; i < msgs.size(); i++) {
                sentIntents.add(sentPI);
            }
            sms.sendMultipartTextMessage(phoneNumber, null, msgs, sentIntents, null);
        } else {
            sms.sendTextMessage(phoneNumber, null, message, sentPI, deliverPI);
        }

    }
}

