package com.binyouwei.wanandroid_compose.ui.page.square

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.ui.page.home.HomeViewModel

@Composable
fun SquarePage(navCtrl: NavHostController,
               homeIndex: Int = 0,
               viewModel: HomeViewModel = hiltViewModel(),
               onPageSelected: (position: Int) -> Unit){
    Image(painter = painterResource(id = R.drawable.ic_score_white_24dp), contentDescription = "")
}