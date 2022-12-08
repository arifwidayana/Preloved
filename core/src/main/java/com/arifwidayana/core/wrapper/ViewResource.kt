package com.arifwidayana.core.wrapper

import java.lang.Exception

sealed class ViewResource<T>(
    val payload: T? = null,
    val message: String? = null,
    val exception: Exception? = null,
) {
    class Success<T>(data: T) : ViewResource<T>(data)
    class Error<T>(exception: Exception?, data: T? = null) : ViewResource<T>(data, exception = exception)
    class Empty<T>(data: T? = null, message: String? = null) : ViewResource<T>(data, message)
    class Loading<T>(data: T? = null) : ViewResource<T>(data)
}