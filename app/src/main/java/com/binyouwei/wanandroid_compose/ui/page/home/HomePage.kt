package com.binyouwei.wanandroid_compose.ui.page.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.ImagePainter.State.Empty.painter
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.bean.MenuBean
import com.binyouwei.wanandroid_compose.ui.route.RouteName
import com.binyouwei.wanandroid_compose.ui.widget.Banner
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch

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
        val currentScreen = remember { mutableStateOf(DrawerAppScreen.Home) }
        val coroutineScope = rememberCoroutineScope()
        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                DrawerHeadComponent()
                DrawerContentComponent { coroutineScope.launch { drawerState.close() } }
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
        )
    }
}

@Composable
fun DrawerHeadComponent() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(
                    id = R.color.colorPrimary
                )
            )
            .padding(16.dp, 36.dp, 16.dp, 10.dp)
    ) {
        val (pointsRanking, avatar, name, row) = createRefs()
        Icon(
            painter = painterResource(R.drawable.ic_rank_white_24dp),
            contentDescription = stringResource(R.string.points_ranking),
            modifier = Modifier
                .constrainAs(pointsRanking) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end, margin = 16.dp)
                }
        )
        Image(
            painter = painterResource(id = R.mipmap.ic_default_avatar),
            contentDescription = stringResource(
                id = R.string.avatar
            ),
            modifier = Modifier
                .size(80.dp)
                .clip(shape = RoundedCornerShape(50.dp))
                .constrainAs(avatar) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )
        Text(
            text = stringResource(id = R.string.go_login),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.White),
            modifier = Modifier.constrainAs(name) {
                top.linkTo(avatar.bottom, margin = 12.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        Row(modifier = Modifier.constrainAs(row) {
            top.linkTo(name.bottom, margin = 8.dp)
            start.linkTo(name.start)
            end.linkTo(name.end)
        }) {
            Text(
                text = stringResource(id = R.string.nav_grade),
                fontSize = 12.sp,
                color = colorResource(
                    id = R.color.Grey100
                )
            )
            Text(
                text = stringResource(id = R.string.nav_line_2),
                fontSize = 12.sp,
                color = colorResource(
                    id = R.color.Grey100
                )
            )
            Text(
                text = stringResource(id = R.string.nav_rank),
                fontSize = 12.sp,
                color = colorResource(
                    id = R.color.Grey100
                ),
                modifier = Modifier.padding(8.dp,0.dp,0.dp,0.dp)
            )
            Text(
                text = stringResource(id = R.string.nav_line_2),
                fontSize = 12.sp,
                color = colorResource(
                    id = R.color.Grey100
                )
            )
        }
    }
}

@Composable
fun DrawerContentComponent(
    closeDrawer: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // 遍历生成菜单
        for (index in DrawerAppScreen.values().indices) {
            if (index != 0) {
                val menu = getScreenBasedOnIndex(index)
                Column(Modifier.clickable(onClick = {
                    menu.menuName
                    closeDrawer()
                }), content = {
                    Surface(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.height(50.dp)
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(18.dp)
                                    .weight(1f),
                                painter = painterResource(menu.id),
                                contentDescription = menu.menuName
                            )
                            Text(
                                text = menu.menuName,
                                modifier = Modifier
                                    .weight(5f)
                            )
                            Text(
                                text = menu.rightText,
                                modifier = Modifier
                                    .weight(1f),
                                color = colorResource(R.color.Grey600)
                            )
                        }
                    }
                })
            }
        }
    }
}

@Composable
fun BodyContentComponent(
    navCtrl: NavHostController,
    currentScreen: DrawerAppScreen,
    openDrawer: () -> Unit,
) {
    when (currentScreen) {
        DrawerAppScreen.Home ->
            Test(navCtrl = navCtrl)

        DrawerAppScreen.Integral -> Screen1Component(
            openDrawer
        )

        DrawerAppScreen.Collect -> Screen1Component(
            openDrawer
        )

        DrawerAppScreen.Share -> Screen1Component(
            openDrawer
        )

        DrawerAppScreen.Theme -> Screen1Component(
            openDrawer
        )

        DrawerAppScreen.Settings -> Screen1Component(
            openDrawer
        )

        DrawerAppScreen.Logout -> Screen1Component(
            openDrawer
        )
    }
}

@Composable
fun Test(navCtrl: NavHostController) {
    Text(text = "城市")
    Column(modifier = Modifier.fillMaxSize()) {
        Banner(navCtrl = navCtrl, onPageSelected = {

        })
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

@Composable
fun getScreenBasedOnIndex(index: Int) = when (index) {
    1 -> MenuBean(
        DrawerAppScreen.Integral,
        stringResource(R.string.integral),
        R.drawable.ic_score_white_24dp, "80"
    )

    2 -> MenuBean(DrawerAppScreen.Collect, stringResource(R.string.collect), R.drawable.ic_like_not)
    3 -> MenuBean(
        DrawerAppScreen.Share,
        stringResource(R.string.share),
        R.drawable.ic_share_white_24dp
    )

    4 -> MenuBean(DrawerAppScreen.Theme, stringResource(R.string.theme), R.drawable.ic_night_24dp)
    5 -> MenuBean(
        DrawerAppScreen.Settings,
        stringResource(R.string.settings),
        R.drawable.ic_setting_24dp
    )

    else -> MenuBean(
        DrawerAppScreen.Logout,
        stringResource(R.string.logout),
        R.drawable.ic_logout_white_24dp
    )
}

enum class DrawerAppScreen {
    Home,      // 首页
    Integral,  // 积分
    Collect,   // 收藏
    Share,     // 分享
    Theme,     // 主题（夜间模式）
    Settings,  // 设置
    Logout     // 退出登录
}
