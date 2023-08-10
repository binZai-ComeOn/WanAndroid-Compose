package com.binyouwei.wanandroid_compose.ui.main.search

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import com.binyouwei.wanandroid_compose.ui.widget.MyAlertDialog
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
        val searchHistorys by remember { viewModel.searchHistory }
        viewModel.getHotKeyList()
        viewModel.getSearchHistory()
        val text = remember { mutableStateOf("") }
        val scaffoldState = rememberScaffoldState()
        var showDialog by remember {
            mutableStateOf(false)
        }
        val onPopupDismissed = { showDialog = false }
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopSearchBar(this, text, scaffoldState = scaffoldState) {
                    viewModel.insertSearchHistory(it)
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
                if (searchHistorys.isNotEmpty()) {
                    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                        val (text, icon) = createRefs()
                        Text(
                            text = stringResource(id = R.string.search_history),
                            color = colorResource(id = R.color.theme_pink_color_primary),
                            modifier = Modifier
                                .constrainAs(text) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    bottom.linkTo(parent.bottom)
                                }
                                .padding(top = 10.dp, bottom = 10.dp),
                            textAlign = TextAlign.Center
                        )
                        IconButton(onClick = {
                            showDialog = true
                        }, modifier = Modifier.constrainAs(icon) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }) {
                            Icon(
                                painter = painterResource(id = android.R.drawable.ic_menu_delete),
                                contentDescription = stringResource(
                                    id = R.string.delete
                                ),
                                modifier = Modifier.size(18.dp),
                                tint = colorResource(id = R.color.Grey500)
                            )
                        }
                    }
                    FlowRow() {
                        searchHistorys.forEach { searchHistory ->
                            TextButton(
                                onClick = {
                                    ActivityMessenger.startActivity<SearchResultPage>(
                                        this@SearchPage,
                                        AppConstant.ExtraKey to searchHistory.name
                                    )
                                },
                                contentPadding = PaddingValues(5.dp, 0.dp),
                                shape = RoundedCornerShape(0.dp)
                            ) {
                                Text(
                                    text = searchHistory.name, modifier = Modifier
                                        .background(colorResource(id = R.color.theme_pink_color_primary))
                                        .padding(10.dp),
                                    color = colorResource(id = R.color.White)
                                )
                            }
                        }
                    }
                }

            }
        }
        if (showDialog) {
            MyAlertDialog(
                title = R.string.delete_search_history,
                text = R.string.is_delete_search_history,
                onPopupDismissed = onPopupDismissed,
                confirm = {
                    viewModel.deleteSearchHistory()
                    showDialog = false
                }
            )
        }
    }
}