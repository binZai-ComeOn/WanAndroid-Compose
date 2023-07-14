package com.binyouwei.wanandroid_compose.ui.widget

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.binyouwei.wanandroid_compose.ui.page.project.ProjectPage
import com.binyouwei.wanandroid_compose.ui.page.square.SquarePage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

/**
 * @author binjx
 * @date 2023/7/14 15:35
 * @purpose：
 **/
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Banner(
    navCtrl: NavHostController,
    onPageSelected: (position: Int) -> Unit,
) {
    val pagerState = rememberPagerState(
        pageCount = 2,
        initialPage = 0,
        initialOffscreenLimit = 2
    )
    // Banner
    HorizontalPager(state = pagerState, dragEnabled = false) { page ->
        onPageSelected(0)
        when (page) {
            0 -> SquarePage(navCtrl = navCtrl) { }
            1 -> ProjectPage(navCtrl = navCtrl) { }
        }
    }
}