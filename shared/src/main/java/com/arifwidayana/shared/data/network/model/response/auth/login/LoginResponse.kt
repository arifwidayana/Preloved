package com.arifwidayana.shared.data.network.model.response.auth.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("access_token")
    val accessToken: String?
)