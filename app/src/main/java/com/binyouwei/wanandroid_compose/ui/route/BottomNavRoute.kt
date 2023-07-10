package com.binyouwei.wanandroid_compose.ui.route

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.material.icons.twotone.LocationOn
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material.icons.twotone.Place
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.binyouwei.wanandroid_compose.R

sealed class BottomNavRoute(
    var routeName: String,
    @StringRes var stringId: Int,
    var icon: Int
) {
    object Home: BottomNavRoute(RouteName.HOME, R.string.home, R.drawable.ic_home_black_24dp)
    object Square: BottomNavRoute(RouteName.SQUARE, R.string.square, R.drawable.ic_square_black_24dp)
    object WeChat: BottomNavRoute(RouteName.WeChat, R.string.wechat, R.drawable.ic_wechat_black_24dp)
    object System: BottomNavRoute(RouteName.SYSTEM, R.string.system, R.drawable.ic_apps_black_24dp)
    object Projects: BottomNavRoute(RouteName.PROJECTS, R.string.projects, R.drawable.ic_project_black_24dp)
}