package com.binyouwei.common.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.binyouwei.common.base.BaseResponse
import com.binyouwei.common.bean.ListWrapper
import com.binyouwei.common.network.HttpResult
import com.blankj.utilcode.util.LogUtils

class BasePagingSource<T: Any> constructor(
    private val callDataFromRemoteServer: suspend (page: Int)-> HttpResult<BaseResponse<ListWrapper<T>>>
): PagingSource<Int, T>() {

    private var page: Int = -1

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        println("当前页 ${params.key}")
        page = params.key ?: 0
        return when (val response = callDataFromRemoteServer(page)) {
            is HttpResult.Success -> {
                val data = response.result.data
                val hasNotNext = (data!!.datas.size < params.loadSize) && (data.over)
                LoadResult.Page(
                    data = response.result.data.datas,
                    prevKey = if (page - 1 > 0) page - 1 else null,
                    nextKey = if (hasNotNext) null else page+1
                )
            }
            is HttpResult.Error -> {
                LogUtils.e("网络请求异常： ${response.exception.message}")
                LoadResult.Error(response.exception)
            }
        }
    }
}