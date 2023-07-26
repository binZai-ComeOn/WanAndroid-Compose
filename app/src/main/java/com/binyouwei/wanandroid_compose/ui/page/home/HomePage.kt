package com.binyouwei.wanandroid_compose.ui.page.home

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
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
            ArticleItem()
            ArticleItem()
            ArticleItem()
            ArticleItem()
            ArticleItem()
        }
    }

}
