package com.binyouwei.wanandroid_compose.ui.account.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
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
import kotlinx.coroutines.CoroutineScope

/**
 * @author 宾有为
 * Created on 2023/8/5 22:57
 * Social homepage: https://blog.csdn.net/baidu_41616022
 * @desc
 **/
private lateinit var scaffoldState: ScaffoldState
private lateinit var coroutineScope: CoroutineScope
private lateinit var pleaseInputAccount: String
private lateinit var pleaseInputPassword: String
private lateinit var enterPasswordAgain: String
private lateinit var enterPasswordTry: String

@Composable
fun RegisterPage(
    navCtrl: NavHostController,
    viewModel: AccountViewModel = hiltViewModel()
) {
    scaffoldState = rememberScaffoldState()
    coroutineScope = rememberCoroutineScope()
    val account = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    val confirmPassword = remember {
        mutableStateOf("")
    }
    val errorMessage by remember {
        viewModel.errorMsg
    }
    var registerResult by remember {
        viewModel.result
    }
    pleaseInputAccount = stringResource(id = R.string.please_input_account)
    pleaseInputPassword = stringResource(id = R.string.please_input_password)
    enterPasswordAgain = stringResource(id = R.string.enter_password_again)
    enterPasswordTry = stringResource(id = R.string.enter_password_try)
    Scaffold(scaffoldState = scaffoldState) {
        it.calculateBottomPadding()
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
            InputText(
                text = confirmPassword,
                placeholder = enterPasswordAgain,
                leadingIcon = painterResource(id = R.mipmap.ic_password_normal),
                keyboardType = KeyboardType.Password,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 8.dp)
                    .background(color = Color.White)
            )
            Button(
                onClick = {
                    register(viewModel, account.value, password.value, confirmPassword.value)
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 15.dp)
                    .clip(RoundedCornerShape(2.dp)),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.colorAccent))
            ) {
                Text(
                    text = stringResource(id = R.string.register),
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
                    navCtrl.navigate(RouteName.LOGIN)
                }) {
                    Text(
                        text = stringResource(id = R.string.have_account),
                        fontSize = 16.sp,
                    )
                }
            }
        }

        if (registerResult) {
            registerResult = false
            snackBar(
                scaffoldState.snackbarHostState,
                coroutineScope,
                stringResource(id = R.string.register_success)
            )
            navCtrl.navigate(RouteName.LOGIN)
        }
        if (errorMessage?.isNotEmpty()!!) {
            snackBar(scaffoldState.snackbarHostState, coroutineScope, errorMessage!!)
        }
    }
}

private fun register(
    viewModel: AccountViewModel,
    account: String,
    password: String,
    confirmPassword: String
) {
    if (account.isEmpty()) {
        snackBar(
            scaffoldState.snackbarHostState,
            coroutineScope,
            pleaseInputAccount
        )
        return
    } else if (password.isEmpty()) {
        snackBar(
            scaffoldState.snackbarHostState,
            coroutineScope,
            pleaseInputPassword
        )
        return
    } else if (confirmPassword.isEmpty()) {
        snackBar(
            scaffoldState.snackbarHostState,
            coroutineScope,
            enterPasswordAgain
        )
        return
    } else if (confirmPassword != password) {
        snackBar(
            scaffoldState.snackbarHostState,
            coroutineScope,
            enterPasswordTry
        )
        return
    }
    viewModel.register(
        account,
        password,
        confirmPassword
    )
}
