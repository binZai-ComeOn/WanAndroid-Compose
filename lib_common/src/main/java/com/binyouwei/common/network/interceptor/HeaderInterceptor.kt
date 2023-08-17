package com.binyouwei.common.network.interceptor

import com.binyouwei.common.constant.*
import com.binyouwei.common.manager.CookiesManager
import com.blankj.utilcode.util.LogUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @desc   头信息拦截器
 * 添加头信息
 */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newBuilder = request.newBuilder()
        newBuilder.addHeader("Content-type", "application/json; charset=utf-8")

        val host = request.url().host()
        val url = request.url().toString()

        //给有需要的接口添加Cookies
        if (!host.isNullOrEmpty() && (url.contains(USER_INFO) || url.contains(COLLECT_LIST))) {
            val cookies = CookiesManager.getCookies()
            LogUtils.e("HeaderInterceptor:cookies:$cookies")
            if (!cookies.isNullOrEmpty()) {
                newBuilder.addHeader(KEY_COOKIE, cookies)
            }
        }
        return chain.proceed(newBuilder.build())
    }
}