package com.binyouwei.wanandroid_compose.ui.page.integra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.ui.widget.IntegralRankingItem
import com.binyouwei.wanandroid_compose.ui.widget.TopBar
import com.blankj.utilcode.util.LogUtils
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author 宾有为
 * Created on 2023/7/26 22:27
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/
@AndroidEntryPoint
class RankingListPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentLayout()
        }
    }

    @Composable
    private fun setContentLayout(viewModel: IntegralViewModel = hiltViewModel()) {
        viewModel.getRankingList()
        val rankingList = viewModel.rankingList.value?.collectAsLazyPagingItems()
        Scaffold(topBar = {
            TopBar(this, title = stringResource(id = R.string.points_ranking))
        }) {
            it.calculateBottomPadding()
            LazyColumn {
                if (rankingList != null) {
                    itemsIndexed(rankingList) { _, item ->
                        LogUtils.e("当前列："+ item!!.username)
                        IntegralRankingItem(item)
                    }
                }
            }
        }
    }
}