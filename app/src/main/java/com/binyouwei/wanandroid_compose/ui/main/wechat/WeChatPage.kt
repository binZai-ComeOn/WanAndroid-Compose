package com.binyouwei.wanandroid_compose.ui.main.wechat

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import com.binyouwei.wanandroid_compose.ui.WebActivity
import com.binyouwei.wanandroid_compose.ui.main.MainViewModel
import com.binyouwei.wanandroid_compose.ui.main.search.SearchPage
import com.binyouwei.wanandroid_compose.ui.widget.ArticleItem
import com.binyouwei.wanandroid_compose.ui.widget.TopSearchMenuBar

@Composable
fun WeChatPage(
    activity: ComponentActivity,
    scaffoldState: ScaffoldState,
    viewModel: MainViewModel = hiltViewModel()
) {
    viewModel.getWXChapters()
    val tabs = remember {
        viewModel.wxChapterTabs
    }
    val coroutineScope = rememberCoroutineScope()
    val drawerState = scaffoldState.drawerState
    val rowIndex = remember { mutableStateOf(0) }
    val id = remember { mutableStateOf(0) }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopSearchMenuBar(
                title = stringResource(id = R.string.wechat),
                coroutineScope = coroutineScope,
                drawerState = drawerState,
                elevation = false,
                onClickSearch = { ActivityMessenger.startActivity<SearchPage>(activity) }
            )
        }) {
        it.calculateBottomPadding()
        Column(modifier = Modifier.fillMaxWidth()) {
            if (tabs.value != null) {
                ScrollableTabRow(
                    edgePadding = 0.dp,
                    selectedTabIndex = rowIndex.value,
                    backgroundColor = colorResource(id = R.color.colorPrimary)
                ) {
                    tabs.value!!.forEachIndexed { index: Int, item ->
                        Tab(
                            text = { Text(text = item.name) },
                            selected = rowIndex.value == index,
                            onClick = {
                                rowIndex.value = index
                                id.value = item.id
                            })
                    }
                }
                viewModel.getWXChapterArticles(if (id.value == 0) tabs.value!![0].id else id.value)
                val wxChapterArticles =
                    viewModel.wxChapterArticles.value?.collectAsLazyPagingItems()
                LazyColumn {
                    if (wxChapterArticles != null) {
                        itemsIndexed(wxChapterArticles) { _, item ->
                            ArticleItem(article = item!!, onClick = { webData ->
                                ActivityMessenger.startActivity<WebActivity>(
                                    activity,
                                    AppConstant.ExtraKey to webData
                                )
                            })
                        }
                    }
                }
            }
        }
    }
}