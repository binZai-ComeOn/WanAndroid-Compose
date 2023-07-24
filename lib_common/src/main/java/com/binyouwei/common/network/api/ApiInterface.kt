package com.binyouwei.common.network.api

import com.binyouwei.common.network.bean.HotKeyBean
import com.binyouwei.common.network.response.BaseResponse
import retrofit2.http.GET


/**
 * @desc   API接口类
 */
interface ApiInterface {

    /**
     * 搜索热词
     */
    @GET("hotkey/json")
    fun getHotKeyList(): BaseResponse<MutableList<HotKeyBean>?>?
}