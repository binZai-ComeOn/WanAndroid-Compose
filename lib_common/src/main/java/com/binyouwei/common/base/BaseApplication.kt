package com.binyouwei.common.base

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.multidex.MultiDex
import com.binyouwei.common.helper.AppHelper
import com.binyouwei.common.manager.ActivityManager
import com.binyouwei.common.manager.AppFrontBack
import com.binyouwei.common.manager.AppFrontBackListener
import com.binyouwei.common.utils.DataStoreUtils
import com.binyouwei.lib_common.BuildConfig
import com.blankj.utilcode.util.LogUtils
import com.hjq.toast.Toaster

/**
 * @author binjx
 * @date 2023/5/19 11:51
 * @purpose：
 **/
open class BaseApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    override fun onCreate() {
        super.onCreate()
        AppHelper.init(this, BuildConfig.DEBUG)
        DataStoreUtils.init(this)

        //注册APP前后台切换监听
        appFrontBackRegister()
        // App启动立即注册监听
        registerActivityLifecycle()
        Toaster.init(this)
    }

    /**
     * 注册APP前后台切换监听
     */
    private fun appFrontBackRegister() {
        AppFrontBack.register(this, object : AppFrontBackListener {
            override fun onBack(activity: Activity?) {
                LogUtils.d("onBack")
            }

            override fun onFront(activity: Activity?) {
                LogUtils.d("onFront")
            }
        })
    }

    /**
     * 注册Activity生命周期监听
     */
    private fun registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityDestroyed(activity: Activity) {
                ActivityManager.pop(activity)
            }

            override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivityCreated(activity: Activity, p1: Bundle?) {
                ActivityManager.push(activity)
            }

            override fun onActivityResumed(activity: Activity) {
            }
        })
    }
}