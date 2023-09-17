package com.binyouwei.wanandroid_compose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.ui.main.home.HomePage
import com.binyouwei.wanandroid_compose.ui.main.knowledge_system.KnowledgeSystemPage
import com.binyouwei.wanandroid_compose.ui.main.square.SquarePage
import com.binyouwei.wanandroid_compose.ui.main.wechat.WeChatPage
import com.binyouwei.wanandroid_compose.route.RouteName
import com.binyouwei.wanandroid_compose.ui.main.project.ProjectPage
import com.binyouwei.wanandroid_compose.ui.widget.BottomNavigationBar
import com.binyouwei.wanandroid_compose.ui.sidebar.Sidebar
import com.binyouwei.wanandroid_compose.ui.widget.MyAlertDialog
import com.binyouwei.wanandroid_compose.ui.widget.bnbsMain
import com.blankj.utilcode.util.LogUtils
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * @author 宾有为
 * Created on 2023/7/26 22:27
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BottomNav()
        }
    }


    @Composable
    fun BottomNav() {
        val navCtrl = rememberNavController()
        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()
        val drawerState = scaffoldState.drawerState
        LogUtils.e("1111111111111111111111111")
        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding(),
            bottomBar = {
                // 设置底部导航栏
                BottomNavigationBar(navCtrl = navCtrl, bnbsMain)
            },
            drawerContent = {
                Sidebar(
                    this@MainActivity,
                    scaffoldState.snackbarHostState
                ) { coroutineScope.launch { drawerState.close() } }
            },
            content = {
                it.calculateBottomPadding()
                NavHost(
                    navController = navCtrl,
                    startDestination = RouteName.HOME
                ) {
                    // 首页
                    composable(route = RouteName.HOME) {
                        HomePage(this@MainActivity, scaffoldState)
                    }

                    // 广场
                    composable(route = RouteName.SQUARE) {
                        SquarePage(this@MainActivity,scaffoldState)
                    }

                    // 体系
                    composable(route = RouteName.SYSTEM) {
                        KnowledgeSystemPage(this@MainActivity, scaffoldState)
                    }

                    // 公众号
                    composable(route = RouteName.WeChat) {
                        WeChatPage(this@MainActivity, scaffoldState)
                    }

                    // 项目
                    composable(route = RouteName.PROJECTS) {
                        ProjectPage(this@MainActivity, scaffoldState)
                    }
                }
            },
            snackbarHost = {
                SnackbarHost(
                    hostState = scaffoldState.snackbarHostState
                ) { data ->
                    Snackbar(
                        snackbarData = data
                    )
                }
            }
        )
    }
}