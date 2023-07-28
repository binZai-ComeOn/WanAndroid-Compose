package com.binyouwei.wanandroid_compose.ui.main.search

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import com.binyouwei.wanandroid_compose.ui.WebPage
import com.binyouwei.wanandroid_compose.ui.widget.ArticleItem
import com.binyouwei.wanandroid_compose.ui.widget.TopBar
import com.blankj.utilcode.util.LogUtils
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author binjx
 * @date 2023/7/26 11:20
 * @purpose：
 **/
@AndroidEntryPoint
class SearchResultPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentLayout()
        }
    }

    @Composable
    private fun setContentLayout() {
        val scaffoldState = rememberScaffoldState()
        val title = intent.getStringExtra(AppConstant.ExtraKey)
        val viewModel : SearchViewModel = hiltViewModel()
        viewModel.queryArticles(title!!)
        val searchResult = viewModel.searchResult.value?.collectAsLazyPagingItems()
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBar(activity = this, title = title)
            }
        ) {
            it.calculateBottomPadding()
            Column() {
                LazyColumn() {
                    if (searchResult != null) {
                        itemsIndexed(searchResult) { _, item ->
                            LogUtils.e(item?.title)
                            ArticleItem(item!!) { webData ->
                                ActivityMessenger.startActivity<WebPage>(this@SearchResultPage,AppConstant.ExtraKey to webData)
                            }
                        }
                    }
                }
            }
        }
    }
}