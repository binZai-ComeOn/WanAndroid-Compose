package com.binyouwei.wanandroid_compose.ui.widget

import android.graphics.drawable.Icon
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.binyouwei.wanandroid_compose.R

/**
 * @author 宾有为
 * Created on 2023/7/31 22:54
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/
@Composable
fun InputText(
    text: MutableState<String>,
    leadingIcon: Painter,
    placeholder: String = "",
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {}
) {
    TextField(
        value = text.value,
        onValueChange = { newValue ->
            text.value = newValue
            onValueChange(newValue)
        },
        placeholder = {
            Text(text = placeholder)
        },
        leadingIcon = {
            Icon(
                painter = leadingIcon,
                contentDescription = stringResource(
                    id = R.string.account
                )
            )
        }, trailingIcon = {
            if (text.value.isNotEmpty()){
                IconButton(onClick = { text.value = "" }) {
                    Icon(
                        painter = painterResource(id = R.mipmap.ic_delete),
                        contentDescription = stringResource(
                            id = R.string.delete
                        )
                    )
                }
            }
        }, modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}