package com.binyouwei.wanandroid_compose.ui.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.binyouwei.common.base.BaseViewModel
import com.binyouwei.common.bean.ArticleBean
import com.binyouwei.common.bean.KnowledgeSystemBean
import com.binyouwei.common.network.HttpResult
import com.binyouwei.common.network.repository.HttpRepository
import com.blankj.utilcode.util.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

/**
 * @author binjx
 * @date 2023/7/28 10:12
 * @purpose：
 **/
@HiltViewModel
class MainViewModel @Inject constructor(val repository: HttpRepository) : BaseViewModel() {
    val squares = MutableLiveData<Flow<PagingData<ArticleBean>>>(null)
    val topArticles = mutableStateListOf<ArticleBean>()
    val articles = MutableLiveData<Flow<PagingData<ArticleBean>>?>(null)
    val knowledgeSystems = mutableStateListOf<KnowledgeSystemBean>()
    val knowledges = MutableLiveData<Flow<PagingData<ArticleBean>>>(null)

    fun getSquareList() {
        squares.value = repository.getSquareList().cachedIn(viewModelScope)
    }

    fun getTopArticles() {
        async {
            repository.getTopArticles().collectLatest {
                when (it) {
                    is HttpResult.Success -> {
                        topArticles.clear()
                        topArticles.addAll(it.result)
                    }

                    is HttpResult.Error -> {
                        topArticles.clear()
                    }
                }
            }
        }
    }

    fun getHomeData() {
        articles.value = null
        articles.value = getArticles()
    }

    private fun getArticles() = repository.getArticles().cachedIn(viewModelScope)

    fun getKnowledgeTree() {
        async {
            repository.getKnowledgeTree().collectLatest { response ->
                when (response) {
                    is HttpResult.Success -> {
                        knowledgeSystems.clear()
                        knowledgeSystems.addAll(response.result)
                    }

                    is HttpResult.Error -> {
                        LogUtils.e("网络请求异常： ${response.exception.message}")
                    }
                }
            }
        }
    }

    fun getKnowledgeList(id: Int) {
        knowledges.value = repository.getKnowledgeList(id).cachedIn(viewModelScope)
    }
}