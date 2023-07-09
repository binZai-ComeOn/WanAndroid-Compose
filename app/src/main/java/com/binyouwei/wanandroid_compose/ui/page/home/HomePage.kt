package com.binyouwei.wanandroid_compose.ui.page.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun HomePage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    homeIndex: Int = 0,
    viewModel: HomeViewModel = hiltViewModel(),
    onPageSelected: (position: Int) -> Unit){

    val scopeState = rememberCoroutineScope()

    Column {
        HomeSearchBar(
            onUserIconClick = {

            },
            onSearchClick = {

            },
            onRightIconClick = {

            }
        )
    }
}

@Composable
fun HomeSearchBar(
    onUserIconClick: ()-> Unit,
    onSearchClick: () -> Unit,
    onRightIconClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
    ) {
        //搜索框
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .height(25.dp)
                .align(alignment = Alignment.CenterVertically)
                .weight(1f)
                .background(
                    color = Color.Blue,
                    shape = RoundedCornerShape(12.5.dp)
                )
                .clickable { onSearchClick() }
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp)
            )
        }

    }
}

