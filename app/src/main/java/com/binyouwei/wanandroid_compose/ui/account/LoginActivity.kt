package com.binyouwei.wanandroid_compose.ui.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.ui.widget.InputText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentLayout()
        }
    }

    @Composable
    private fun setContentLayout() {
        login()
    }

    @Composable
    fun login() {

        val account = remember {
            mutableStateOf("")
        }

        val password = remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.go_register),
                    color = colorResource(id = R.color.Light_Blue),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(8.dp, 0.dp)
                )
                Icon(
                    painter = painterResource(id = R.mipmap.ic_next),
                    contentDescription = stringResource(
                        id = R.string.next
                    ), modifier = Modifier.size(15.dp),
                    tint = colorResource(id = R.color.Light_Blue)
                )
            }
            InputText(
                text = account,
                placeholder = stringResource(id = R.string.please_input_account),
                leadingIcon = painterResource(id = R.mipmap.ic_account_normal),
                modifier = Modifier.padding(top = 100.dp)
            )
            InputText(
                text = password,
                placeholder = stringResource(id = R.string.please_input_password),
                leadingIcon = painterResource(id = R.mipmap.ic_password_normal),
                modifier = Modifier.padding(top = 10.dp)
            )
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(top = 30.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                )
            }
        }
    }

    @Composable
    fun register() {

    }
}