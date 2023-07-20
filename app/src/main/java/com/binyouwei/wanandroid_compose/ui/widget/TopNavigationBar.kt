package com.binyouwei.wanandroid_compose.ui.widget

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.binyouwei.wanandroid_compose.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(
    activity: ComponentActivity,
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = { Text(title) },
        backgroundColor = colorResource(id = R.color.colorPrimary),
        navigationIcon = {
            IconButton(onClick = {
                activity.finish()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back)
                )
            }
        },
        actions = actions
    )
}

@Composable
fun TopSearchMenuBar(
    title: String,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    onClickSearch: () -> Unit = {},
) {
    TopAppBar(
        title = { Text(title) },
        backgroundColor = colorResource(id = R.color.colorPrimary),
        navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch { if (drawerState.isClosed) drawerState.open() else drawerState.close() }
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(id = R.string.menu)
                )
            }
        },
        actions = {
            IconButton(onClick = { onClickSearch() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search_white_24dp),
                    contentDescription = stringResource(R.string.search)
                )
            }
        }
    )
}

@Composable
fun TopSearchBar(
    activity: ComponentActivity,
    placeholder: String = stringResource(id = R.string.search),
    onClickSearch: (String) -> Unit,
) {
    val text = remember { mutableStateOf("") }
    TopAppBar(
        title = {
            TextField(
                value = text.value,
                onValueChange = { text.value = it },
                placeholder = { Text(placeholder) },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth(),
                // 自定义回车为搜索操作
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                // 将搜索事件自定义
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onClickSearch(text.value)
                    }),
                // 设置文本末尾的可选图标
                trailingIcon = {
                    if (text.value.isNotEmpty()) {
                        IconButton(onClick = {
                            text.value = ""
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = stringResource(id = R.string.clear_up)
                            )
                        }
                    }
                },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
            )
        },
        backgroundColor = colorResource(id = R.color.colorPrimary),
        navigationIcon = {
            IconButton(onClick = {
                activity.finish()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back)
                )
            }
        },
        actions = {
            IconButton(onClick = { onClickSearch(text.value) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search_white_24dp),
                    contentDescription = stringResource(R.string.search)
                )
            }
        }
    )
}