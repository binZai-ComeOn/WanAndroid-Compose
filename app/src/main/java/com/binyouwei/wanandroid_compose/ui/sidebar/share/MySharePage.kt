package com.binyouwei.wanandroid_compose.ui.sidebar.share

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import com.binyouwei.wanandroid_compose.ui.WebActivity
import com.binyouwei.wanandroid_compose.ui.sidebar.SidebarViewModel
import com.binyouwei.wanandroid_compose.ui.widget.ArticleItem
import com.binyouwei.wanandroid_compose.ui.widget.TopBar
import com.blankj.utilcode.util.LogUtils
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel

/**
 * @author 宾有为
 * Created on 2023/7/26 22:27
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/
@AndroidEntryPoint
class MySharePage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentLayout()
        }
    }

    @Composable
    private fun setContentLayout() {
        val viewModel: SidebarViewModel = hiltViewModel()
        viewModel.getShareList()
        var requestResult by remember {
            viewModel.getShareArticleResult
        }
        // val shares = viewModel.shares.value?.collectAsLazyPagingItems()
        Scaffold(topBar = {
            TopBar(this, title = stringResource(id = R.string.share)) {
                IconButton(onClick = { ActivityMessenger.startActivity<ShareArticlePage>(this@MySharePage) }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(R.string.add)
                    )
                }
            }
        }) {
            it.calculateBottomPadding()
            Column() {
                LazyColumn(content = {
                    if (requestResult) {
                        val value = viewModel.shares.value
                        itemsIndexed(value?.shareArticles?.datas!!) { _, item ->
                            ArticleItem(item) { webData ->
                                ActivityMessenger.startActivity<WebActivity>(
                                    this@MySharePage,
                                    AppConstant.ExtraKey to webData
                                )
                            }
                        }
                    }
                })
            }
        }
    }
}