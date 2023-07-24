package com.binyouwei.common.manager

import com.binyouwei.common.network.api.ApiInterface

/**
 * @desc   API管理器
 */
object ApiManager {
    val api by lazy { HttpManager.create(ApiInterface::class.java) }
}