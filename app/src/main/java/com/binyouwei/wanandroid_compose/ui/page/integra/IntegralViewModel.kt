package com.binyouwei.wanandroid_compose.ui.page.integra

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.binyouwei.common.base.BaseViewModel
import com.binyouwei.common.bean.ArticleBean
import com.binyouwei.common.bean.RankingListBean
import com.binyouwei.common.network.repository.HttpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author binjx
 * @date 2023/7/27 16:27
 * @purpose：
 **/
@HiltViewModel
class IntegralViewModel @Inject constructor(
    private val repository: HttpRepository,
) : BaseViewModel() {
    val rankingList = MutableLiveData<Flow<PagingData<RankingListBean>>?>(null)

    fun getRankingList() {
        rankingList.value = repository.getRankingList().cachedIn(viewModelScope)
    }
}