package com.binyouwei.wanandroid_compose.ui.main.knowledge_system

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.binyouwei.common.bean.KnowledgeSystemBean
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import com.binyouwei.wanandroid_compose.ui.main.MainViewModel
import com.binyouwei.wanandroid_compose.ui.widget.ArticleItem
import com.binyouwei.wanandroid_compose.ui.widget.TopBar
import dagger.hilt.android.AndroidEntryPoint
import com.binyouwei.wanandroid_compose.ui.WebActivity
import com.blankj.utilcode.util.LogUtils

@AndroidEntryPoint
class KnowledgeSystemActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentLayout()
        }
    }

    @Preview
    @Composable
    private fun setContentLayout() {
        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(AppConstant.ExtraKey, KnowledgeSystemBean::class.java)
        } else {
            intent.getParcelableExtra<KnowledgeSystemBean>(AppConstant.ExtraKey)
        }
        val rowIndex = remember { mutableStateOf(0) }
        val viewModel: MainViewModel = hiltViewModel()
        viewModel.getKnowledgeList(data?.children!![rowIndex.value].id)
        val knowledges = viewModel.knowledges.value?.collectAsLazyPagingItems()
        Scaffold(topBar = {
            TopBar(activity = this, title = data.name!!, elevation = false)
        }) {
            it.calculateBottomPadding()
            Column() {
                ScrollableTabRow(
                    edgePadding = 0.dp,
                    selectedTabIndex = rowIndex.value,
                    backgroundColor = colorResource(id = R.color.colorPrimary)
                ) {
                    data.children?.forEachIndexed { index, bean ->
                        Tab(
                            text = { Text(text = bean.name!!) },
                            selected = rowIndex.value == index,
                            onClick = { rowIndex.value = index })
                    }
                }
                LazyColumn() {
                    if (knowledges != null) {
                        itemsIndexed(knowledges) { _, bean ->
                            LogUtils.e(bean?.title)
                            ArticleItem(bean!!) { webData ->
                                ActivityMessenger.startActivity<WebActivity>(
                                    this@KnowledgeSystemActivity,
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