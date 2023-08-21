package com.binyouwei.wanandroid_compose.ui.widget

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.binyouwei.common.bean.*
import com.binyouwei.common.utils.TimeUtil
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.bean.MenuBean
import com.binyouwei.wanandroid_compose.ui.sidebar.collect.CollectPage
import com.binyouwei.wanandroid_compose.ui.sidebar.integral.IntegralPage
import com.binyouwei.wanandroid_compose.ui.sidebar.share.MySharePage
import com.binyouwei.wanandroid_compose.ui.sidebar.seting.SetingPage

var menuList = listOf(
    MenuBean(
        R.string.integral,
        R.drawable.ic_score_white_24dp, IntegralPage::class.java, "0"
    ),
    MenuBean(
        R.string.collect,
        R.drawable.ic_like_not,
        CollectPage::class.java
    ),
    MenuBean(
        R.string.my_share,
        R.drawable.ic_share_white_24dp, MySharePage::class.java
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
            if (!article.tags.isNullOrEmpty()) {
                Text(
                    text = article.tags!![0].name, modifier = Modifier
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
        Text(
            text = article.title,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            color = colorResource(id = R.color.item_title),
        )
        var tagValue = ""
        if (!article.tags.isNullOrEmpty()) {
            article.tags?.forEachIndexed { index, tag ->
                tagValue += tag.name
                if (article.tags!!.size != (index + 1)) {
                    tagValue += " / "
                }
            }
        } else {
            tagValue = when {
                !article.superChapterName.isNullOrEmpty() and !article.chapterName.isNullOrEmpty() -> {
                    "${article.superChapterName} / ${article.chapterName}"
                }

                !article.superChapterName.isNullOrEmpty() -> {
                    article.superChapterName!!
                }

                !article.chapterName.isNullOrEmpty() -> {
                    article.chapterName!!
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

@Composable
fun KnowledgeSystemItem(
    data: KnowledgeSystemBean,
    onClick: (KnowledgeSystemBean) -> Unit = {},
) {
    ConstraintLayout(
        modifier = Modifier
            .background(color = colorResource(id = R.color.viewBackground))
            .clickable {
                onClick(data)
            }
    ) {
        val (tv_text, tv_second, ic_arrow, divider) = createRefs()
        Text(
            text = data.name!!,
            color = colorResource(id = R.color.item_title),
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(tv_text) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    end.linkTo(ic_arrow.start, margin = 10.dp)
                    width = Dimension.fillToConstraints
                }, textAlign = TextAlign.Left
        )
        var text = ""
        data.children?.forEach { item ->
            text += "${item.name}    "
        }
        Text(
            text = text,
            color = colorResource(id = R.color.item_desc),
            fontSize = 10.sp,
            modifier = Modifier
                .constrainAs(tv_second) {
                    top.linkTo(tv_text.bottom, margin = 10.dp)
                    start.linkTo(tv_text.start)
                    end.linkTo(ic_arrow.start, margin = 10.dp)
                    width = Dimension.fillToConstraints
                }, textAlign = TextAlign.Left
        )
        Icon(painter = painterResource(id = R.drawable.ic_arrow_right_24dp),
            contentDescription = stringResource(
                id = R.string.arrow
            ),
            modifier = Modifier
                .size(24.dp)
                .constrainAs(ic_arrow) {
                    end.linkTo(parent.end, margin = 10.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                })
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(color = colorResource(id = R.color.list_divider))
                .constrainAs(divider) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(tv_second.bottom, margin = 10.dp)
                }
        )
    }
}

@Composable
fun ProjectItem(bean: ArticleBean, onClick: (WebData) -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(180.dp)
            .clickable { onClick(WebData(bean.title, bean.link)) }
    ) {
        val (tv_image, tv_title, tv_desc, tv_date, tv_author, divider) = createRefs()
        AsyncImage(
            model = bean.envelopePic,
            contentDescription = "",
            modifier = Modifier
                .constrainAs(tv_image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }
                .width(110.dp)
        )
        Text(
            text = bean.title,
            fontSize = 16.sp,
            color = colorResource(id = R.color.item_title),
            modifier = Modifier.constrainAs(tv_title) {
                start.linkTo(tv_image.end, margin = 8.dp)
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            })
        Text(
            text = bean.desc,
            fontSize = 10.sp,
            color = colorResource(id = R.color.item_desc),
            modifier = Modifier.constrainAs(tv_desc) {
                start.linkTo(tv_image.end, margin = 8.dp)
                top.linkTo(tv_title.bottom, margin = 10.dp)
                end.linkTo(parent.end)
                bottom.linkTo(tv_author.top, 10.dp)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }, overflow = TextOverflow.Ellipsis
        )
        Text(
            text = bean.author,
            fontSize = 12.sp,
            color = colorResource(id = R.color.item_author),
            modifier = Modifier.constrainAs(tv_author) {
                bottom.linkTo(parent.bottom)
                start.linkTo(tv_image.end, margin = 8.dp)
            })
        Text(
            text = TimeUtil.timeOf(bean.publishTime),
            fontSize = 12.sp,
            color = colorResource(id = R.color.item_date),
            modifier = Modifier.constrainAs(tv_date) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            })
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(color = colorResource(id = R.color.list_divider))
                .constrainAs(divider) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.bottom, margin = 10.dp)
                }
        )
    }
}

@Composable
fun UserScoreItem(item: ScoreBean) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val (tv_reason, tv_desc, tv_score) = createRefs()
        Text(
            text = item.reason, modifier = Modifier.constrainAs(tv_reason) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            fontSize = 16.sp, color = colorResource(id = R.color.item_title)
        )
        Text(text = item.desc, modifier = Modifier
            .constrainAs(tv_desc) {
                top.linkTo(tv_reason.bottom)
                start.linkTo(parent.start)
            }
            .padding(top = 4.dp), color = colorResource(id = R.color.item_date))
        Text(text = "+${item.coinCount}", modifier = Modifier.constrainAs(tv_score) {
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }, color = colorResource(id = R.color.colorAccent))
    }
}