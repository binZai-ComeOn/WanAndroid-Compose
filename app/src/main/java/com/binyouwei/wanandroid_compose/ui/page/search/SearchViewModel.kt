package com.binyouwei.wanandroid_compose.ui.page.search

import androidx.compose.runtime.mutableStateOf
import com.binyouwei.common.base.BaseViewModel
import com.binyouwei.common.bean.HotKeyBean
import com.binyouwei.common.network.HttpResult
import com.binyouwei.common.network.repository.HttpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
) : BaseViewModel() {
    val hotKeyList = mutableStateOf(mutableListOf<HotKeyBean>())

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
}