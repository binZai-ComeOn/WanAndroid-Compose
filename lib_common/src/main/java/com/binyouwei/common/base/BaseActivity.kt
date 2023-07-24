package com.binyouwei.common.base

import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.binyouwei.common.network.constant.routerKey
import com.binyouwei.lib_common.R
import com.hjq.toast.Toaster
import java.io.Serializable


/**
 * Desc   Activity基类
 */
abstract class BaseActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentLayout()
        }
    }

    /**
     * 设置布局
     */
    @Composable
    abstract fun setContentLayout()
}