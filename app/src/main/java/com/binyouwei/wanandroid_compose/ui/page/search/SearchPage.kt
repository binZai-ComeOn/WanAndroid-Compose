package com.binyouwei.wanandroid_compose.ui.page.search

import android.content.Intent
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
import androidx.navigation.compose.rememberNavController
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { setContentLayout() }
    }

    @Composable
    fun setContentLayout(viewModel: SearchViewModel = hiltViewModel()) {
        val hotkeys by remember { viewModel.hotKeyList }
        viewModel.getHotKeyList()
        val text = remember { mutableStateOf("") }
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopSearchBar(this, text, scaffoldState = scaffoldState) {
                    ActivityMessenger.startActivity<SearchResultPage>(
                        this,
                        AppConstant.ExtraKey to text.value
                    )
                }
            }) {
            it.calculateBottomPadding()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp)
            ) {
                if (hotkeys.isNotEmpty()) {
                    Text(
                        text = stringResource(id = R.string.hot_search),
                        color = colorResource(id = R.color.theme_pink_color_primary),
                        modifier = Modifier.padding(top = 16.dp, bottom = 10.dp)
                    )
                    FlowRow() {
                        hotkeys.forEach {
                            TextButton(
                                onClick = {
                                    ActivityMessenger.startActivity<SearchResultPage>(
                                        this@SearchPage,
                                        AppConstant.ExtraKey to it.name
                                    )
                                },
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