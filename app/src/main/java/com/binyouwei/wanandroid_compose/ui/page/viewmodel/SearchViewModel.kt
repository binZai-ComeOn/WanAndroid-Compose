package com.binyouwei.wanandroid_compose.ui.page.viewmodel

import androidx.lifecycle.MutableLiveData
import com.binyouwei.common.base.BaseViewModel
import com.binyouwei.common.manager.ApiManager
import com.binyouwei.common.network.bean.HotKeyBean
import com.binyouwei.common.network.callback.IApiErrorCallback

/**
 * @author binjx
 * @date 2023/7/24 17:10
 * @purpose：
 **/
class SearchViewModel : BaseViewModel() {
    val hotKeyList = MutableLiveData<MutableList<HotKeyBean>>()

    fun getHotKeyList() {

    }
}