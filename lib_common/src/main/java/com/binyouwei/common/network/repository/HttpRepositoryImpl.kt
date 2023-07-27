package com.binyouwei.common.network.repository

import androidx.paging.PagingData
import com.binyouwei.common.base.BaseRepository
import com.binyouwei.common.network.HttpService
import com.binyouwei.common.bean.HotKeyBean
import com.binyouwei.common.base.BaseResponse
import com.binyouwei.common.bean.ArticleBean
import com.binyouwei.common.bean.ListWrapper
import com.binyouwei.common.bean.RankingListBean
import com.binyouwei.common.network.HttpResult
import kotlinx.coroutines.flow.Flow

class HttpRepositoryImpl constructor(private val apiService: HttpService) : BaseRepository(),
    HttpRepository {

    override suspend fun getHotkeys(): Flow<HttpResult<MutableList<HotKeyBean>>> =
        flowable { apiService.getHotkeys() }

    override suspend fun getTopArticles(): Flow<HttpResult<MutableList<ArticleBean>>> =
        flowable { apiService.getTopArticles() }

    override fun getArticles() = pager { page -> apiService.getArticles(page) }
    override fun getRankingList() = pager(initKey = 1) { page -> apiService.getRankingList(page) }
    override fun queryArticles(keywords : String) = pager { page -> apiService.queryArticles(page,keywords) }
}