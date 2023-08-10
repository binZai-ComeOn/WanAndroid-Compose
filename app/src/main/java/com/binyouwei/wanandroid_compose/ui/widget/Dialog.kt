package com.binyouwei.wanandroid_compose.ui.widget

import androidx.annotation.StringRes
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
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
    @StringRes title: Int,
    @StringRes text: Int,
    onPopupDismissed: () -> Unit,
    confirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onPopupDismissed,
        title = { Text(text = stringResource(id = title)) },
        text = { Text(text = stringResource(id = text)) },
        confirmButton = {
            TextButton(
                onClick = {
                    confirm()
                    onPopupDismissed
                }
            ) {
                Text("确认")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onPopupDismissed
            ) {
                Text("取消")
            }
        },
    )
}