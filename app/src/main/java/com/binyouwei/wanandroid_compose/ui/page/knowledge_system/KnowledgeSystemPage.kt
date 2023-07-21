package com.binyouwei.wanandroid_compose.ui.page.knowledge_system

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.ui.page.SearchPage
import com.binyouwei.wanandroid_compose.ui.page.home.HomeViewModel
import com.binyouwei.wanandroid_compose.ui.widget.TopSearchMenuBar

@Composable
fun KnowledgeSystemPage(
    activity: ComponentActivity,
    scaffoldState: ScaffoldState,
    viewModel: HomeViewModel = hiltViewModel(),
    onPageSelected: (position: Int) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = scaffoldState.drawerState
    val rowMenu = mutableListOf<String>(
        stringResource(id = R.string.system),
        stringResource(id = R.string.navigation)
    )

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
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.colorPrimary))
        ) {
            items(rowMenu.size) {
                Text(
                    text = rowMenu[it],
                    fontSize = 14.sp,
                    modifier = Modifier
                        .size(width = 60.dp, height = 50.dp)
                        .wrapContentHeight()
                        .wrapContentWidth()
                )
            }
        }
    }
}