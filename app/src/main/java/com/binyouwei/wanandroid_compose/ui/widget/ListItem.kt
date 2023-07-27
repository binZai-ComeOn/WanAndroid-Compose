package com.binyouwei.wanandroid_compose.ui.widget

import android.text.Html
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.binyouwei.common.bean.ArticleBean
import com.binyouwei.common.bean.RankingListBean
import com.binyouwei.common.bean.WebData
import com.binyouwei.common.utils.TimeUtil
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.bean.MenuBean
import com.binyouwei.wanandroid_compose.ui.page.CollectPage
import com.binyouwei.wanandroid_compose.ui.page.integra.IntegralPage
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

@Composable
fun ArticleItem(article: ArticleBean, onClick: (WebData) -> Unit) {
    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.White))
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .clickable {
                onClick(WebData(article.title, article.link))
            }
    ) {
        Row {
            if (article.top == "1") {
                Text(
                    text = stringResource(id = R.string.topping), modifier = Modifier
                        .padding(end = 10.dp)
                        .border(
                            border = BorderStroke(0.5.dp, colorResource(id = R.color.Red)),
                            shape = RoundedCornerShape(2.dp)
                        )
                        .padding(4.dp, 2.dp),
                    fontSize = 10.sp,
                    color = colorResource(id = R.color.Red)
                )
            }
            if (article.fresh) {
                Text(
                    text = stringResource(id = R.string.xin), modifier = Modifier
                        .padding(end = 10.dp)
                        .border(
                            border = BorderStroke(0.5.dp, colorResource(id = R.color.Red)),
                            shape = RoundedCornerShape(2.dp)
                        )
                        .padding(4.dp, 2.dp),
                    fontSize = 10.sp,
                    color = colorResource(id = R.color.Red)
                )
            }
            if (article.tags.size > 0) {
                Text(
                    text = article.tags[0].name, modifier = Modifier
                        .padding(end = 10.dp)
                        .border(
                            border = BorderStroke(0.5.dp, colorResource(id = R.color.colorAccent)),
                            shape = RoundedCornerShape(2.dp)
                        )
                        .padding(4.dp, 2.dp),
                    fontSize = 10.sp,
                    color = colorResource(id = R.color.colorAccent)
                )
            }
            Text(
                text = article.author.ifEmpty { article.shareUser },
                fontSize = 12.sp,
                color = colorResource(id = R.color.item_author)
            )
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = TimeUtil.timeOf(article.publishTime),
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.item_date),
                )
            }
        }
        Html.fromHtml(article.title)
        Text(
            text = article.title,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            color = colorResource(id = R.color.item_title),
        )
        var tagValue = ""
        if (article.tags.isNotEmpty()) {
            article.tags.forEachIndexed { index, tag ->
                tagValue += tag.name
                if (article.tags.size != (index + 1)) {
                    tagValue += " / "
                }
            }
        } else {
            tagValue = when {
                article.superChapterName.isNotEmpty() and article.chapterName.isNotEmpty() -> {
                    "${article.superChapterName} / ${article.chapterName}"
                }

                article.superChapterName.isNotEmpty() -> {
                    article.superChapterName
                }

                article.chapterName.isNotEmpty() -> {
                    article.chapterName
                }

                else -> ""
            }
        }

        Text(
            text = tagValue,
            modifier = Modifier
                .padding(top = 8.dp),
            color = colorResource(id = R.color.item_chapter),
            fontSize = 10.sp,
        )
    }
    Box(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(0.5.dp)
            .background(color = colorResource(id = R.color.list_divider))
    )
}

@Composable
fun IntegralRankingItem(item: RankingListBean) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val (tv_number, tv_name, tv_integral) = createRefs()
        Text(
            text = item.rank,
            color = colorResource(id = R.color.item_author),
            textAlign = TextAlign.Left,
            modifier = Modifier
                .constrainAs(tv_number) {
                    start.linkTo(parent.start, margin = 10.dp)
                    top.linkTo(parent.top)
                }, fontSize = 14.sp
        )
        Text(
            text = item.username,
            color = colorResource(id = R.color.item_title),
            modifier = Modifier
                .constrainAs(tv_name) {
                    start.linkTo(tv_number.end, margin = 20.dp)
                    top.linkTo(parent.top)
                }, fontSize = 14.sp
        )
        Text(
            text = item.coinCount.toString(),
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.colorAccent),
            modifier = Modifier.constrainAs(tv_integral) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }, fontSize = 14.sp
        )
    }
}