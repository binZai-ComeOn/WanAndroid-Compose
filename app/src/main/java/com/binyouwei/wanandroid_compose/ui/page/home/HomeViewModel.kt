package com.binyouwei.wanandroid_compose.ui.page.home

import androidx.compose.runtime.mutableStateOf
import com.binyouwei.common.base.BaseViewModel
import com.binyouwei.common.bean.ArticleBean
import com.binyouwei.common.network.HttpResult
import com.binyouwei.common.network.repository.HttpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
    val topArticles = mutableStateOf(mutableListOf<ArticleBean>())

    fun getTopArticles() {
        async {
            repository.getTopArticles().collectLatest {
                when (it) {
                    is HttpResult.Success -> {
                        topArticles.value = it.result
                    }
                    else -> {

                    }
                }
            }
        }
    }
}