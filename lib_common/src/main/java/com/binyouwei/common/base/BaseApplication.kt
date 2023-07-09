package com.binyouwei.common.base

import android.app.Application
import com.binyouwei.common.utils.DataStoreUtils

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DataStoreUtils.init(this)
    }
}