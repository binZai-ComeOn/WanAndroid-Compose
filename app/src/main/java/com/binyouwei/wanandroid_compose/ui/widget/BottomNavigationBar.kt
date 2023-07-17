package com.binyouwei.wanandroid_compose.ui.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.binyouwei.wanandroid_compose.ui.route.BottomNavRoute

val bnbsMain = listOf(
    BottomNavRoute.Home,
    BottomNavRoute.Square,
    BottomNavRoute.System,
    BottomNavRoute.WeChat,
    BottomNavRoute.Projects
)

@Composable
fun BottomNavigationBar(navCtrl: NavHostController, menus: List<BottomNavRoute>) {
    val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(backgroundColor = Color(0xffffffff)) {
        menus.forEach { screen ->
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