package com.binyouwei.wanandroid_compose.ui.route

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.binyouwei.wanandroid_compose.R

sealed class BottomNavRoute(
    var routeName: String,
    @StringRes var stringId: Int,
    var icon: ImageVector
) {
    object Home: BottomNavRoute(RouteName.HOME, R.string.home, Icons.Default.Home)
    object KnowledgeSystem: BottomNavRoute(RouteName.KNOWLEDGE_SYSTEM, R.string.knowledge_system, Icons.Default.Home)
    object Classification: BottomNavRoute(RouteName.CLASSIFICATION, R.string.classification, Icons.Default.Favorite)
    object My: BottomNavRoute(RouteName.MY, R.string.my, Icons.Default.Person)
}