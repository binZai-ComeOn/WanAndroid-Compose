package com.binyouwei.wanandroid_compose.ui.page

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.ui.widget.TopSearchBar
import com.google.accompanist.flowlayout.FlowRow
import com.google.android.material.internal.FlowLayout
import kotlinx.coroutines.launch

class SearchPage : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val rememberScaffoldState = rememberScaffoldState()
            val coroutineScope = rememberCoroutineScope()
            val drawerState = rememberScaffoldState.drawerState
            val text = remember { mutableStateOf("") }
            Scaffold(topBar = {
                TopSearchBar(this) {

                }
            }) {
                it.calculateBottomPadding()
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(id = R.string.hot_search),
                        color = colorResource(id = R.color.theme_pink_color_primary)
                    )
                    FlowRow() {
                        Button(
                            {}, shape = RoundedCornerShape(10.dp),
                        ) {
                            Text(text = "测试")
                        }
                    }
                }
            }
        }
    }
}