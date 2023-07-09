package com.binyouwei.wanandroid_compose.ui.page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.binyouwei.wanandroid_compose.ui.page.classification.ClassificationPage
import com.binyouwei.wanandroid_compose.ui.page.home.HomePage
import com.binyouwei.wanandroid_compose.ui.page.knowledge_system.KnowledgeSystemPage
import com.binyouwei.wanandroid_compose.ui.page.my.MyPage
import com.binyouwei.wanandroid_compose.ui.route.BottomNavRoute
import com.binyouwei.wanandroid_compose.ui.route.RouteName
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPage : ComponentActivity() {
    val bns = listOf(
        BottomNavRoute.Home,
        BottomNavRoute.KnowledgeSystem,
        BottomNavRoute.Classification,
        BottomNavRoute.My
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
        val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val scaffoldState = rememberScaffoldState()

        Scaffold(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding(),
            bottomBar = {
                // 设置底部导航栏
                BottomNavigation {
                    when (currentDestination?.route) {
                        RouteName.HOME -> BottomNavBarView(navCtrl = navCtrl)
                        RouteName.KNOWLEDGE_SYSTEM -> BottomNavBarView(navCtrl = navCtrl)
                        RouteName.CLASSIFICATION -> BottomNavBarView(navCtrl = navCtrl)
                        RouteName.MY -> BottomNavBarView(navCtrl = navCtrl)
                    }
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

                    // 知识体系
                    composable(route = RouteName.KNOWLEDGE_SYSTEM) {
                        KnowledgeSystemPage(navCtrl, categoryIndex) { categoryIndex = it }
                    }

                    // 分类
                    composable(route = RouteName.CLASSIFICATION) {
                        ClassificationPage(navCtrl) { categoryIndex = it }
                    }

                    // 我的
                    composable(route = RouteName.MY) {
                        MyPage(navCtrl) { categoryIndex = it }
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
        BottomNavigation {
            bns.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = stringResource(screen.stringId)) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.routeName } == true,
                    onClick = {
                        if (currentDestination?.route != screen.routeName) {
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
