package com.binyouwei.wanandroid_compose.ui.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.binyouwei.common.utils.ActivityMessenger
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.ui.sidebar.share.ShareArticlePage
import com.binyouwei.wanandroid_compose.ui.widget.InputText
import com.binyouwei.wanandroid_compose.ui.widget.TopBar
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
        val account = remember {
            mutableStateOf("")
        }

        val password = remember {
            mutableStateOf("")
        }
        val remember = remember { MutableInteractionSource() }
        val remember2 = remember { MutableInteractionSource() }
        Scaffold(topBar = {
            TopBar(this, title = stringResource(id = R.string.login))
        }) {
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
                InputText(
                    text = account,
                    placeholder = stringResource(id = R.string.please_input_account),
                    leadingIcon = painterResource(id = R.mipmap.ic_account_normal),
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .background(colorResource(id = R.color.White)),
                    interactionSource = remember2
                )
                InputText(
                    text = password,
                    placeholder = stringResource(id = R.string.please_input_password),
                    leadingIcon = painterResource(id = R.mipmap.ic_password_normal),
                    keyboardType = KeyboardType.Password,
                    modifier = Modifier
                        .padding(0.dp, 8.dp)
                        .background(color = Color.White),
                    interactionSource = remember
                )
            }
        }
    }

    @Composable
    fun register() {

    }
}