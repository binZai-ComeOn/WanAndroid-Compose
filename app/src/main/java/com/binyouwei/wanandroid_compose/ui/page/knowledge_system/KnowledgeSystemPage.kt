package com.binyouwei.wanandroid_compose.ui.page.knowledge_system

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.ui.page.viewmodel.SearchPage
import com.binyouwei.wanandroid_compose.ui.page.home.HomeViewModel
import com.binyouwei.wanandroid_compose.ui.widget.TopSearchMenuBar

@Composable
fun KnowledgeSystemPage(
    activity: ComponentActivity,
    scaffoldState: ScaffoldState,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = scaffoldState.drawerState
    val rowMenu = mutableListOf<String>(
        stringResource(id = R.string.system),
        stringResource(id = R.string.navigation)
    )
    var rowIndex = remember { mutableStateOf(0) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopSearchMenuBar(
                title = stringResource(id = R.string.system),
                coroutineScope = coroutineScope,
                drawerState = drawerState,
                elevation = false,
                onClickSearch = { activity.startActivity(Intent(activity, SearchPage::class.java)) }
            )
        }) { pv ->
        pv.calculateBottomPadding()
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
    }
}