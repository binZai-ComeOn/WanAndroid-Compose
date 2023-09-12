package com.binyouwei.common.network.interceptor

import com.binyouwei.common.constant.*
import com.binyouwei.common.manager.CookiesManager
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
        val url = request.url().toString()
        newBuilder.addHeader("Content-type", "application/json; charset=utf-8")
        newBuilder.addHeader("RequestUrl", url)
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
            ) || url.contains(USER_SHARE_ARTICLE)
        ) {
            val cookies = CookiesManager.getCookies()
            if (!cookies.isNullOrEmpty()) {
                newBuilder.addHeader(KEY_COOKIE, cookies)
            }
        }
        return chain.proceed(newBuilder.build())
    }
}