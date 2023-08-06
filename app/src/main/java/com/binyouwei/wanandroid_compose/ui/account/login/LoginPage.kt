package com.binyouwei.wanandroid_compose.ui.account.login

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.binyouwei.wanandroid_compose.ui.widget.snackBar

/**
 * @author 宾有为
 * Created on 2023/8/5 22:57
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/
@Composable
fun LoginPage(
    activity: AppCompatActivity,
    navCtrl: NavHostController,
    viewModel: AccountViewModel = hiltViewModel()
) {
    val account = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }
    val loginResult by remember {
        viewModel.login
    }
    val errorMessage by remember {
        viewModel.errorMsg
    }
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(scaffoldState = scaffoldState) {
        it.calculateBottomPadding()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 40.dp, end = 24.dp)
        ) {
            Text(text = stringResource(id = R.string.user_login), fontSize = 18.sp)
            Text(
                text = stringResource(id = R.string.login_tip),
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 6.dp)
            )
            val pleaseInputAccount = stringResource(id = R.string.please_input_account)
            val pleaseInputPassword = stringResource(id = R.string.please_input_password)
            InputText(
                text = account,
                placeholder = pleaseInputAccount,
                leadingIcon = painterResource(id = R.mipmap.ic_account_normal),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .background(colorResource(id = R.color.White))
            )
            InputText(
                text = password,
                placeholder = pleaseInputPassword,
                leadingIcon = painterResource(id = R.mipmap.ic_password_normal),
                keyboardType = KeyboardType.Password,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 8.dp)
                    .background(color = Color.White)
            )
            Button(
                onClick = {
                    if (account.value.isEmpty()) {
                        snackBar(
                            scaffoldState.snackbarHostState,
                            coroutineScope,
                            pleaseInputAccount
                        )
                        return@Button
                    } else if (password.value.isEmpty()) {
                        snackBar(
                            scaffoldState.snackbarHostState,
                            coroutineScope,
                            pleaseInputPassword
                        )
                        return@Button
                    }
                    viewModel.login(account.value, password.value)
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 15.dp)
                    .clip(RoundedCornerShape(2.dp)),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.colorAccent))
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    modifier = Modifier
                        .padding(10.dp),
                    color = colorResource(
                        id = R.color.White
                    ),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                TextButton(onClick = {
                    navCtrl.navigate(RouteName.REGISTER)
                }) {
                    Text(
                        text = stringResource(id = R.string.no_account),
                        fontSize = 16.sp,
                    )
                }
            }
        }
        if (loginResult) {
            snackBar(
                scaffoldState.snackbarHostState,
                coroutineScope,
                stringResource(id = R.string.login_success)
            )
            activity.finish()
        }
        if (errorMessage?.isNotEmpty()!!) {
            snackBar(scaffoldState.snackbarHostState, coroutineScope, errorMessage!!)
        }
    }
}