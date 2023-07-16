package com.binyouwei.wanandroid_compose.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.binyouwei.wanandroid_compose.ui.page.home.HomeViewModel
@Composable
fun RankingListPage(navCtrl: NavHostController,
                      homeIndex: Int = 0,
                      viewModel: HomeViewModel = hiltViewModel(),
                      onPageSelected: (position: Int) -> Unit) {
    Column(Modifier.fillMaxSize()) {
        LazyColumn {
            item {

            }
        }
    }
}