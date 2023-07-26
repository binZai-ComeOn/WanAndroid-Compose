package com.binyouwei.wanandroid_compose.ui.widget

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @author binjx
 * @date 2023/7/26 11:04
 * @purpose：
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