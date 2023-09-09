package com.binyouwei.wanandroid_compose.ui.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.binyouwei.common.bean.BannerBean
import com.binyouwei.common.bean.WebData
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import com.binyouwei.wanandroid_compose.ui.WebActivity
import kotlinx.coroutines.delay


/**
 * @author 宾有为
 * Created on 2023/7/14 15:35
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Banner(banners: MutableList<BannerBean>?,onClick : (BannerBean) -> Unit = {}) {
    val pagerState = rememberPagerState()
    LaunchedEffect(pagerState.settledPage) {
        delay(2000)
        val currentPage =
            if (pagerState.currentPage + 1 == banners!!.size) 0 else pagerState.currentPage + 1
        pagerState.animateScrollToPage(currentPage)
    }
    HorizontalPager(
        pageCount = banners!!.size,
        modifier = Modifier.height(180.dp),
        state = pagerState
    ) { index ->
        Box(modifier = Modifier.clickable { onClick(banners[index]) }) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(banners[index].imagePath)
                    .scale(Scale.FILL)
                    .build(),
                contentDescription = banners[index].title,
                contentScale = ContentScale.FillWidth
            )
            Box(
                modifier = Modifier
                    .height(30.dp)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.bg_transparent)),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = banners[index].title,
                    modifier = Modifier
                        .padding(start = 10.dp),
                    color = colorResource(id = R.color.White),
                    fontSize = 16.sp,
                )
            }
        }
    }
}