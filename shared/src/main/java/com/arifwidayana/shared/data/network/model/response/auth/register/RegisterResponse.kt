package com.arifwidayana.shared.data.network.model.response.auth.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("phone_number")
    val phoneNumber: String?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("createdAt")
    val createdAt: String?
)