package com.arifwidayana.shared.utils

import android.text.TextUtils
import android.util.Patterns

object StringUtils {
    fun isEmailValid(input : CharSequence?) : Boolean? {
        return if(TextUtils.isEmpty(input)){
            false
        } else {
            input?.let {
                Patterns.EMAIL_ADDRESS.matcher(it).matches()
            }
        }
    }
}