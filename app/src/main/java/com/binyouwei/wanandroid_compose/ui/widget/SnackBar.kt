package com.binyouwei.wanandroid_compose.ui.widget

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @author 宾有为
 * Created on 2023/7/26 11:04
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/
fun snackBar(
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    message: String = "",
) {
    coroutineScope.launch {
        snackbarHostState.showSnackbar(message = message)
    }
}