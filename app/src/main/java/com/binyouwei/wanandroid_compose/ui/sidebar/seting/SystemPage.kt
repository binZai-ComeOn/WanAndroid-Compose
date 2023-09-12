package com.binyouwei.wanandroid_compose.ui.sidebar.seting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.binyouwei.common.bean.SettingBean
import com.binyouwei.common.bean.WebData
import com.binyouwei.common.manager.CookiesManager
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.common.utils.CacheDataUtil
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import com.binyouwei.wanandroid_compose.data.constant.isLogin
import com.binyouwei.wanandroid_compose.ui.WebActivity
import com.binyouwei.wanandroid_compose.ui.widget.MyAlertDialog
import com.binyouwei.wanandroid_compose.ui.widget.SettingItem
import com.binyouwei.wanandroid_compose.ui.widget.TopBar
import com.binyouwei.wanandroid_compose.ui.widget.snackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentLayout()
        }
    }

    @Composable
    private fun setContentLayout() {
        var showDialog by remember {
            mutableStateOf(false)
        }
        val onPopupDismissed = { showDialog = false }
        val settingList = mutableListOf(
            SettingBean(
                stringResource(id = R.string.clear_cache),
                CacheDataUtil.getTotalCacheSize(this)
            ),
            SettingBean(
                stringResource(id = R.string.sourse_code),
                "https://github.com/binZai-ComeOn/WanAndroid-Compose"
            )
        )
        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()
        Scaffold(scaffoldState = scaffoldState, topBar = {
            TopBar(this, stringResource(id = R.string.seting))
        }) {
            it.calculateBottomPadding()
            val clearCache = stringResource(id = R.string.clear_cache)
            val sourseCode = stringResource(id = R.string.sourse_code)
            Column {
                LazyColumn() {
                    itemsIndexed(settingList) { _, item ->
                        SettingItem(item = item) { title ->
                            if (title == clearCache) {
                                showDialog = true
                            } else if (title == sourseCode) {
                                ActivityMessenger.startActivity<WebActivity>(
                                    this@SettingActivity,
                                    AppConstant.ExtraKey to WebData(title, item.desc)
                                )
                            }
                        }
                    }
                }
            }
            if (showDialog) {
                val ccs = stringResource(id = R.string.clear_cache_successfully)
                MyAlertDialog(
                    title = R.string.clear_cache,
                    text = R.string.is_clear_cache,
                    onPopupDismissed = onPopupDismissed,
                    confirm = {
                        showDialog = false
                        CacheDataUtil.clearAllCache(this@SettingActivity)
                        settingList.forEachIndexed { index, settingBean ->
                            if (settingBean.title == clearCache) {
                                settingList[index].desc =
                                    CacheDataUtil.getTotalCacheSize(this@SettingActivity)
                                snackBar(scaffoldState.snackbarHostState, coroutineScope, ccs)
                            }
                        }
                    })
            }
        }
    }
}