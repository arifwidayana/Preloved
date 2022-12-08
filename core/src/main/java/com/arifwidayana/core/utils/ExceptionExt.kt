package com.arifwidayana.core.utils

import android.content.Context
import com.arifwidayana.style.R
import java.lang.Exception

class ApiErrorException(
    override val message: String? = null,
    val httpCode: Int? = null
) : Exception()
class NoInternetConnectionException : Exception()
class UnexpectedErrorException : Exception()
class FieldErrorException(val errorFields: List<Pair<Int, Int>>) : Exception()

fun Context.getErrorMessage(exception: Exception): String {
    return when (exception) {
        is ApiErrorException -> exception.message.orEmpty()
        is NoInternetConnectionException -> getString(R.string.message_error_no_internet)
        else -> getString(R.string.message_error_unknown)
    }
}