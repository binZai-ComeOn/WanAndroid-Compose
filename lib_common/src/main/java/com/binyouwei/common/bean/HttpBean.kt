package com.binyouwei.common.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HotKeyBean(
    var id: Int,
    var link: String = "",
    var name: String,
    var order: Int,
    var visible: Int
) : Parcelable