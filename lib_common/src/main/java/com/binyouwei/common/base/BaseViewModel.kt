package com.binyouwei.common.base

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * @desc   viewModel基类
 */
abstract class BaseViewModel : ViewModel() {


    var currentListIndex = mutableStateOf(0)

    var loading = mutableStateOf(false)

    private var _isInited = mutableStateOf(false)

    var message = mutableStateOf("")

    private val isInited: Boolean
        get() = _isInited.value

    private fun requestInitialized() {
        _isInited.value = true
    }

    fun resetListIndex() {
        currentListIndex.value = 0
    }

    fun resetInitState() {
        _isInited.value = false
    }

    fun async(block: suspend ()-> Unit) {
        viewModelScope.launch { block() }
    }

//    abstract fun start()

    fun initThat(block: () -> Unit) {
        if (!isInited) {
            block.invoke()
            requestInitialized()
        }
    }

    fun savePosition(index: Int) {
        currentListIndex.value = index
        println("## save position = $index ##")
    }

    fun stopLoading() {
        loading.value = false
    }

    fun startLoading() {
        loading.value = true
    }

    open fun loadContent() { }

}