package com.binyouwei.wanandroid_compose.ui.sidebar

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.common.utils.DataStoreUtils
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import com.binyouwei.wanandroid_compose.data.constant.isLogin
import com.binyouwei.wanandroid_compose.ui.account.AccountActivity
import com.binyouwei.wanandroid_compose.ui.sidebar.rank_list.RankingListActivity
import com.binyouwei.wanandroid_compose.ui.widget.MenuListItem
import com.binyouwei.wanandroid_compose.ui.widget.MyAlertDialog
import com.binyouwei.wanandroid_compose.ui.widget.menuList

/**
 * @author 宾有为
 * Created on 2023/7/26 22:27
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/

private var integral = mutableStateOf(0)
private var level = mutableStateOf(0)
private var rank = mutableStateOf(0)

@Composable
fun Sidebar(
    activity: ComponentActivity,
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
    val userInfo = remember {
        viewModel.userInfo
    }
    if (userInfo.value != null) {
        menuList[0].rightText = userInfo.value!!.coinInfo.coinCount.toString()
        rank.value = userInfo.value!!.coinInfo.rank.toInt()
        level.value = userInfo.value!!.coinInfo.level
    }
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
                } else {
                    activity.startActivity(Intent(activity, it.route))
                }
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
                ActivityMessenger.startActivity<AccountActivity>(activity)
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
                    level.value.toString()
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