package com.binyouwei.wanandroid_compose.ui.widget

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.binyouwei.common.bean.ArticleBean
import com.binyouwei.common.bean.HotKeyBean
import com.binyouwei.common.bean.WebData
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.db.table.SearchHistoryTable
import com.google.accompanist.flowlayout.FlowRow

/**
 * @author binjx
 * @date 2023/9/12 15:16
 * @purpose：
 **/
@Composable
fun MyArticleFlowRow(
    list: MutableList<ArticleBean>,
    modifier: Modifier = Modifier,
    onClick: (WebData) -> Unit,
) {
    FlowRow(modifier = modifier) {
        list.forEach { acticle ->
            ItemFlow(acticle.title) { str ->
                onClick(WebData(str,acticle.link))
            }
        }
    }
}

@Composable
fun MyHotKeyFlowRow(
    list: MutableList<HotKeyBean>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    FlowRow(modifier = modifier) {
        list.forEach { acticle ->
            ItemFlow(acticle.name) { str ->
                onClick(str)
            }
        }
    }
}

@Composable
fun MyMySearchHistoryFlowRow(
    list: MutableList<SearchHistoryTable>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    FlowRow(modifier = modifier) {
        list.forEach { acticle ->
            ItemFlow(acticle.name) { str ->
                onClick(str)
            }
        }
    }
}

@Composable
fun ItemFlow(title: String, onClick: (String) -> Unit) {
    TextButton(
        onClick = { onClick(title) },
        contentPadding = PaddingValues(5.dp, 0.dp),
        shape = RoundedCornerShape(0.dp)
    ) {
        Text(
            text = title, modifier = Modifier
                .background(colorResource(id = R.color.theme_pink_color_primary))
                .padding(10.dp),
            color = colorResource(id = R.color.Black)
        )
    }
}