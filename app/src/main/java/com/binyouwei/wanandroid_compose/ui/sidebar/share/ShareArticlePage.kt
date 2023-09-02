package com.binyouwei.wanandroid_compose.ui.sidebar.share

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.ui.sidebar.SidebarViewModel
import com.binyouwei.wanandroid_compose.ui.widget.TopBar
import com.binyouwei.wanandroid_compose.ui.widget.snackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author 宾有为
 * Created on 2023/7/26 22:27
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/
@AndroidEntryPoint
class ShareArticlePage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentLayout()
        }
    }

    @Preview
    @Composable
    private fun setContentLayout() {
        val viewModel: SidebarViewModel = hiltViewModel()
        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()
        var title by remember {
            mutableStateOf("")
        }
        var link by remember {
            mutableStateOf("")
        }
        val shareArticleResult by remember {
            viewModel.shareArticleResult
        }
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                val titleTip = stringResource(id = R.string.share_article_title_tip_null)
                val linkTip = stringResource(id = R.string.share_article_link_tip_null)
                val shareSuccess = stringResource(R.string.success_share)
                val shareFail = stringResource(R.string.fail_share)
                TopBar(this, title = stringResource(id = R.string.my_share)) {
                    IconButton(onClick = {
                        if (title.isEmpty()) {
                            snackBar(scaffoldState.snackbarHostState, coroutineScope, titleTip)
                            return@IconButton
                        } else if (link.isEmpty()) {
                            snackBar(scaffoldState.snackbarHostState, coroutineScope, linkTip)
                            return@IconButton
                        }
                        viewModel.shareArticle(title, link)
                        if (shareArticleResult) {
                            snackBar(
                                scaffoldState.snackbarHostState, coroutineScope,
                                shareSuccess
                            )
                            coroutineScope.launch {
                                delay(2000)
                                finish()
                            }
                        } else {
                            snackBar(scaffoldState.snackbarHostState, coroutineScope, shareFail)
                        }
                    }) {
                        Text(text = stringResource(id = R.string.share))
                    }
                }
            }) {
            it.calculateTopPadding()
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.share_article_title),
                    fontSize = 14.sp,
                    color = colorResource(
                        id = R.color.common_color
                    ), modifier = Modifier.padding(bottom = 10.dp)
                )
                OutlinedTextField(
                    value = title,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = stringResource(id = R.string.share_article_title_tip)) },
                    onValueChange = { text ->
                        title = text
                    }, colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(
                            id = R.color.colorPrimary
                        )
                    )
                )
                Text(
                    text = stringResource(id = R.string.share_article_link),
                    fontSize = 14.sp,
                    color = colorResource(
                        id = R.color.common_color
                    ), modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
                )
                OutlinedTextField(
                    value = link,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = stringResource(id = R.string.share_article_link_tip)) },
                    onValueChange = { text ->
                        link = text
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(
                            id = R.color.colorPrimary
                        )
                    )
                )
                Text(
                    text = stringResource(id = R.string.share_article_tip), color = colorResource(
                        id = R.color.item_desc
                    ), fontSize = 12.sp, modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }

}