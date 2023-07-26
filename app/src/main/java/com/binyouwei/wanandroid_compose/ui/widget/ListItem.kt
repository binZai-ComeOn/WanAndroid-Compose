package com.binyouwei.wanandroid_compose.ui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.bean.MenuBean
import com.binyouwei.wanandroid_compose.ui.page.CollectPage
import com.binyouwei.wanandroid_compose.ui.page.IntegralPage
import com.binyouwei.wanandroid_compose.ui.page.SharePage
import com.binyouwei.wanandroid_compose.ui.page.seting.SetingPage

var menuList = listOf(
    MenuBean(
        R.string.integral,
        R.drawable.ic_score_white_24dp, IntegralPage::class.java, "80"
    ),
    MenuBean(
        R.string.collect,
        R.drawable.ic_like_not,
        CollectPage::class.java
    ),
    MenuBean(
        R.string.share,
        R.drawable.ic_share_white_24dp, SharePage::class.java
    ),
    MenuBean(R.string.theme, R.drawable.ic_night_24dp, null),
    MenuBean(
        R.string.seting,
        R.drawable.ic_setting_24dp, SetingPage::class.java
    ),
    MenuBean(
        R.string.logout,
        R.drawable.ic_logout_white_24dp,
        null
    )
)

@Composable
fun MenuListItem(data: MenuBean) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(40.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(18.dp)
                    .weight(1f),
                painter = painterResource(data.iconId),
                contentDescription = stringResource(id = data.menuName)
            )
            Text(
                text = stringResource(id = data.menuName),
                modifier = Modifier
                    .weight(5f)
            )
            Text(
                text = data.rightText,
                modifier = Modifier
                    .weight(1f),
                color = colorResource(R.color.Grey600)
            )
        }
    }
}

@Preview
@Composable
fun ArticleItem() {
    Column() {
        ConstraintLayout(
            modifier = Modifier
                .background(color = colorResource(id = R.color.White))
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            val (tv_author, tv_topping, tv_new, tv_tag, tv_date, tv_content, tv_source) = createRefs()
            Text(
                text = "置顶", modifier = Modifier
                    .padding(end = 10.dp)
                    .border(
                        border = BorderStroke(0.5.dp, colorResource(id = R.color.Red)),
                        shape = RoundedCornerShape(2.dp)
                    )
                    .padding(4.dp, 2.dp)
                    .constrainAs(tv_topping) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                fontSize = 10.sp,
                color = colorResource(id = R.color.Red)
            )
            Text(
                text = "新", modifier = Modifier
                    .padding(end = 10.dp)
                    .border(
                        border = BorderStroke(0.5.dp, colorResource(id = R.color.Red)),
                        shape = RoundedCornerShape(2.dp)
                    )
                    .padding(4.dp, 2.dp)
                    .constrainAs(tv_new) {
                        top.linkTo(tv_topping.top)
                        start.linkTo(tv_topping.end)
                        bottom.linkTo(tv_topping.bottom)
                    },
                fontSize = 10.sp,
                color = colorResource(id = R.color.Red)
            )
            Text(
                text = "标签", modifier = Modifier
                    .padding(end = 10.dp)
                    .border(
                        border = BorderStroke(0.5.dp, colorResource(id = R.color.colorAccent)),
                        shape = RoundedCornerShape(2.dp)
                    )
                    .padding(4.dp, 2.dp)
                    .constrainAs(tv_tag) {
                        top.linkTo(tv_new.top)
                        start.linkTo(tv_new.end)
                        bottom.linkTo(tv_new.bottom)
                    },
                fontSize = 10.sp,
                color = colorResource(id = R.color.colorAccent)
            )
            Text(
                text = "作者", modifier = Modifier.constrainAs(tv_author) {
                    top.linkTo(tv_tag.top)
                    start.linkTo(tv_tag.end)
                    bottom.linkTo(tv_tag.bottom)
                },
                fontSize = 12.sp,
                color = colorResource(id = R.color.item_author)
            )
            Text(
                text = "日期", modifier = Modifier
                    .constrainAs(tv_date) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                fontSize = 12.sp,
                color = colorResource(id = R.color.item_date)
            )
            Text(
                text = "内容",
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .constrainAs(tv_content) {
                        top.linkTo(tv_author.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                        color = colorResource(id = R.color.item_title),)
            Text(
                text = "问答/官方",
                modifier = Modifier
                    .padding(top = 8.dp)
                    .constrainAs(tv_source) {
                        top.linkTo(tv_content.bottom)
                    },
                color = colorResource(id = R.color.item_chapter),
                fontSize = 10.sp,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(color = colorResource(id = R.color.list_divider))
        )
    }
}