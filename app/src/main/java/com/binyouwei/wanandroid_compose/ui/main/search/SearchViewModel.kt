package com.binyouwei.wanandroid_compose.ui.main.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.binyouwei.common.base.BaseViewModel
import com.binyouwei.common.bean.ArticleBean
import com.binyouwei.common.bean.HotKeyBean
import com.binyouwei.common.network.HttpResult
import com.binyouwei.common.network.repository.HttpRepository
import com.binyouwei.common.utils.TimeUtil
import com.binyouwei.wanandroid_compose.data.db.AppDataBase
import com.binyouwei.wanandroid_compose.data.db.table.SearchHistoryTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

/**
 * @author binjx
 * @date 2023/7/24 17:10
 * @purpose：
 **/
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: HttpRepository,
    private val db: AppDataBase,
) : BaseViewModel() {
    val hotKeyList = mutableStateOf(mutableListOf<HotKeyBean>())
    val searchResult = MutableLiveData<Flow<PagingData<ArticleBean>>?>(null)
    val searchHistory = mutableStateOf(mutableListOf<SearchHistoryTable>())

    fun getHotKeyList() {
        async {
            repository.getHotkeys().collectLatest {
                when (it) {
                    is HttpResult.Success -> {
                        hotKeyList.value = it.result
                    }

                    is HttpResult.Error -> {

                    }
                }
            }
        }
    }

    fun getSearchHistory() {
        async {
            val data = db.searchHistoryDao().querySearchHistoryList()
            searchHistory.value = data
        }
    }

    fun deleteSearchHistory() {
        async {
            db.searchHistoryDao().deleteAllSearchHistory()
            searchHistory.value = db.searchHistoryDao().querySearchHistoryList()
        }
    }

    fun insertSearchHistory(name: String) {
        async {
            if (name.isNullOrEmpty()) return@async
            val data = db.searchHistoryDao().querySearchHistory(name)
            val bean = SearchHistoryTable(name = name, time = TimeUtil.currentTimeMillis())
            if (data.isNotEmpty()) {
                data.forEach {
                    db.searchHistoryDao().deleteSearchHistory(it)
                }
            }
            db.searchHistoryDao().insertSearchHistory(bean)
        }
    }

    fun queryArticles(keyword: String) {
        searchResult.value = repository.queryArticles(keyword).cachedIn(viewModelScope)
    }
}