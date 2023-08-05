package com.binyouwei.wanandroid_compose.ui.account

import com.binyouwei.common.base.BaseViewModel
import com.binyouwei.common.network.repository.HttpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author 宾有为
 * Created on 2023/8/5 21:54
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/
@HiltViewModel
class AccountViewModel @Inject constructor(val repository: HttpRepository) : BaseViewModel() {

    fun login() {

    }

    fun register() {

    }
}