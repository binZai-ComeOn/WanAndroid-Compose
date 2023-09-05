package com.binyouwei.wanandroid_compose.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.binyouwei.common.bean.BannerBean
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

/**
 * @author 宾有为
 * Created on 2023/7/14 15:35
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/
@Preview
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Banner() {
    val list = mutableListOf<String>(
        "https://www.wanandroid.com/blogimgs/42da12d8-de56-4439-b40c-eab66c227a4b.png",
        "https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png",
        "https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png"
    )
    HorizontalPager(state = pagerState, dragEnabled = false) { page ->
        onPageSelected(0)

    }
}