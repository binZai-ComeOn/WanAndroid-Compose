package com.binyouwei.wanandroid_compose.ui.main.knowledge_system

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.ui.main.MainViewModel
import com.binyouwei.wanandroid_compose.ui.main.search.SearchPage
import com.binyouwei.wanandroid_compose.ui.widget.KnowledgeSystemItem
import com.binyouwei.wanandroid_compose.ui.widget.TopSearchMenuBar

@Composable
fun KnowledgeSystemPage(
    activity: ComponentActivity,
    scaffoldState: ScaffoldState,
    viewModel: MainViewModel = hiltViewModel(),
) {
    viewModel.run {
        getKnowledgeTree()
    }
    val knowledgeSystems = remember {
        viewModel.knowledgeSystems
    }
    val coroutineScope = rememberCoroutineScope()
    val drawerState = scaffoldState.drawerState
    val rowMenu = mutableListOf<String>(
        stringResource(id = R.string.system),
        stringResource(id = R.string.navigation)
    )
    val rowIndex = remember { mutableStateOf(0) }

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
            when (rowIndex.value) {
                0 -> {
                    LazyColumn {
                        if (knowledgeSystems.isNotEmpty()) {
                            itemsIndexed(knowledgeSystems) { _, item ->
                                KnowledgeSystemItem(item)
                            }
                        }
                    }
                }

                1 -> {
                    Text(text = "1")
                }
            }
        }
    }
}