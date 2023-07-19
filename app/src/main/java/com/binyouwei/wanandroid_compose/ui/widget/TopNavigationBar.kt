package com.binyouwei.wanandroid_compose.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.binyouwei.wanandroid_compose.R
import kotlinx.coroutines.launch

@Composable
fun TopSearchMenuBar(
    title: String,
    drawerState: DrawerState,
    onClickSearch: () -> Unit = {},
    content: (PaddingValues) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
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
        }) {
        content(it)
    }
}