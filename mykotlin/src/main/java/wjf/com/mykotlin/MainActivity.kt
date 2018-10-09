package wjf.com.mykotlin

import wjf.com.mykotlin.base.BaseTitlebarActivity

class MainActivity : BaseTitlebarActivity() {

    override val contentViewLayoutID: Int
        get() = R.layout.activity_main

    override fun initViewAndData() {
        //隐藏toolbar返回按钮
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        mTvTitle.setText("Kotlin 首页")
    }
}
