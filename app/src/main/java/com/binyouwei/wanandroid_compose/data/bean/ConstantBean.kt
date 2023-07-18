package com.binyouwei.wanandroid_compose.data.bean

data class MenuBean(
    val menuName: Int,
    val iconId: Int,
    var route: Class<*>?,
    var rightText: String = ""
)