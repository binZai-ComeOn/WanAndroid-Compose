package com.binyouwei.wanandroid_compose.ui.widget

import android.graphics.drawable.Icon
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onValueChange: (String) -> Unit = {}
) {
    var visualTransformation = VisualTransformation.None
    if (keyboardType == KeyboardType.Password) {
        visualTransformation = PasswordVisualTransformation()
    }
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
                ),
                modifier = Modifier.size(16.dp)
            )
        },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        trailingIcon = {
            if (text.value.isNotEmpty() && interactionSource.collectIsFocusedAsState().value) {
                IconButton(onClick = { text.value = "" }) {
                    Icon(
                        painter = painterResource(id = R.mipmap.ic_delete),
                        contentDescription = stringResource(
                            id = R.string.delete
                        ),
                        modifier = Modifier.size(13.dp)
                    )
                }
            }
        },
        singleLine = singleLine,
        modifier = modifier,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = keyboardType
        ),
        interactionSource = interactionSource
    )
}