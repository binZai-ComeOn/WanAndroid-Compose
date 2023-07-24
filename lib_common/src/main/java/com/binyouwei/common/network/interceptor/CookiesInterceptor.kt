package com.binyouwei.common.network.interceptor

import com.binyouwei.common.manager.CookiesManager
import com.binyouwei.common.network.constant.KEY_SAVE_USER_LOGIN
import com.binyouwei.common.network.constant.KEY_SAVE_USER_REGISTER
import com.binyouwei.common.network.constant.KEY_SET_COOKIE
import com.blankj.utilcode.util.LogUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @desc   Cookies拦截器
 */
class CookiesInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newBuilder = request.newBuilder()

        val response = chain.proceed(newBuilder.build())
        val url = request.url().toString()
        val host = request.url().host()

        // set-cookie maybe has multi, login to save cookie
        if ((url.contains(KEY_SAVE_USER_LOGIN) || url.contains(KEY_SAVE_USER_REGISTER))
                && response.headers(KEY_SET_COOKIE).isNotEmpty()
        ) {
            val cookies = response.headers(KEY_SET_COOKIE)
            val cookiesStr = CookiesManager.encodeCookie(cookies)
            CookiesManager.saveCookies(cookiesStr)
            LogUtils.e("CookiesInterceptor:cookies:$cookies")
        }
        return response
    }
}