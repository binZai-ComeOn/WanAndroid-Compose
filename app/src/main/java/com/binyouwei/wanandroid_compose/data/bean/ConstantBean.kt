package com.binyouwei.wanandroid_compose.data.bean

import com.binyouwei.wanandroid_compose.ui.page.home.DrawerAppScreen

data class MenuBean(
    val type: DrawerAppScreen,
    val menuName: String,
    val id: Int,
    var rightText: String = ""
)