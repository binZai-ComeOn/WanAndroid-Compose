package com.binyouwei.common.base

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * @desc   viewModel基类
 */
abstract class BaseViewModel : ViewModel() {

    var message = mutableStateOf("")

    fun async(block: suspend ()-> Unit) {
        viewModelScope.launch { block() }
    }

}