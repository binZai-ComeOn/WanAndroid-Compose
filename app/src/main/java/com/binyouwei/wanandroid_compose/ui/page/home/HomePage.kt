package com.binyouwei.wanandroid_compose.ui.page.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.binyouwei.wanandroid_compose.R

@Composable
fun HomePage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    homeIndex: Int = 0,
    viewModel: HomeViewModel = hiltViewModel(),
    onPageSelected: (position: Int) -> Unit
) {

    val scopeState = rememberCoroutineScope()
    // navCtrl.navigate(RouteName.INTEGRAL)
    Column {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val coroutineScope = rememberCoroutineScope()
        /*ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                DrawerHeadComponent()
                DrawerContentComponent(navCtrl) { coroutineScope.launch { drawerState.close() } }
                // 链接：https://cloud.tencent.com/developer/article/1998526?areaSource=102001.8&traceId=J1JaKsmJGffTBPnYVMnAj
            },
            content = {
                BodyContentComponent(
                    navCtrl = navCtrl,
                    currentScreen = currentScreen.value,
                    openDrawer = {
                        coroutineScope.launch { drawerState.open() }
                    }
                )
            }
        )*/
    }
}


@Composable
fun Screen1Component(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(stringResource(id = R.string.wan_android)) },
            backgroundColor = MaterialTheme.colors.primarySurface,
            navigationIcon = {
                IconButton(onClick = openDrawer) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = stringResource(id = R.string.menu)
                    )
                }
            }
        )
        Surface(color = Color(0xFFffd7d7.toInt()), modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Text(text = "Screen 1")
                }
            )
        }
    }
}
