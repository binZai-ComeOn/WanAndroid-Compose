package com.binyouwei.common.network.repository

import androidx.paging.PagingData
import com.binyouwei.common.base.BaseRepository
import com.binyouwei.common.network.HttpService
import com.binyouwei.common.bean.*
import com.binyouwei.common.network.HttpResult
import kotlinx.coroutines.flow.Flow

class HttpRepositoryImpl constructor(private val apiService: HttpService) : BaseRepository(),
    HttpRepository {
    override suspend fun getBanners(): Flow<HttpResult<MutableList<BannerBean>>> = flowable { apiService.getBanners() }

    override suspend fun getHotkeys(): Flow<HttpResult<MutableList<HotKeyBean>>> =
        flowable { apiService.getHotkeys() }

    override suspend fun getTopArticles(): Flow<HttpResult<MutableList<ArticleBean>>> =
        flowable { apiService.getTopArticles() }

    override fun getArticles() = pager { page -> apiService.getArticles(page) }

    override fun getRankingList() = pager(initKey = 1) { page -> apiService.getRankingList(page) }
    override fun getUserScoreList(): Flow<PagingData<ScoreBean>> =
        pager(initKey = 1) { page: Int -> apiService.getUserScoreList(page) }

    override fun queryArticles(keywords: String) =
        pager { page -> apiService.queryArticles(page, keywords) }

    override fun getSquareList() = pager { page -> apiService.getSquareList(page) }
    override fun shareArticle(title: String, link: String): Flow<HttpResult<String>> =
        flowable { apiService.shareArticle(title,link) }

    override fun getKnowledgeTree(): Flow<HttpResult<MutableList<KnowledgeSystemBean>>> =
        flowable { apiService.getKnowledgeTree() }

    override fun getKnowledgeList(id: Int): Flow<PagingData<ArticleBean>> =
        pager { page -> apiService.getKnowledgeList(page, id) }

    override suspend fun getWXChapters(): Flow<HttpResult<MutableList<TabBean>>> = flowable {
        apiService.getWXChapters()
    }

    override fun getWXChapterArticles(id: Int): Flow<PagingData<ArticleBean>> =
        pager(initKey = 1) { page ->
            apiService.getWXChapterArticles(page, id)
        }

    override suspend fun getProjectTabs(): Flow<HttpResult<MutableList<TabBean>>> = flowable {
        apiService.getProjectTabs()
    }

    override fun getProjectTabArticles(id: Int): Flow<PagingData<ArticleBean>> =
        pager { page -> apiService.getProjectTabArticles(page, id) }

    override suspend fun login(username: String, password: String): Flow<HttpResult<LoginBean>> =
        flowable { apiService.login(username, password) }

    override suspend fun register(
        username: String,
        password: String,
        repassword: String
    ): Flow<HttpResult<LoginBean>> =
        flowable { apiService.register(username, password, repassword) }

    override suspend fun getUserInfo(): Flow<HttpResult<UserInfoBean>> =
        flowable { apiService.getUserInfo() }

    override fun getCollectList(): Flow<PagingData<ArticleBean>> =
        pager { page -> apiService.getCollectList(page) }

    override suspend fun getShareList(page: Int): Flow<HttpResult<ShareWrapper<ArticleBean>>> =
        flowable { apiService.getShareList(page) }
}