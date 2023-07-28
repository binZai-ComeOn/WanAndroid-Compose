package com.binyouwei.wanandroid_compose.ui.main.square

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import com.binyouwei.wanandroid_compose.ui.WebPage
import com.binyouwei.wanandroid_compose.ui.main.MainViewModel
import com.binyouwei.wanandroid_compose.ui.sidebar.share.ShareArticlePage
import com.binyouwei.wanandroid_compose.ui.widget.ArticleItem
import kotlinx.coroutines.launch

@Composable
fun SquarePage(
    activity: ComponentActivity,
    scaffoldState: ScaffoldState,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = scaffoldState.drawerState
    viewModel.getSquareList()
    val squares = viewModel.squares.value?.collectAsLazyPagingItems()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = colorResource(id = R.color.colorPrimary),
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch { if (drawerState.isClosed) drawerState.open() else drawerState.close() }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = stringResource(id = R.string.menu)
                        )
                    }
                },
                title = { Text(text = stringResource(id = R.string.square)) },
                actions = {
                    IconButton(onClick = { ActivityMessenger.startActivity<ShareArticlePage>(activity) }) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = stringResource(R.string.add)
                        )
                    }
                }
            )
        }) { sc ->
        sc.calculateBottomPadding()
        Column {
            LazyColumn {
                if (squares != null) {
                    itemsIndexed(squares) { _, item ->
                        ArticleItem(item!!) { webData ->
                            ActivityMessenger.startActivity<WebPage>(
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