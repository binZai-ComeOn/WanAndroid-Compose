package com.binyouwei.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.hjq.toast.Toaster

/**
 * @desc Fragment基类
 */
abstract class BaseFragment : Fragment() {
    protected var TAG: String? = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return getContentView(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view, savedInstanceState)
        initData()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    /**
     * 设置布局View
     */
    open fun getContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(getLayoutResId(), null)
    }

    /**
     * 初始化视图
     * @return Int 布局id
     * 仅用于不继承BaseDataBindFragment类的传递布局文件
     */
    abstract fun getLayoutResId(): Int

    /**
     * 初始化布局
     * @param savedInstanceState Bundle?
     */
    abstract fun initView(view: View, savedInstanceState: Bundle?)

    /**
     * 初始化数据
     */
    open fun initData() {}

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
        showLoading(getString(res))
    }

    /**
     * 关闭提示框
     */
    fun dismissLoading() {
        // dismissLoadingDialog()
    }

    /**
     * Toaster
     * @param msg Toaster内容
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

    override fun onDestroy() {
        super.onDestroy()
    }
}