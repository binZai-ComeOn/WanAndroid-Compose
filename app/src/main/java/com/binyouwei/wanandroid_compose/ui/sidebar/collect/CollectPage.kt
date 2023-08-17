package com.binyouwei.wanandroid_compose.ui.sidebar.collect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import com.binyouwei.wanandroid_compose.ui.WebActivity
import com.binyouwei.wanandroid_compose.ui.sidebar.SidebarViewModel
import com.binyouwei.wanandroid_compose.ui.widget.ArticleItem
import com.binyouwei.wanandroid_compose.ui.widget.TopBar
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author 宾有为
 * Created on 2023/7/26 22:27
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/
@AndroidEntryPoint
class CollectPage : ComponentActivity() {
    //lateinit var collectIds : ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //collectIds = intent.getIntegerArrayListExtra(AppConstant.ExtraKey)!!
            setContentLayout()
        }
    }

    @Composable
    private fun setContentLayout() {
        val viewModel: SidebarViewModel = hiltViewModel()
        viewModel.getCollectArticles()
        val pagingItems = viewModel.collectArticles.value?.collectAsLazyPagingItems()
        Scaffold(topBar = {
            TopBar(this, title = stringResource(id = R.string.collect))
        }) {
            it.calculateBottomPadding()
            Column() {
                LazyColumn() {
                    if (pagingItems != null && pagingItems.itemCount > 0) {
                        itemsIndexed(pagingItems) { _, item ->
                            ArticleItem(item!!) { webData ->
                                ActivityMessenger.startActivity<WebActivity>(
                                    this@CollectPage,
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