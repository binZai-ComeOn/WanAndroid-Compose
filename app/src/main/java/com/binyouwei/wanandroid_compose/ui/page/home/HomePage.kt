package com.binyouwei.wanandroid_compose.ui.page.home

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import com.binyouwei.wanandroid_compose.ui.page.WebPage
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
    viewModel.run {
        getTopArticles()
        getHomeData()
    }
    val pagingItems = viewModel.articles.value?.collectAsLazyPagingItems()
    val topArticles = remember {
        viewModel.topArticles
    }
    Scaffold(topBar = {
        TopSearchMenuBar(
            title = stringResource(id = R.string.wan_android),
            coroutineScope = coroutineScope,
            drawerState = drawerState,
            onClickSearch = { ActivityMessenger.startActivity<SearchPage>(activity) }
        )
    }) {
        it.calculateBottomPadding()
        Column() {
            LazyColumn() {
                if (topArticles.isNotEmpty()) {
                    itemsIndexed(topArticles) { _, item ->
                        item.top = "1"
                        ArticleItem(item) {webData ->
                            ActivityMessenger.startActivity<WebPage>(activity,AppConstant.ExtraKey to webData)
                        }
                    }
                }
                if (pagingItems != null && pagingItems.itemCount > 0) {
                    itemsIndexed(pagingItems) { _, item ->
                        ArticleItem(item!!) { webData ->
                            ActivityMessenger.startActivity<WebPage>(activity,AppConstant.ExtraKey to webData)
                        }
                    }
                }
            }
        }
    }

}
