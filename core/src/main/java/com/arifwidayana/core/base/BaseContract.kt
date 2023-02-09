package com.arifwidayana.core.base

import android.os.Bundle
import androidx.navigation.NavDirections
import java.lang.Exception

interface BaseContract {
    fun moveNavigateUp()
    fun moveNav(navUp: Int, args: Bundle? = null)
    fun moveNav(direction: NavDirections)
    fun moveNav(deepLink: String?)
    fun moveNav(deepLink: String, idFragmentPopUp: String?, inclusive: Boolean = true)
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