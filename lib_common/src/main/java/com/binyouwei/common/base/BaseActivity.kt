package com.binyouwei.common.base

import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import com.binyouwei.common.network.constant.routerKey
import com.binyouwei.lib_common.R
import com.hjq.toast.Toaster
import java.io.Serializable


/**
 * Desc   Activity基类
 */
abstract class BaseActivity : ComponentActivity() {
    protected var Tag: String? = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout()
        initView(savedInstanceState)
        initData()
    }

    /**
     * 设置布局
     */
    open fun setContentLayout() {
        setContentView(getLayoutResId())
    }

    /**
     * 初始化视图
     * @return Int 布局id
     * 仅用于不继承BaseDataBindActivity类的传递布局文件
     */
    abstract fun getLayoutResId(): Int

    /**
     * 初始化布局
     * @param savedInstanceState Bundle?
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 初始化数据
     */
    open fun initData() {

    }

    /**
     * 加载中……弹框
     */
    fun showLoading() {
        // showLoading(getString(R.string.default_loading))
    }

    /**
     * 加载提示框
     */
    fun showLoading(msg: String) {
        // showLoadingDialog(msg)
    }

    /**
     * 加载提示框
     */
    fun showLoading(@StringRes res: Int) {
        // showLoading(getString(res))
    }

    /**
     * 关闭提示框
     */
    fun dismissLoading() {
        // dismissLoadingDialog()
    }

    /**
     * Toaster
     * @param msg Toast内容
     */
    fun showToast(msg: String) {
        Toaster.show(msg)
    }

    /**
     * Toaster
     * @param resId 字符串id
     */
    fun showToast(@StringRes resId: Int) {
        Toaster.show(resId)
    }
}