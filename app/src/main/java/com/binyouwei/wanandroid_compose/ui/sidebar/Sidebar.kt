package com.binyouwei.wanandroid_compose.ui.sidebar

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.binyouwei.common.manager.CookiesManager
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.common.utils.DataStoreUtils
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import com.binyouwei.wanandroid_compose.data.constant.isLogin
import com.binyouwei.wanandroid_compose.ui.account.AccountActivity
import com.binyouwei.wanandroid_compose.ui.sidebar.collect.CollectPage
import com.binyouwei.wanandroid_compose.ui.sidebar.integral.IntegralPage
import com.binyouwei.wanandroid_compose.ui.sidebar.rank_list.RankingListActivity
import com.binyouwei.wanandroid_compose.ui.widget.MenuListItem
import com.binyouwei.wanandroid_compose.ui.widget.MyAlertDialog
import com.binyouwei.wanandroid_compose.ui.widget.menuList
import com.binyouwei.wanandroid_compose.ui.widget.snackBar
import com.blankj.utilcode.util.LogUtils

/**
 * @author 宾有为
 * Created on 2023/7/26 22:27
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/

private var integral = mutableStateOf("0")
private var level = mutableStateOf(0)
private var rank = mutableStateOf(0)

@Composable
fun Sidebar(
    activity: ComponentActivity,
    snackbarHostState: SnackbarHostState,
    closeDrawer: () -> Unit,
) {
    var showDialog by remember {
        mutableStateOf(false)
    }
    val onPopupDismissed = { showDialog = false }
    val viewModel: SidebarViewModel = hiltViewModel()
    if (isLogin.value) {
        viewModel.getUserInfo()
    }
    val userInfoResult by remember {
        viewModel.userInfoResult
    }
    val scope = rememberCoroutineScope()
    if (userInfoResult) {
        val value = viewModel.userInfo.value!!
        integral.value = value.coinInfo.coinCount.toString()
        rank.value = value.coinInfo.rank.toInt()
        level.value = value.coinInfo.level
    }
    menuList[0].rightText = if (isLogin.value) integral.value else "0"
    val stringResource = stringResource(id = R.string.please_log_in_and_try_again)
    DrawerHeadComponent(activity)
    Column(modifier = Modifier.fillMaxSize()) {
        // 遍历生成菜单
        menuList.forEach continuing@{
            if (it.menuName == R.string.logout) {
                if (!isLogin.value) {
                    return@continuing
                }
            }
            Column(Modifier.clickable(onClick = {
                if (it.menuName == R.string.logout) {
                    // 点击退出登录
                    showDialog = true
                    return@clickable
                } else if (!isLogin.value && (it.menuName == R.string.my_share || it.menuName == R.string.collect || it.menuName == R.string.integral)) {
                    snackBar(snackbarHostState, scope, stringResource)
                    closeDrawer()
                    return@clickable
                }
                val intent = Intent(activity, it.route)
                if (isLogin.value) {
                    val userInfo = viewModel.userInfo.value
                    if (it.route == CollectPage::class.java) {
                        intent.putIntegerArrayListExtra(
                            AppConstant.ExtraKey,
                            userInfo?.userInfo?.collectIds
                        )
                    } else if (it.route == IntegralPage::class.java) {
                        intent.putExtra(
                            AppConstant.ExtraKey,
                            userInfo?.userInfo?.coinCount.toString()
                        )
                    }
                }
                activity.startActivity(intent)
                closeDrawer()
            }), content = {
                MenuListItem(it)
            })
        }
        if (showDialog) {
            MyAlertDialog(
                title = R.string.logout,
                text = R.string.is_logout,
                onPopupDismissed = onPopupDismissed,
                confirm = {
                    showDialog = false
                    isLogin.value = false
                    CookiesManager.clearCookies()
                })
        }
    }
}


@Composable
fun DrawerHeadComponent(activity: ComponentActivity) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(
                    id = R.color.colorPrimary
                )
            )
            .padding(8.dp, 8.dp, 8.dp, 8.dp)
            .clickable {
                if (!isLogin.value) {
                    ActivityMessenger.startActivity<AccountActivity>(activity)
                }
            }
    ) {
        val (pointsRanking, avatar, name, row) = createRefs()
        IconButton(onClick = {
            ActivityMessenger.startActivity<RankingListActivity>(activity)
        }, modifier = Modifier
            .constrainAs(pointsRanking) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }) {
            Icon(
                painter = painterResource(R.drawable.ic_rank_white_24dp),
                contentDescription = stringResource(R.string.points_ranking),
            )
        }
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
            text = if (isLogin.value) {
                DataStoreUtils.readStringData(AppConstant.UserNickname, "")
            } else {
                stringResource(id = R.string.go_login)
            },
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
                text = if (isLogin.value) {
                    level.value.toString()
                } else {
                    stringResource(id = R.string.nav_line_2)
                },
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
                modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
            )
            Text(
                text = if (isLogin.value) {
                    rank.value.toString()
                } else {
                    stringResource(id = R.string.nav_line_2)
                },
                fontSize = 12.sp,
                color = colorResource(
                    id = R.color.Grey100
                )
            )
        }
    }
}