package com.wjf.mykotlin.utils

import android.content.Context
import android.text.TextUtils
import android.widget.Toast

/**
 * Created by wjf on 2018/8/31.
 */
class ToastUtil {
    private var toast: Toast? = null

    /**
     * 强大的吐司，能够连续弹的吐司
     *
     * @param message
     */
    fun  show(context: Context, message: String) {
        var message = message
        if (TextUtils.isEmpty(message))
            return
        else if (message.toLowerCase().contains("failed to connect to"))
            message = "服务器正在维护中，请稍后重试"
        if (toast == null) {
            toast = Toast.makeText(context.applicationContext, message, Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(message)//如果不为空，则直接改变当前toast的文本
        }
        toast!!.show()
    }
}