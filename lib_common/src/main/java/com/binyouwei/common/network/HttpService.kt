package com.binyouwei.common.network

import com.binyouwei.common.bean.HotKeyBean
import com.binyouwei.common.base.BaseResponse
import retrofit2.http.*

/**
 * 网络请求接口
 * 注意：接口前无需加斜杠
 * create by 宾有为 at 7/25
 */
interface HttpService {

    companion object {
        const val url = "https://www.wanandroid.com"
    }

    // 搜索热词
    @GET("/hotkey/json")
    suspend fun getHotkeys(): BaseResponse<MutableList<HotKeyBean>>
}
