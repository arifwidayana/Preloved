package com.arifwidayana.shared.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.StrikethroughSpan
import android.util.Patterns

object StringUtils {
    fun isEmailValid(input: CharSequence?): Boolean? {
        return if (TextUtils.isEmpty(input)) {
            false
        } else {
            input?.let {
                Patterns.EMAIL_ADDRESS.matcher(it).matches()
            }
        }
    }

    fun strikeThrough(text: String): Spannable {
        val span = SpannableString(text)
        span.setSpan(
            StrikethroughSpan(),
            0,
            text.length,
            0
        )
        return span
    }

    fun firstCharUpperCase(words: String): String {
        return words.replaceFirstChar { it.uppercase() }
    }

    fun textUpperCase(text: String): String {
        return text.uppercase()
    }
}