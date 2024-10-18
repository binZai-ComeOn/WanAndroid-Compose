package com.binyouwei.wanandroid_compose.ui.main.knowledge_system

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.binyouwei.common.bean.NavigationBean
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import com.binyouwei.wanandroid_compose.ui.WebActivity
import com.binyouwei.wanandroid_compose.ui.main.MainViewModel
import com.binyouwei.wanandroid_compose.ui.main.search.SearchPage
import com.binyouwei.wanandroid_compose.ui.widget.KnowledgeSystemItem
import com.binyouwei.wanandroid_compose.ui.widget.MyArticleFlowRow
import com.binyouwei.wanandroid_compose.ui.widget.TopSearchMenuBar
import kotlinx.coroutines.launch

@Composable
fun KnowledgeSystemPage(
    activity: ComponentActivity,
    scaffoldState: ScaffoldState,
    viewModel: MainViewModel = hiltViewModel(),
) {
    viewModel.run {
        getKnowledgeTree()
        getNavJson()
    }
    val knowledgeSystems = remember {
        viewModel.knowledgeSystems
    }
    val navigationMenu = remember {
        viewModel.navigationMenu
    }
    val coroutineScope = rememberCoroutineScope()
    val drawerState = scaffoldState.drawerState
    val rowMenu = mutableListOf(
        stringResource(id = R.string.system),
        stringResource(id = R.string.navigation)
    )
    val rowIndex = remember { mutableStateOf(0) }
    val navController = rememberNavController()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopSearchMenuBar(
                title = stringResource(id = R.string.system),
                coroutineScope = coroutineScope,
                drawerState = drawerState,
                elevation = false,
                onClickSearch = { ActivityMessenger.startActivity<SearchPage>(activity) }
            )
        }) { pv ->
        pv.calculateBottomPadding()
        Column(modifier = Modifier.fillMaxWidth()) {
            ScrollableTabRow(
                edgePadding = 0.dp,
                selectedTabIndex = rowIndex.value,
                backgroundColor = colorResource(id = R.color.colorPrimary)
            ) {
                rowMenu.forEachIndexed { index: Int, text: String ->
                    Tab(
                        text = { Text(text = text) },
                        selected = rowIndex.value == index,
                        onClick = { rowIndex.value = index })
                }
            }
            val lazyListState = rememberLazyListState()
            when (rowIndex.value) {
                0 -> {
                    LazyColumn {
                        if (knowledgeSystems.isNotEmpty()) {
                            itemsIndexed(knowledgeSystems) { _, item ->
                                KnowledgeSystemItem(item) {
                                    ActivityMessenger.startActivity<KnowledgeSystemActivity>(
                                        activity,
                                        AppConstant.ExtraKey to it
                                    )
                                }
                            }
                        }
                    }
                }

                1 -> {
                    Row {
                        LazyColumn {
                            item {
                                NavigationRail {
                                    val currentDestination =
                                        navController.currentBackStackEntryAsState().value?.destination
                                    navigationMenu.forEachIndexed { index: Int, item: NavigationBean ->
                                        NavigationRailItem(
                                            icon = {},
                                            label = { Text(item.name) },
                                            selected = currentDestination?.route?.let { it == item.name }
                                                ?: false,
                                            onClick = {
                                                navController.navigate(item.name)
                                                coroutineScope.launch {
                                                    lazyListState.animateScrollToItem(index)
                                                }
                                            }
                                        )
                                    }
                                }
                            }
                        }
                        LazyColumn(
                            state = lazyListState,
                            content = {
                                itemsIndexed(navigationMenu) { _: Int, item: NavigationBean ->
                                    Text(
                                        text = item.name,
                                        color = colorResource(id = R.color.item_title),
                                        modifier = Modifier.padding(
                                            top = 16.dp,
                                            bottom = 10.dp,
                                            start = 10.dp
                                        ),
                                        fontSize = 16.sp
                                    )
                                    MyArticleFlowRow(
                                        item.articles,
                                        modifier = Modifier.padding(start = 16.dp)
                                    ) { webData ->
                                        ActivityMessenger.startActivity<WebActivity>(
                                            activity,
                                            AppConstant.ExtraKey to webData
                                        )
                                    }
                                }
                            })
                    }
                    if (navigationMenu.size > 0) {
                        NavHost(
                            navController = navController,
                            startDestination = navigationMenu[0].name
                        ) {
                            navigationMenu.forEach { item ->
                                composable(route = item.name) { Text(text = item.name) }
                            }
                        }
                    }
                }
            }
        }
    }
}