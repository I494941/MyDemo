package wjf.com.mykotlin.base

import android.app.Activity
import java.util.*

/**
 * Created by wjf on 2018/8/31.
 */

class BaseAppManager {

    private var instance: BaseAppManager? = null
    private val mActivities = LinkedList<Activity>()

    @Synchronized private fun createInstance() {
        if (instance == null) {
            instance = BaseAppManager()
        }
    }

    fun getInstance(): BaseAppManager? {
        if (instance == null)
            createInstance()
        return instance
    }

    fun size(): Int {
        return mActivities.size
    }

    @Synchronized
    fun getForwardActivity(): Activity? {
        return if (size() > 0) mActivities[size() - 1] else null
    }

    @Synchronized
    fun addActivity(activity: Activity) {
        mActivities.add(activity)
    }

    @Synchronized
    fun removeActivity(activity: Activity) {
        if (mActivities.contains(activity)) {
            mActivities.remove(activity)
        }
    }

    @Synchronized
    fun clear() {
        var i = mActivities.size - 1
        while (i > -1) {
            val activity = mActivities[i]
            removeActivity(activity)
            activity.finish()
            i = mActivities.size
            i--
        }
    }

    @Synchronized
    fun clearToTop() {
        var i = mActivities.size - 2
        while (i > -1) {
            val activity = mActivities[i]
            removeActivity(activity)
            activity.finish()
            i = mActivities.size - 1
            i--
        }
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        var activity = activity
        if (activity != null) {
            mActivities.remove(activity)
            activity.finish()
            activity = null
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in mActivities) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
                break
            }
        }
    }


}