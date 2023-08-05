package com.binyouwei.wanandroid_compose.ui.account.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.route.RouteName
import com.binyouwei.wanandroid_compose.ui.account.AccountViewModel
import com.binyouwei.wanandroid_compose.ui.widget.InputText

/**
 * @author 宾有为
 * Created on 2023/8/5 22:57
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/

@Composable
fun RegisterPage(
    navCtrl: NavHostController,
    viewModel: AccountViewModel = hiltViewModel()
) {
    val account = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    val confirmPassword = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 40.dp, end = 24.dp)
    ) {
        Text(text = stringResource(id = R.string.user_register), fontSize = 18.sp)
        Text(
            text = stringResource(id = R.string.register_tip),
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 6.dp)
        )
        InputText(
            text = account,
            placeholder = stringResource(id = R.string.please_input_account),
            leadingIcon = painterResource(id = R.mipmap.ic_account_normal),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .background(colorResource(id = R.color.White))
        )
        InputText(
            text = password,
            placeholder = stringResource(id = R.string.please_input_password),
            leadingIcon = painterResource(id = R.mipmap.ic_password_normal),
            keyboardType = KeyboardType.Password,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 8.dp)
                .background(color = Color.White)
        )
        InputText(
            text = confirmPassword,
            placeholder = stringResource(id = R.string.enter_password_again),
            leadingIcon = painterResource(id = R.mipmap.ic_password_normal),
            keyboardType = KeyboardType.Password,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 8.dp)
                .background(color = Color.White)
        )
        Text(
            text = stringResource(id = R.string.register),
            modifier = Modifier
                .padding(0.dp, 24.dp)
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.colorAccent),
                    RoundedCornerShape(2.dp)
                )
                .padding(10.dp)
                .clickable {

                },
            color = colorResource(
                id = R.color.White
            ),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.have_account),
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navCtrl.navigate(RouteName.LOGIN)
                },
            textAlign = TextAlign.End,
            style = TextStyle()
        )
    }
}