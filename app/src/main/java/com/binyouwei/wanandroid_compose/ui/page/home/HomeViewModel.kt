package com.binyouwei.wanandroid_compose.ui.page.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.binyouwei.common.base.BaseViewModel
import com.binyouwei.common.bean.ArticleBean
import com.binyouwei.common.network.HttpResult
import com.binyouwei.common.network.repository.HttpRepository
import com.blankj.utilcode.util.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

/**
 * @author 宾有为
 * Created on 2023/7/26 22:27
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HttpRepository
) : BaseViewModel() {
    val topArticles = mutableStateListOf<ArticleBean>()
    val articles = MutableLiveData<Flow<PagingData<ArticleBean>>>(null)

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

    fun getHomeData(){
        articles.value = getArticles()
    }

    private fun getArticles() = repository.getArticles().cachedIn(viewModelScope)
}