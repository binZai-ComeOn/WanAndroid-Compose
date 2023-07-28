package com.binyouwei.wanandroid_compose.ui.main.wechat

import androidx.activity.ComponentActivity
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.ui.main.MainViewModel
import com.binyouwei.wanandroid_compose.ui.main.search.SearchPage
import com.binyouwei.wanandroid_compose.ui.widget.TopSearchMenuBar

@Composable
fun WeChatPage(
    activity: ComponentActivity,
    scaffoldState: ScaffoldState,
    viewModel: MainViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = scaffoldState.drawerState
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopSearchMenuBar(
                title = stringResource(id = R.string.wechat),
                coroutineScope = coroutineScope,
                drawerState = drawerState,
                onClickSearch = { ActivityMessenger.startActivity<SearchPage>(activity) }
            )
        }) {
        it.calculateBottomPadding()
    }
}