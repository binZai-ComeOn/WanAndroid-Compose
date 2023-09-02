package com.binyouwei.common.network.repository

import androidx.paging.PagingData
import com.binyouwei.common.bean.*
import com.binyouwei.common.network.HttpResult
import kotlinx.coroutines.flow.Flow


interface HttpRepository {

    suspend fun getHotkeys(): Flow<HttpResult<MutableList<HotKeyBean>>>

    suspend fun getTopArticles(): Flow<HttpResult<MutableList<ArticleBean>>>

    fun getArticles(): Flow<PagingData<ArticleBean>>

    fun getRankingList(): Flow<PagingData<RankingListBean>>

    fun getUserScoreList(): Flow<PagingData<ScoreBean>>

    fun queryArticles(keywords: String): Flow<PagingData<ArticleBean>>

    fun getSquareList(): Flow<PagingData<ArticleBean>>

    fun shareArticle(title: String, link: String): Flow<HttpResult<String>>

    fun getKnowledgeTree(): Flow<HttpResult<MutableList<KnowledgeSystemBean>>>

    fun getKnowledgeList(id: Int): Flow<PagingData<ArticleBean>>

    suspend fun getWXChapters(): Flow<HttpResult<MutableList<TabBean>>>

    fun getWXChapterArticles(id: Int): Flow<PagingData<ArticleBean>>


    suspend fun getProjectTabs(): Flow<HttpResult<MutableList<TabBean>>>

    fun getProjectTabArticles(id: Int): Flow<PagingData<ArticleBean>>

    suspend fun login(username: String, password: String): Flow<HttpResult<LoginBean>>

    suspend fun register(
        username: String,
        password: String,
        repassword: String
    ): Flow<HttpResult<LoginBean>>

    suspend fun getUserInfo(): Flow<HttpResult<UserInfoBean>>

    fun getCollectList(): Flow<PagingData<ArticleBean>>

    suspend fun getShareList(page : Int): Flow<HttpResult<ShareWrapper<ArticleBean>>>
}