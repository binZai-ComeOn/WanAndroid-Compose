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
import com.binyouwei.wanandroid_compose.ui.page.classification.WeChatPage
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

        Scaffold(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding(),
            bottomBar = {
                // 设置底部导航栏
                BottomNavigationBar(navCtrl = navCtrl, bnbsMain)
            },
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(id = R.string.wan_android)) },
                    backgroundColor = MaterialTheme.colors.primarySurface,
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch { scaffoldState.drawerState.open() }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = stringResource(id = R.string.menu)
                            )
                        }
                    }
                )
            },
            drawerContent = {
                Drawer(navCtrl) { coroutineScope.launch { scaffoldState.drawerState.close() }  }
            },
            content = {
                var homeIndex = remember { 0 }
                var categoryIndex = remember { 0 }
                it.calculateBottomPadding()
                NavHost(
                    navController = navCtrl,
                    startDestination = RouteName.HOME
                ) {
                    // 首页
                    composable(route = RouteName.HOME) {
                        HomePage(navCtrl, scaffoldState, homeIndex) { homeIndex = it }
                    }

                    // 广场
                    composable(route = RouteName.SQUARE) {
                        SquarePage(navCtrl, categoryIndex) { categoryIndex = it }
                    }

                    // 体系
                    composable(route = RouteName.SYSTEM) {
                        KnowledgeSystemPage(navCtrl) { categoryIndex = it }
                    }

                    // 公众号
                    composable(route = RouteName.WeChat) {
                        WeChatPage(navCtrl) { categoryIndex = it }
                    }

                    // 项目
                    composable(route = RouteName.PROJECTS) {
                        ProjectPage(navCtrl) { categoryIndex = it }
                    }

                    // 积分排行榜
                    composable(route = RouteName.RANKING_LIST) {
                        RankingListPage(navCtrl) { categoryIndex = it }
                    }

                    // 积分
                    composable(route = RouteName.INTEGRAL) {
                        IntegralPage(navCtrl) { categoryIndex = it }
                    }

                    // 收藏
                    composable(route = RouteName.COLLECT) {
                        CollectPage(navCtrl) { categoryIndex = it }
                    }

                    // 分享
                    composable(route = RouteName.SHARE) {
                        SharePage(navCtrl) { categoryIndex = it }
                    }

                    // 设置
                    composable(route = RouteName.SETTINGS) {
                        SetingPage(navCtrl) { categoryIndex = it }
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