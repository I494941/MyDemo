package wjf.com.mykotlin.base

import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import butterknife.ButterKnife
import wjf.com.mykotlin.R

/**
 * Created by wjf on 2018/8/31.
 */

abstract class BaseTitlebarActivity : BaseAppCompatActivity() {
    protected var mToolbar: Toolbar? = null
    protected lateinit var mTvTitle: TextView
    protected lateinit var mTvRight: TextView
    protected lateinit var mIvMore: ImageView

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        mToolbar = ButterKnife.findById(this, R.id.toolbar)
        mTvTitle = ButterKnife.findById(this, R.id.tv_title)
        mTvRight = ButterKnife.findById(this, R.id.tv_right)
        mIvMore = ButterKnife.findById(this, R.id.toolbar_iv_more)

        initToolbar()
    }

    fun initToolbar() {
        if (mToolbar != null) {
            mToolbar!!.setNavigationIcon(R.drawable.arrow_back)
            setSupportActionBar(mToolbar)
            val actionBar = supportActionBar
            if (actionBar != null) {
                actionBar.setDisplayShowTitleEnabled(false)
                //actionBar.setHomeButtonEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            back()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun back() {
        finish()
    }
}