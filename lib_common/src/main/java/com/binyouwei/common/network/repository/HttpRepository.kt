package com.binyouwei.common.network.repository

import androidx.paging.PagingData
import com.binyouwei.common.base.BaseResponse
import com.binyouwei.common.bean.ArticleBean
import com.binyouwei.common.network.HttpResult
import com.binyouwei.common.bean.HotKeyBean
import com.binyouwei.common.bean.KnowledgeSystemBean
import com.binyouwei.common.bean.ListWrapper
import com.binyouwei.common.bean.RankingListBean
import kotlinx.coroutines.flow.Flow


interface HttpRepository {

    suspend fun getHotkeys(): Flow<HttpResult<MutableList<HotKeyBean>>>

    suspend fun getTopArticles(): Flow<HttpResult<MutableList<ArticleBean>>>

    fun getArticles(): Flow<PagingData<ArticleBean>>

    fun getRankingList(): Flow<PagingData<RankingListBean>>

    fun queryArticles(keywords: String): Flow<PagingData<ArticleBean>>

    fun getSquareList(): Flow<PagingData<ArticleBean>>

    fun shareArticle(): Flow<PagingData<ArticleBean>>

    fun getKnowledgeTree(): Flow<HttpResult<MutableList<KnowledgeSystemBean>>>
}