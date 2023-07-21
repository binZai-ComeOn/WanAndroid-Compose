package com.binyouwei.wanandroid_compose.ui.page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.*
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.ui.page.wechat.WeChatPage
import com.binyouwei.wanandroid_compose.ui.page.home.HomePage
import com.binyouwei.wanandroid_compose.ui.page.project.ProjectPage
import com.binyouwei.wanandroid_compose.ui.page.square.SquarePage
import com.binyouwei.wanandroid_compose.ui.page.knowledge_system.KnowledgeSystemPage
import com.binyouwei.wanandroid_compose.ui.page.seting.SetingPage
import com.binyouwei.wanandroid_compose.ui.route.RouteName
import com.binyouwei.wanandroid_compose.ui.widget.BottomNavigationBar
import com.binyouwei.wanandroid_compose.ui.widget.Drawer
import com.binyouwei.wanandroid_compose.ui.widget.bnbsMain
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainPage : ComponentActivity() {

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
                Drawer(this@MainPage) { coroutineScope.launch { drawerState.close() } }
            },
            content = {
                it.calculateBottomPadding()
                NavHost(
                    navController = navCtrl,
                    startDestination = RouteName.HOME
                ) {
                    // 首页
                    composable(route = RouteName.HOME) {
                        HomePage(this@MainPage, scaffoldState)
                    }

                    // 广场
                    composable(route = RouteName.SQUARE) {
                        SquarePage(this@MainPage,scaffoldState)
                    }

                    // 体系
                    composable(route = RouteName.SYSTEM) {
                        KnowledgeSystemPage(this@MainPage,scaffoldState)
                    }

                    // 公众号
                    composable(route = RouteName.WeChat) {
                        WeChatPage(this@MainPage,scaffoldState)
                    }

                    // 项目
                    composable(route = RouteName.PROJECTS) {
                        ProjectPage(this@MainPage,scaffoldState)
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