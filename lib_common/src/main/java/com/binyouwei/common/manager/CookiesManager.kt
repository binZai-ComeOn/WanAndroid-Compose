package com.binyouwei.common.manager

import com.binyouwei.common.utils.DataStoreUtils
import com.blankj.utilcode.util.LogUtils

/**
 * @desc   Cookies管理类
 */
object CookiesManager {
    val HTTP_COOKIES_INFO = "http_cookies_info"

    /**
     * 保存Cookies
     * @param cookies
     */
    fun saveCookies(cookies: String) = DataStoreUtils.putSyncData(HTTP_COOKIES_INFO,cookies)

    /**
     * 获取Cookies
     * @return cookies
     */
    fun getCookies(): String? = DataStoreUtils.readStringData(HTTP_COOKIES_INFO,"")

    /**
     * 清除Cookies
     * @param cookies
     */
    fun clearCookies() {
        saveCookies("")
    }

    /**
     * 解析Cookies
     * @param cookies
     */
    fun encodeCookie(cookies: List<String>?): String {
        val sb = StringBuilder()
        val set = HashSet<String>()
        cookies
                ?.map { cookie ->
                    cookie.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                }
                ?.forEach {
                    it.filterNot { set.contains(it) }.forEach { set.add(it) }
                }
        LogUtils.e("cookiesList:$cookies")
        val ite = set.iterator()
        while (ite.hasNext()) {
            val cookie = ite.next()
            sb.append(cookie).append(";")
        }
        val last = sb.lastIndexOf(";")
        if (sb.length - 1 == last) {
            sb.deleteCharAt(last)
        }
        return sb.toString()
    }
}