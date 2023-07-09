package com.binyouwei.wanandroid_compose.ui.route

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.material.icons.twotone.LocationOn
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material.icons.twotone.Place
import androidx.compose.ui.graphics.vector.ImageVector
import com.binyouwei.wanandroid_compose.R

sealed class BottomNavRoute(
    var routeName: String,
    @StringRes var stringId: Int,
    var icon: ImageVector
) {
    object Home: BottomNavRoute(RouteName.HOME, R.string.home, Icons.Default.Home)
    object KnowledgeSystem: BottomNavRoute(RouteName.KNOWLEDGE_SYSTEM, R.string.knowledge_system, Icons.TwoTone.Place)
    object Classification: BottomNavRoute(RouteName.CLASSIFICATION, R.string.classification, Icons.Default.Menu)
    object My: BottomNavRoute(RouteName.MY, R.string.my, Icons.Default.Person)
}