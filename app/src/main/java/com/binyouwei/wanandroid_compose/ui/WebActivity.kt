package com.binyouwei.wanandroid_compose.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.binyouwei.common.bean.WebData
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import com.binyouwei.wanandroid_compose.ui.widget.TopBar
import androidx.compose.runtime.*
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author binjx
 * @date 2023/7/27 14:15
 * @purpose：
 **/
@AndroidEntryPoint
class WebActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { setContentLayout() }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Composable
    private fun setContentLayout() {
        val webData : WebData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(AppConstant.ExtraKey, WebData::class.java)!!
        } else {
            intent.getParcelableExtra(AppConstant.ExtraKey)!!
        }
        val progress = remember { mutableStateOf(0) }
        val weTitle = remember {
            mutableStateOf("加载中...")
        }
        Scaffold(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            topBar = {
                TopBar(activity = this, title = weTitle.value, progress = progress)
            }) {
            it.calculateTopPadding()
            AndroidView(modifier = Modifier.fillMaxWidth(), factory = { context ->
                val webView = WebView(context)
                webView.settings.javaScriptEnabled = true
                webView.settings.javaScriptCanOpenWindowsAutomatically = true
                webView.settings.domStorageEnabled = true
                webView.settings.loadsImagesAutomatically = true
                webView.settings.mediaPlaybackRequiresUserGesture = false
                val webViewClient = WebViewClient()
                webView.webViewClient = webViewClient
                webView.webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView?, newProgress: Int) {
                        super.onProgressChanged(view, newProgress)
                        progress.value = newProgress
                    }

                    override fun onReceivedTitle(view: WebView?, title: String?) {
                        super.onReceivedTitle(view, title)
                        weTitle.value = title!!
                    }
                }
                webView.loadUrl(webData.url)
                webView
            })
        }
    }
}