package com.binyouwei.common.network.interceptor

import com.binyouwei.common.constant.*
import com.binyouwei.common.extend.saveAs
import com.binyouwei.common.manager.CookiesManager
import com.blankj.utilcode.util.LogUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @desc   头信息拦截器
 * 添加头信息
 */
class HeaderInterceptor : Interceptor {
    private val loginUrl = "/user/login"
    private val registerUrl = "/user/register"
    private val SET_COOKIE_KEY = "set-cookie"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val newBuilder = request.newBuilder()
        newBuilder.addHeader("Content-type", "application/json; charset=utf-8")
        val url = request.url().toString()
        // 登录、注册的时候提取cookie
        val responseCookies = response.headers(SET_COOKIE_KEY)
        if (url.contains(loginUrl) || url.contains(registerUrl)) {
            if (responseCookies.isNotEmpty()) {
                CookiesManager.saveCookies(CookiesManager.encodeCookie(responseCookies))
            }
        }
        // 给有需要的接口添加Cookies
        if (url.contains(USER_INFO) || url.contains(COLLECT_LIST) || url.contains(USER_SCORE) || url.contains(
                USER_SHARE
            )
        ) {
            val cookies = CookiesManager.getCookies()
            LogUtils.e("HeaderInterceptor:cookies:$cookies")
            if (!cookies.isNullOrEmpty()) {
                newBuilder.addHeader(KEY_COOKIE, cookies)
            }
        }
        return chain.proceed(newBuilder.build())
    }
}