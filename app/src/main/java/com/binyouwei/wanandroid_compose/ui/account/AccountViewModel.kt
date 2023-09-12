package com.binyouwei.wanandroid_compose.ui.account

import androidx.compose.runtime.mutableStateOf
import com.binyouwei.common.base.BaseViewModel
import com.binyouwei.common.network.HttpResult
import com.binyouwei.common.network.repository.HttpRepository
import com.binyouwei.common.utils.DataStoreUtils
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

/**
 * @author 宾有为
 * Created on 2023/8/5 21:54
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/
@HiltViewModel
class AccountViewModel @Inject constructor(val repository: HttpRepository) : BaseViewModel() {
    val result = mutableStateOf(false)
    val errorMsg = mutableStateOf<String?>("")

    fun login(username: String, password: String) {
        errorMsg.value = ""
        async {
            repository.login(username, password).collectLatest { response ->
                when (response) {
                    is HttpResult.Success -> {
                        result.value = true
                        val value = response.result
                        DataStoreUtils.putData(AppConstant.UserId, value.id)
                        DataStoreUtils.putData(AppConstant.UserType, value.type)
                        DataStoreUtils.putData(AppConstant.UserNickname, value.nickname)
                        DataStoreUtils.putData(AppConstant.UserPublicName, value.publicName)
                        DataStoreUtils.putData(AppConstant.UserCoinCount, value.coinCount)
                    }

                    is HttpResult.Error -> {
                        errorMsg.value = response.exception.message
                    }
                }
            }
        }
    }

    fun register(
        username: String,
        password: String,
        repassword: String,
    ) {
        errorMsg.value = ""
        async {
            repository.register(username, password, repassword).collectLatest { response ->
                when (response) {
                    is HttpResult.Success -> {
                        result.value = true
                    }

                    is HttpResult.Error -> {
                        errorMsg.value = response.exception.message
                    }
                }
            }
        }
    }
}