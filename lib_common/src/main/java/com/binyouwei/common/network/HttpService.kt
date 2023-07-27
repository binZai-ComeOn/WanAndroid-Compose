package com.binyouwei.common.network

import com.binyouwei.common.bean.HotKeyBean
import com.binyouwei.common.base.BaseResponse
import com.binyouwei.common.bean.ArticleBean
import com.binyouwei.common.bean.ListWrapper
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

    /**
     * 搜索热词
     * https://www.wanandroid.com/hotkey/json
     */
    @GET("/hotkey/json")
    suspend fun getHotkeys(): BaseResponse<MutableList<HotKeyBean>>

    /**
     * 置顶文章
     * https://www.wanandroid.com/article/top/json
     */
    @GET("/article/top/json")
    suspend fun getTopArticles(): BaseResponse<MutableList<ArticleBean>>


    /**
     * 获取文章列表
     * http://www.wanandroid.com/article/list/0/json
     * @param pageNum
     */
    @GET("article/list/{pageNum}/json")
    suspend fun getArticles(@Path("pageNum") pageNum: Int): BaseResponse<ListWrapper<ArticleBean>>
}
