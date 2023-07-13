package com.binyouwei.wanandroid_compose.ui.page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.binyouwei.wanandroid_compose.ui.page.classification.WeChatPage
import com.binyouwei.wanandroid_compose.ui.page.home.HomePage
import com.binyouwei.wanandroid_compose.ui.page.project.ProjectPage
import com.binyouwei.wanandroid_compose.ui.page.square.SquarePage
import com.binyouwei.wanandroid_compose.ui.page.system.SystemPage
import com.binyouwei.wanandroid_compose.ui.route.BottomNavRoute
import com.binyouwei.wanandroid_compose.ui.route.RouteName
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPage : ComponentActivity() {
    val bns = listOf(
        BottomNavRoute.Home,
        BottomNavRoute.Square,
        BottomNavRoute.System,
        BottomNavRoute.WeChat,
        BottomNavRoute.Projects
    )

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

        Scaffold(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding(),
            bottomBar = {
                // 设置底部导航栏
                BottomNavigation {
                    BottomNavBarView(navCtrl = navCtrl)
                }
            }, content = {
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
                        SystemPage(navCtrl) { categoryIndex = it }
                    }

                    // 公众号
                    composable(route = RouteName.WeChat) {
                        WeChatPage(navCtrl) { categoryIndex = it }
                    }

                    // 项目
                    composable(route = RouteName.PROJECTS) {
                        ProjectPage(navCtrl) { categoryIndex = it }
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

    @Composable
    fun BottomNavBarView(navCtrl: NavHostController) {
        val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        BottomNavigation(backgroundColor = Color(0xffffffff)) {
            bns.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            modifier = Modifier
                                .size(24.dp)
                                .padding(2.dp),
                            painter = painterResource(screen.icon),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            color = Color(0xFF888888),
                            text = stringResource(screen.stringId)
                        )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.routeName } == true,
                    onClick = {
                        if (currentDestination?.route != screen.routeName) {
                            // 点击指定导航栏切换到指定页面
                            navCtrl.navigate(screen.routeName) {
                                popUpTo(navCtrl.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    })
            }
        }
    }
}
