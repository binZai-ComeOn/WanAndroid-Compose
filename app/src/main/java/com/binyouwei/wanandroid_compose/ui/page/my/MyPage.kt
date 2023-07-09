package com.binyouwei.wanandroid_compose.ui.page.my

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.binyouwei.wanandroid_compose.ui.page.home.HomeSearchBar
import com.binyouwei.wanandroid_compose.ui.page.home.HomeViewModel

@Composable
fun MyPage(
    navCtrl: NavHostController,
    homeIndex: Int = 0,
    viewModel: HomeViewModel = hiltViewModel(),
    onPageSelected: (position: Int) -> Unit) {
    Column(modifier = Modifier.background(Color.Cyan)) {
        Text(text = "我的")
    }
}