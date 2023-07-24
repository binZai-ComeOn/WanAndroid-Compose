package com.binyouwei.common.network.api


/**
 * @desc   API接口类
 */
interface ApiInterface {

    /**
     * 首页资讯
     * @param page    页码
     * @param pageSize 每页数量
     */
    /*@GET("/article/list/{page}/json")
    suspend fun getHomeList(
        @Path("page") page: Int,
        @Query("page_size") pageSize: Int
    ): BaseResponse<ArticleList>?

    *//**
     * 搜索结果
     * @param page   页码
     * @param keyWord  关键词，支持多个，空格分开
     *//*
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    suspend fun searchResult(
        @Path("page") page: Int,
        @Field("k") keyWord: String
    ): BaseResponse<ArticleList>?

    *//**
     * 分类列表
     *//*
    @GET("/navi/json")
    suspend fun getCategoryData(): BaseResponse<MutableList<CategoryItem>>?

    *//**
     * 登录
     * @param username  用户名
     * @param password  密码
     *//*
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): BaseResponse<User?>?*/
}