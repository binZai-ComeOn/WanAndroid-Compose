package com.binyouwei.common.network

import com.binyouwei.common.base.BaseResponse
import com.binyouwei.common.bean.*
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
     * https://www.wanandroid.com/article/list/0/json
     * @param pageNum
     */
    @GET("/article/list/{page}/json")
    suspend fun getArticles(@Path("page") page: Int): BaseResponse<ListWrapper<ArticleBean>>

    /**
     * 获取积分排行榜
     * https://www.wanandroid.com/coin/rank/1/json
     * @param page 页码 从1开始
     */
    @GET("/coin/rank/{page}/json")
    suspend fun getRankingList(@Path("page") page: Int): BaseResponse<ListWrapper<RankingListBean>>

    @POST("/article/query/{page}/json")
    @FormUrlEncoded
    suspend fun queryArticles(
        @Path("page") page: Int,
        @Field("k") key: String
    ): BaseResponse<ListWrapper<ArticleBean>>

    /**
     * 广场列表数据
     * https://wanandroid.com/user_article/list/0/json
     * @param page 页码拼接在url上从0开始
     */
    @GET("/user_article/list/{page}/json")
    suspend fun getSquareList(@Path("page") page: Int): BaseResponse<ListWrapper<ArticleBean>>


    /**
     * 分享文章
     * https://www.wanandroid.com/lg/user_article/add/json
     * @param map
     *      title: 文章标题
     *      link:  文章链接
     */
    @POST("/lg/user_article/add/json")
    @FormUrlEncoded
    suspend fun shareArticle(@FieldMap map: MutableMap<String, Any>): HttpResult<Any>

    /**
     * 获取知识体系
     * https://www.wanandroid.com/tree/json
     */
    @GET("/tree/json")
    suspend fun getKnowledgeTree(): BaseResponse<MutableList<KnowledgeSystemBean>>

    /**
     * 知识体系下的文章
     * https://www.wanandroid.com/article/list/0/json?cid=168
     * @param page
     * @param cid
     */
    @GET("/article/list/{page}/json")
    suspend fun getKnowledgeList(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): BaseResponse<ListWrapper<ArticleBean>>

    /**
     * 获取公众号列表
     * https://wanandroid.com/wxarticle/chapters/json
     */
    @GET("/wxarticle/chapters/json")
    suspend fun getWXChapters(): BaseResponse<MutableList<TabBean>>

    /**
     * 获取公众号列表
     * https://wanandroid.com/wxarticle/list/408/1/json
     */
    @GET("/wxarticle/list/{id}/{page}/json")
    suspend fun getWXChapterArticles(
        @Path("page") page: Int,
        @Path("id") id: Int
    ): BaseResponse<ListWrapper<ArticleBean>>

    /**
     * 获取公众号列表
     * https://www.wanandroid.com/project/tree/json
     */
    @GET("/project/tree/json")
    suspend fun getProjectTabs(): BaseResponse<MutableList<TabBean>>

    /**
     * 获取公众号列表
     * https://www.wanandroid.com/project/list/1/json?cid=294
     */
    @GET("/project/list/1/json")
    suspend fun getProjectTabArticles(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): BaseResponse<ListWrapper<ArticleBean>>
}
