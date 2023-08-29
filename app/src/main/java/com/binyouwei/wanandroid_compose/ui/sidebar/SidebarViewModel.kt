package com.binyouwei.wanandroid_compose.ui.sidebar

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.binyouwei.common.base.BaseViewModel
import com.binyouwei.common.bean.*
import com.binyouwei.common.network.HttpResult
import com.binyouwei.common.network.repository.HttpRepository
import com.blankj.utilcode.util.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

/**
 * @author binjx
 * @date 2023/7/27 16:27
 * @purpose：
 **/
@HiltViewModel
class SidebarViewModel @Inject constructor(
    private val repository: HttpRepository,
) : BaseViewModel() {
    val rankingList = MutableLiveData<Flow<PagingData<RankingListBean>>?>(null)
    val collectArticles = MutableLiveData<Flow<PagingData<ArticleBean>>?>(null)
    val integrals = MutableLiveData<Flow<PagingData<ScoreBean>>?>(null)
    val shares = MutableLiveData<ShareWrapper<ArticleBean>>(null)

    val userInfoResult = mutableStateOf(false)
    val userInfo = MutableLiveData<UserInfoBean>(null)
    val getShareArticleResult = mutableStateOf(false)

    fun getCollectArticles() {
        collectArticles.value = repository.getCollectList().cachedIn(viewModelScope)
    }

    fun getRankingList() {
        rankingList.value = repository.getRankingList().cachedIn(viewModelScope)
    }

    fun getUserScoreList() {
        integrals.value = repository.getUserScoreList().cachedIn(viewModelScope)
    }

    fun getShareList() {
        async {
            repository.getShareList(1).collectLatest { response ->
                when (response) {
                    is HttpResult.Success -> {
                        getShareArticleResult.value = true
                        shares.value = response.result!!
                    }
                    else -> {
                        getShareArticleResult.value = false
                    }
                }
            }
        }
    }

    fun getUserInfo() {
        async {
            repository.getUserInfo().collectLatest { response ->
                when (response) {
                    is HttpResult.Success -> {
                        userInfoResult.value = true
                        userInfo.value = response.result!!
                    }
                    else -> {
                        userInfoResult.value = false
                    }
                }
            }
        }
    }

    /**
     * 需要实现登录功能才可以做
     */
    fun shareArticle() {
        async {
            repository.shareArticle()
        }
    }
}