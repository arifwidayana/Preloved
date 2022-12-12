package com.arifwidayana.core.base

import java.lang.Exception

interface BaseView {
    fun moveNav()
    fun moveNav(navUp: Int)
    fun moveNav(deepLink: String?, idFragmentPopUp: String?, inclusive: Boolean = true)
    fun showLoading(isVisible: Boolean)
    fun showContent(isVisible: Boolean)
    fun showContentEmpty(isVisible: Boolean)
    fun showMessageToast(isEnabled: Boolean, message: String? = null, exception: Exception? = null)
    fun showMessageSnackBar(
        isEnabled: Boolean,
        message: String? = null,
        exception: Exception? = null
    )
}