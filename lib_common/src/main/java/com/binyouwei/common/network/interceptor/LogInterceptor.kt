package com.binyouwei.common.network.interceptor

import android.os.Build
import androidx.annotation.RequiresApi
import com.blankj.utilcode.util.LogUtils
import okhttp3.*
import java.io.IOException
import java.net.URLDecoder
import javax.inject.Inject

/**
 * Created by Superman on 2021/1/29.
 */
class LogInterceptor @Inject constructor() : Interceptor {

    @RequiresApi(Build.VERSION_CODES.N)
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return kotlin.runCatching { chain.proceed(request) }
            .onSuccess {
                logRequest(request, chain.connection())
                if (it.isSuccessful) {
                    logResponse(it)
                } else {
                    LogUtils.e(it.message().toString())
                }
            }
            .onFailure {
                LogUtils.e(it.message.toString())
            }
            .getOrThrow()
    }

    private fun logResponse(response: Response) {
        val strb = StringBuffer()
        strb.appendLine("\r\n")
        strb.appendLine("<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-")

        var headerText = ""
        response.headers().toMultimap().forEach { header ->
            headerText += "请求 Header:{${header.key}=${header.value}}\n"
        }
        strb.appendLine(headerText)
        kotlin.runCatching {
            //peek类似于clone数据流，监视，窥探，不能直接用原来的body的string流数据作为日志，会消费掉io，所有这里是peek，监测
            val peekBody: ResponseBody = response.peekBody(1024 * 1024)
            strb.appendLine(peekBody.string())
        }.getOrNull()

        strb.appendLine(
            "请求结果<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<" +
                    "-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-"
        )
        LogUtils.e(strb.toString())
    }

    private fun logRequest(request: Request, connection: Connection?) {
        val strb = StringBuilder()
        strb.appendLine("\r\n")
        strb.appendLine(
            "开始请求->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->"
        )
        logHeaders(strb, request, connection)
        strb.appendLine("RequestBody:${request.body().toString()}")
        LogUtils.e(strb.toString())
    }

    private fun logHeaders(strb: StringBuilder, request: Request, connection: Connection?) {
        logBasic(strb, request, connection)
        var headerStr = ""
        request.headers().toMultimap().forEach { header ->
            headerStr += "请求 Header:{${header.key}=${header.value}}\n"
        }
        strb.appendLine(headerStr)
    }

    private fun logBasic(strb: StringBuilder, request: Request, connection: Connection?) {
        strb.appendLine(
            "请求 method：${request.method()} url:${decodeUrlStr(request.url().toString())} tag:" +
                    "${request.tag()} protocol:${connection?.protocol() ?: Protocol.HTTP_1_1}"
        )
    }

    /**
     * 对于url编码的string解码
     */
    private fun decodeUrlStr(url: String): String? {
        return kotlin.runCatching {
            URLDecoder.decode(url, "utf-8")
        }.onFailure { it.printStackTrace() }.getOrNull()
    }
}

