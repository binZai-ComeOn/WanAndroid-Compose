package com.binyouwei.common.network.repository

import com.binyouwei.common.bean.ArticleBean
import com.binyouwei.common.network.HttpResult
import com.binyouwei.common.bean.HotKeyBean
import kotlinx.coroutines.flow.Flow


interface HttpRepository {

    suspend fun getHotkeys(): Flow<HttpResult<MutableList<HotKeyBean>>>

    suspend fun getTopArticles(): Flow<HttpResult<MutableList<ArticleBean>>>
}