package wjf.com.mykotlin.base

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import butterknife.ButterKnife
import butterknife.Unbinder
import com.readystatesoftware.systembartint.SystemBarTintManager
import com.wjf.mykotlin.utils.ToastUtil
import wjf.com.mykotlin.R

/**
 * Created by wjf on 2018/8/31.
 */
abstract class BaseAppCompatActivity : AppCompatActivity() {

    private var mUnbinder: Unbinder? = null

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract val contentViewLayoutID: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        //overridePendingTransition(R.anim.right_in, R.anim.right_out);
        super.onCreate(savedInstanceState)
        setTranslucentStatus()
        setStatusBarColor()

        BaseAppManager().getInstance()!!.addActivity(this)

        if (contentViewLayoutID != 0) {
            setContentView(contentViewLayoutID)
        } else {
            throw IllegalArgumentException("You must return a right contentView layout resource Id")
        }

        initViewAndData()
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        mUnbinder = ButterKnife.bind(this)
    }

    override fun finish() {
        super.finish()
        if (mUnbinder != null) {
            try {
                mUnbinder!!.unbind()
                mUnbinder = null
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        BaseAppManager().getInstance()!!.removeActivity(this)
        //overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    override fun onDestroy() {
        super.onDestroy()
        //RxApiManager.getInstance().cancelAll();
    }

    /**
     * init all views and add events
     */
    protected abstract fun initViewAndData()

    /**
     * startActivity
     *
     * @param clazz
     */
    protected fun startActivity(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    protected fun startActivity(clazz: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    protected fun startActivityForResult(clazz: Class<*>, requestCode: Int) {
        val intent = Intent(this, clazz)
        startActivityForResult(intent, requestCode)
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    protected fun startActivityForResult(clazz: Class<*>, requestCode: Int, bundle: Bundle?) {
        val intent = Intent(this, clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
    }

    /**
     * set status bar translucency
     *
     * @param on
     */
    private fun setTranslucentStatus(on: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val win = window
            val winParams = win.attributes
            val bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            if (on) {
                winParams.flags = winParams.flags or bits
            } else {
                winParams.flags = winParams.flags and bits.inv()
            }
            win.attributes = winParams
        }
    }

    private fun setTranslucentStatus() {
        window.requestFeature(Window.FEATURE_NO_TITLE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            //window.setNavigationBarColor(Color.TRANSPARENT);
            //window.setNavigationBarColor(Color.argb(255,240,240,240));
        } else {
            setTranslucentStatus(true)
        }
    }

    fun setStatusBarTintColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = color
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val tintManager = SystemBarTintManager(this)
            tintManager.isStatusBarTintEnabled = true
            tintManager.setStatusBarTintColor(color)
        }
    }

    fun setStatusBarColor() {
        setStatusBarTintColor(ContextCompat.getColor(this, R.color.red))
    }

    fun showShortToast(msg: String) {
        ToastUtil().show(this, msg)
    }
}