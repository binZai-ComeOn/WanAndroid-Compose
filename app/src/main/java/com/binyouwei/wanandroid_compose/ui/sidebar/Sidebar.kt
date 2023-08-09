package com.binyouwei.wanandroid_compose.ui.sidebar

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.blankj.utilcode.util.LogUtils

/**
 * @author 宾有为
 * Created on 2023/7/26 22:27
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/
@Composable
fun Sidebar(
    activity: ComponentActivity,
    closeDrawer: () -> Unit,
) {
    val logOut = remember {
        mutableStateOf(false)
    }
    DrawerHeadComponent(activity)
    Column(modifier = Modifier.fillMaxSize()) {
        // 遍历生成菜单
        menuList.forEach continuing@{
            if (it.menuName == R.string.logout){
                if (!isLogin.value){
                    return@continuing
                }
            }
            Column(Modifier.clickable(onClick = {
                if (it.menuName == R.string.logout) {
                    // 点击退出登录
                    logOut.value = true
                } else {
                    activity.startActivity(Intent(activity, it.route))
                    closeDrawer()
                }
            }), content = {
                MenuListItem(it)
            })
        }
        if (logOut.value){
            MyAlertDialog( title = R.string.logout, text = R.string.is_logout, confirm = {
                logOut.value = false
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
        var userName = ""
        userName = if (isLogin.value) {
            DataStoreUtils.readStringData(AppConstant.UserNickname, "")
        } else {
            stringResource(id = R.string.go_login)
        }
        Text(
            text = userName,
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
                modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
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