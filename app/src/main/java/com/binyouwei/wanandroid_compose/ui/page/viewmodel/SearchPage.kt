package com.binyouwei.wanandroid_compose.ui.page.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.ui.widget.TopSearchBar
import com.google.accompanist.flowlayout.FlowRow
import androidx.compose.runtime.*

class SearchPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { setContentLayout() }
    }

    @Composable
    fun setContentLayout(viewModel: SearchViewModel = hiltViewModel()) {
        val hotkeys by remember { viewModel.hotKeyList }
//        viewModel.getHotKeyList()
//        LogUtils.e("测试村上春树：${a.value?.size}")
        val text = remember { mutableStateOf("") }
        Scaffold(topBar = {
            TopSearchBar(this, text) {

            }
        }) {
            it.calculateBottomPadding()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.hot_search),
                    color = colorResource(id = R.color.theme_pink_color_primary),
                    modifier = Modifier.padding(top = 16.dp, bottom = 10.dp)
                )
                FlowRow() {
                    if (hotkeys.isNotEmpty()){
                        hotkeys.forEach {
                            TextButton(
                                onClick = { text.value = it.name },
                                contentPadding = PaddingValues(5.dp, 0.dp),
                                shape = RoundedCornerShape(0.dp)
                            ) {
                                Text(
                                    text = it.name, modifier = Modifier
                                        .background(colorResource(id = R.color.theme_pink_color_primary))
                                        .padding(10.dp),
                                    color = colorResource(id = R.color.White)
                                )
                            }
                        }
                    }
                }
                Text(
                    text = stringResource(id = R.string.search_history),
                    color = colorResource(id = R.color.theme_pink_color_primary),
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                )
            }
        }
    }
}