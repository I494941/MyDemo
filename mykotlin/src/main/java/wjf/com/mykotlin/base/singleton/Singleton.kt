package com.wjf.mykotlin.base.ktBase.singleton

/**
 * Created by wjf on 2018/8/31.
 */
class Singleton {

    private var instance: Singleton? = null

    @Synchronized private fun createInstance() {
        if (instance == null) {
            instance = Singleton()
        }
    }

    fun getInstance(): Singleton? {
        if (instance == null)
            createInstance()
        return instance
    }
}