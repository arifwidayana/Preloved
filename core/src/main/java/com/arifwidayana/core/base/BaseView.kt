package com.arifwidayana.core.base

import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import java.lang.Exception

interface BaseView {
    fun navPopUp(idFragment: String?, inclusive: Boolean = true): NavOptions
    fun moveNav()
    fun moveNav(navUp: Int)
    fun moveNav(direction: NavDirections)
    fun showLoading(isVisible: Boolean)
    fun showContent(isVisible: Boolean)
    fun showContentEmpty(isVisible: Boolean)
    fun showMessageToast(isEnabled: Boolean, message: String? = null, exception: Exception? = null)
    fun showMessageSnackBar(isEnabled: Boolean, message: String? = null, exception: Exception? = null)
}