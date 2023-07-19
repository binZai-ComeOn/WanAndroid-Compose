package com.binyouwei.wanandroid_compose.ui.page.home

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.ui.page.SearchPage
import com.binyouwei.wanandroid_compose.ui.widget.TopSearchMenuBar

@Composable
fun HomePage(
    activity: ComponentActivity,
    scaffoldState: ScaffoldState,
    viewModel: HomeViewModel = hiltViewModel(),
    onPageSelected: (position: Int) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = scaffoldState.drawerState
    TopSearchMenuBar(
        title = stringResource(id = R.string.wan_android),
        drawerState = drawerState,
        onClickSearch = { activity.startActivity(Intent(activity, SearchPage::class.java)) }
    ) {

    }
}
