package com.binyouwei.wanandroid_compose.ui.page.project

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.binyouwei.wanandroid_compose.ui.page.home.HomeViewModel

@Composable
fun ProjectPage(navCtrl: NavHostController,
                homeIndex: Int = 0,
                viewModel: HomeViewModel = hiltViewModel(),
                onPageSelected: (position: Int) -> Unit){

}
