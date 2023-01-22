package com.arifwidayana.shared.data.network.model.response.home.banner

import com.google.gson.annotations.SerializedName

data class BannerResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)