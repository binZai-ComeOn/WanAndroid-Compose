package com.binyouwei.wanandroid_compose.ui.widget

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

/**
 * @author 宾有为
 * Created on 2023/7/14 15:35
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
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
//            0 -> SquarePage(navCtrl = navCtrl) { }
//            1 -> ProjectPage(navCtrl = navCtrl) { }
        }
    }
}