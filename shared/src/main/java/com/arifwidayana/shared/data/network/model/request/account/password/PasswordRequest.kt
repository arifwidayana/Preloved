package com.arifwidayana.shared.data.network.model.request.account.password

import com.google.gson.annotations.SerializedName

data class PasswordRequest(
    @SerializedName("current_password")
    val currentPassword: String?,
    @SerializedName("new_password")
    val newPassword: String?,
    @SerializedName("confirm_password")
    val confirmPassword: String?
)
