package com.binyouwei.wanandroid_compose.ui.widget

import androidx.annotation.StringRes
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import com.binyouwei.wanandroid_compose.R

/**
 * @author 宾有为
 * Created on 2023/7/19 10:31
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/
@Composable
fun MyAlertDialog(
    isShowDialog: MutableState<Boolean>,
    @StringRes title: Int,
    @StringRes text: Int,
    confirm: () -> Unit
) {
    if (isShowDialog.value) {
        AlertDialog(
            onDismissRequest = { isShowDialog.value = false },
            title = { Text(text = stringResource(id = title)) },
            text = { Text(text = stringResource(id = text)) },
            confirmButton = {
                TextButton(
                    onClick = {
                        confirm()
                        isShowDialog.value = false
                    }
                ) {
                    Text("确认")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        isShowDialog.value = false
                    }
                ) {
                    Text("取消")
                }
            }
        )
    }
}