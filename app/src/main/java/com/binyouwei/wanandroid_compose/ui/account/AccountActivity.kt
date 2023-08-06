package com.binyouwei.wanandroid_compose.ui.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.route.RouteName
import com.binyouwei.wanandroid_compose.ui.account.login.LoginPage
import com.binyouwei.wanandroid_compose.ui.account.register.RegisterPage
import com.binyouwei.wanandroid_compose.ui.widget.*
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentLayout()
        }
    }


    @Composable
    private fun setContentLayout() {
        val navCtrl = rememberNavController()
        val login = stringResource(id = R.string.login)
        val register = stringResource(id = R.string.register)
        val topBarTitle = remember {
            mutableStateOf(login)
        }
        Scaffold(topBar = {
            TopBar(this, title = topBarTitle.value)
        },
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding(),
            content = {
                it.calculateBottomPadding()
                NavHost(
                    navController = navCtrl,
                    startDestination = RouteName.LOGIN
                ) {
                    composable(route = RouteName.LOGIN) {
                        topBarTitle.value = login
                        LoginPage(this@AccountActivity,navCtrl)
                    }

                    composable(route = RouteName.REGISTER) {
                        topBarTitle.value = register
                        RegisterPage(navCtrl)
                    }
                }
            }
        )
    }
}