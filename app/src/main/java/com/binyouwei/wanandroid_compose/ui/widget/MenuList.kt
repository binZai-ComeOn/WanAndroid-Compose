package com.binyouwei.wanandroid_compose.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.data.bean.MenuBean
import com.binyouwei.wanandroid_compose.ui.route.RouteName


var menuList = listOf(
    MenuBean(
        R.string.integral,
        R.drawable.ic_score_white_24dp, RouteName.INTEGRAL, "80"
    ),
    MenuBean(
        R.string.collect,
        R.drawable.ic_like_not,
        RouteName.COLLECT
    ),
    MenuBean(
        R.string.share,
        R.drawable.ic_share_white_24dp, RouteName.SHARE
    ),
    MenuBean(R.string.theme, R.drawable.ic_night_24dp),
    MenuBean(
        R.string.settings,
        R.drawable.ic_setting_24dp, RouteName.SETTINGS
    ),
    MenuBean(
        R.string.logout,
        R.drawable.ic_logout_white_24dp
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