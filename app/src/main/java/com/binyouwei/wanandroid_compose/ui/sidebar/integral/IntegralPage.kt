package com.binyouwei.wanandroid_compose.ui.sidebar.integral

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.binyouwei.common.bean.WebData
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import com.binyouwei.wanandroid_compose.ui.WebPage
import com.binyouwei.wanandroid_compose.ui.widget.TopBar
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
            Scaffold(topBar = {
                TopBar(this, title = stringResource(id = R.string.points_details)) {
                    val pointsRule = stringResource(id = R.string.points_rule)
                    IconButton(onClick = {
                        ActivityMessenger.startActivity<WebPage>(
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
            }
        }
    }
}