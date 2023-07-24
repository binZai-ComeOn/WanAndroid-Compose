package com.binyouwei.wanandroid_compose.ui.page.project

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.ui.page.viewmodel.SearchPage
import com.binyouwei.wanandroid_compose.ui.page.home.HomeViewModel
import com.binyouwei.wanandroid_compose.ui.widget.TopSearchMenuBar

@Composable
fun ProjectPage(
    activity: ComponentActivity,
    scaffoldState: ScaffoldState,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = scaffoldState.drawerState
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
        TopSearchMenuBar(
            title = stringResource(id = R.string.projects),
            coroutineScope = coroutineScope,
            drawerState = drawerState,
            onClickSearch = { activity.startActivity(Intent(activity, SearchPage::class.java)) }
        )
    }) {
        it.calculateBottomPadding()
    }
}
