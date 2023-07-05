package com.binyouwei.wanandroid_compose.ui.page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.binyouwei.wanandroid_compose.ui.page.home.HomePage
import com.binyouwei.wanandroid_compose.ui.route.RouteName
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

class MainPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
                BottomNav()
        }
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
            when (currentDestination?.route) {
//                RouteName.HOME -> BottomNavBarView(navCtrl = navCtrl)
//                RouteName.KNOWLEDGE_SYSTEM -> BottomNavBarView(navCtrl = navCtrl)
//                RouteName.CLASSIFICATION -> BottomNavBarView(navCtrl = navCtrl)
//                RouteName.MY -> BottomNavBarView(navCtrl = navCtrl)
            }
        },content = {
            var homeIndex = remember { 0 }
            var categoryIndex = remember { 0 }
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
                    //KnowledgeSystemPage(navCtrl, categoryIndex) { categoryIndex = it }
                }

                // 分类
                composable(route = RouteName.CLASSIFICATION) {
                    // Classification(navCtrl, scaffoldState)
                }

                //我的
                composable(route = RouteName.MY) {
                    // MyPage(navCtrl)
                }
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = scaffoldState.snackbarHostState
            ) { data ->
                println("actionLabel = ${data.actionLabel}")
            }
        }
    )
}