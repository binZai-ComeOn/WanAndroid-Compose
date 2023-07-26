package com.binyouwei.common.network.interceptor

import com.binyouwei.common.helper.AppHelper
import com.binyouwei.common.manager.AppManager
import com.binyouwei.common.utils.DeviceInfoUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.net.URLEncoder

/**
 * @desc   公共参数拦截器
 */
class PublicParameterInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newBuilder = request.newBuilder().apply {
            addHeader("device-type", "android")
            addHeader("app-version", AppManager.getAppVersionName(AppHelper.getApplication()))
            addHeader("device-id", DeviceInfoUtil.androidId)
            addHeader("device-os-version", AppManager.getDeviceBuildRelease())//获取手机系统版本号
            val deviceNameStr = AppManager.getDeviceBuildBrand().plus("_")
                    .plus(AppManager.getDeviceBuildModel())
            addHeader("device-name", URLEncoder.encode(deviceNameStr, "UTF-8"))//获取设备类型
        }

        return chain.proceed(newBuilder.build())
    }
}