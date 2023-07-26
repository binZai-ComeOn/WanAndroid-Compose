package com.binyouwei.wanandroid_compose.ui.page.search

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import com.binyouwei.wanandroid_compose.ui.widget.TopBar

/**
 * @author binjx
 * @date 2023/7/26 11:20
 * @purpose：
 **/
class SearchResultPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentLayout()
        }
    }

    @Composable
    private fun setContentLayout() {
        val title = intent.getStringExtra(AppConstant.ExtraKey)
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBar(activity = this, title = title!!, actions = {

                })
            }
        ) {

        }
    }
}