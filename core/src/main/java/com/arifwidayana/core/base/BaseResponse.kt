package com.arifwidayana.core.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<D>(
    @SerializedName("metadata")
    val metadata: Metadata?,
    @SerializedName("data")
    val data: D?
) {
    data class Metadata(
        @SerializedName("msg")
        val msg: String?,
        @SerializedName("status")
        val status: Int?
    )
}