package com.binyouwei.wanandroid_compose.ui.page.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.binyouwei.common.base.BaseViewModel
import com.binyouwei.common.manager.ApiManager
import com.binyouwei.common.bean.HotKeyBean
import com.binyouwei.common.network.callback.IApiErrorCallback
import com.binyouwei.common.network.repository.HttpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author binjx
 * @date 2023/7/24 17:10
 * @purpose：
 **/
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repo: HttpRepository,
) : BaseViewModel() {
    val hotKeyList = mutableStateOf(mutableListOf<HotKeyBean>())

    fun getHotKeyList() {

    }
}