package com.binyouwei.wanandroid_compose.ui.sidebar.seting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.ui.widget.SettingItem
import com.binyouwei.wanandroid_compose.ui.widget.TopBar

class SetingPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentLayout()
        }
    }

    @Composable
    private fun setContentLayout() {
        Scaffold(topBar = {
            TopBar(this, stringResource(id = R.string.seting))
        }) {
            it.calculateBottomPadding()
            Column {
                LazyColumn(content = {
                    itemsIndexed { index, item ->
                        SettingItem()
                    }
                })
            }
        }
    }
}