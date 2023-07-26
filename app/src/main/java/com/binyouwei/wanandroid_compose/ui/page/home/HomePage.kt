package com.binyouwei.wanandroid_compose.ui.page.home

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.ui.page.search.SearchPage
import com.binyouwei.wanandroid_compose.ui.widget.ArticleItem
import com.binyouwei.wanandroid_compose.ui.widget.TopSearchMenuBar

@Composable
fun HomePage(
    activity: ComponentActivity,
    scaffoldState: ScaffoldState,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = scaffoldState.drawerState
    viewModel.getTopArticles()
    val topArticles by remember {
        viewModel.topArticles
    }
    Scaffold(topBar = {
        TopSearchMenuBar(
            title = stringResource(id = R.string.wan_android),
            coroutineScope = coroutineScope,
            drawerState = drawerState,
            onClickSearch = { activity.startActivity(Intent(activity, SearchPage::class.java)) }
        )
    }) {
        it.calculateBottomPadding()
        Column() {
            if (topArticles.isNotEmpty()){
                topArticles.forEach {
                    it.top = "1"
                    ArticleItem(it)
                }
            }
        }
    }

}
