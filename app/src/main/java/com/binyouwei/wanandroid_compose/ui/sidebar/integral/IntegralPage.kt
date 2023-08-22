package com.binyouwei.wanandroid_compose.ui.sidebar.integral

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.binyouwei.common.bean.WebData
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import com.binyouwei.wanandroid_compose.ui.WebActivity
import com.binyouwei.wanandroid_compose.ui.sidebar.SidebarViewModel
import com.binyouwei.wanandroid_compose.ui.widget.TopBar
import com.binyouwei.wanandroid_compose.ui.widget.UserScoreItem
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author 宾有为
 * Created on 2023/7/26 22:27
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/
@AndroidEntryPoint
class IntegralPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentLayout()
        }
    }

    @Composable
    private fun setContentLayout() {
        val viewModel: SidebarViewModel = hiltViewModel()
        viewModel.getUserScoreList()
        val integralList = viewModel.integrals.value?.collectAsLazyPagingItems()
        Scaffold(topBar = {
            TopBar(this, title = stringResource(id = R.string.points_details)) {
                val pointsRule = stringResource(id = R.string.points_rule)
                IconButton(onClick = {
                    ActivityMessenger.startActivity<WebActivity>(
                        this@IntegralPage, AppConstant.ExtraKey to WebData(
                            pointsRule, "https://www.wanandroid.com/blog/show/2653"
                        )
                    )
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_help_white_24dp),
                        contentDescription = stringResource(id = R.string.help)
                    )
                }
            }
        }) {
            it.calculateBottomPadding()
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .background(
                            colorResource(id = R.color.colorPrimary)
                        )
                        .height(150.dp)
                        .fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    val key = intent.getStringExtra(AppConstant.ExtraKey)
                    Text(
                        text = key ?: stringResource(id = R.string.nav_line_4),
                        fontSize = 50.sp,
                        color = colorResource(id = R.color.White)
                    )
                }
                LazyColumn() {
                    if (integralList != null) {
                        itemsIndexed(integralList) { _, item ->
                            UserScoreItem(item!!)
                        }
                    }
                }
            }
        }
    }
}