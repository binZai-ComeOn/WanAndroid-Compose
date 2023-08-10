package com.binyouwei.wanandroid_compose.ui.sidebar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.binyouwei.common.base.BaseViewModel
import com.binyouwei.common.bean.RankingListBean
import com.binyouwei.common.bean.UserInfoBean
import com.binyouwei.common.network.HttpResult
import com.binyouwei.common.network.repository.HttpRepository
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
    val userInfo = MutableLiveData<UserInfoBean>(null)
    val article = MutableLiveData<Flow<PagingData<RankingListBean>>?>(null)

    fun getRankingList() {
        rankingList.value = repository.getRankingList().cachedIn(viewModelScope)
    }

    fun getUserInfo() {
        async {
            repository.getUserInfo().collectLatest { response ->
                when (response) {
                    is HttpResult.Success -> {
                        userInfo.value = response.result!!
                    }
                    else -> {

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