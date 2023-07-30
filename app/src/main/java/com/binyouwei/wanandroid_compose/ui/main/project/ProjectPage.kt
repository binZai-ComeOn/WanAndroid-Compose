package com.binyouwei.wanandroid_compose.ui.main.project

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
import com.binyouwei.wanandroid_compose.ui.widget.ProjectItem
import com.binyouwei.wanandroid_compose.ui.widget.TopSearchMenuBar
import com.blankj.utilcode.util.LogUtils

@Composable
fun ProjectPage(
    activity: ComponentActivity,
    scaffoldState: ScaffoldState,
    viewModel: MainViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = scaffoldState.drawerState
    val rowIndex = remember {
        mutableStateOf(0)
    }
    val id = remember {
        mutableStateOf(0)
    }
    viewModel.getProjectTabs()
    val projectTabs = remember {
        viewModel.projectTabs
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopSearchMenuBar(
                title = stringResource(id = R.string.projects),
                coroutineScope = coroutineScope,
                drawerState = drawerState,
                elevation = false,
                onClickSearch = {
                    ActivityMessenger.startActivity<SearchPage>(activity)
                }
            )
        }) {
        it.calculateBottomPadding()
        Column(modifier = Modifier.fillMaxWidth()) {
            if (projectTabs.value != null) {
                ScrollableTabRow(
                    edgePadding = 0.dp,
                    selectedTabIndex = rowIndex.value,
                    backgroundColor = colorResource(id = R.color.colorPrimary)
                ) {
                    projectTabs.value?.forEachIndexed { index, bean ->
                        Tab(
                            text = { Text(text = bean.name) },
                            selected = rowIndex.value == index,
                            onClick = {
                                rowIndex.value = index
                                id.value = bean.id
                            })
                    }
                }
                viewModel.getProjectTabArticles(if (id.value == 0) projectTabs.value!![0].id else id.value)
                val articles =
                    viewModel.projectTabArticles.value?.collectAsLazyPagingItems()
                LazyColumn() {
                    if (articles != null) {
                        itemsIndexed(articles) { _, bean ->
                            ProjectItem(bean!!) { webData ->
                                ActivityMessenger.startActivity<WebActivity>(
                                    activity,
                                    AppConstant.ExtraKey to webData
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
