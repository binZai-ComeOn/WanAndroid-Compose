package com.binyouwei.common.network

import com.binyouwei.common.bean.HotKeyBean
import com.binyouwei.common.base.BaseResponse
import com.binyouwei.common.bean.ArticleBean
import retrofit2.http.*

/**
 * @author 宾有为
 * Created on 2023/7/26 22:27
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/
interface HttpService {

    companion object {
        const val url = "https://www.wanandroid.com"
    }

    // 搜索热词
    @GET("/hotkey/json")
    suspend fun getHotkeys(): BaseResponse<MutableList<HotKeyBean>>

    // 获取置顶文章
    @GET("/article/top/json")
    suspend fun getTopArticles(): BaseResponse<MutableList<ArticleBean>>
}
