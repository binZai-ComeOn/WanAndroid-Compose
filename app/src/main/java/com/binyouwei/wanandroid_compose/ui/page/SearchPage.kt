package com.binyouwei.wanandroid_compose.ui.page

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.binyouwei.wanandroid_compose.R
import com.binyouwei.wanandroid_compose.ui.widget.TopSearchBar
import kotlinx.coroutines.launch

class SearchPage : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val rememberScaffoldState = rememberScaffoldState()
            val coroutineScope = rememberCoroutineScope()
            val drawerState = rememberScaffoldState.drawerState
            val text = remember { mutableStateOf("") }
            Scaffold(topBar = {
                TopSearchBar(this){

                }
            }) {

            }
        }
    }
}