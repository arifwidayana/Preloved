package com.arifwidayana.shared.data.network.model.request.account.password

data class PasswordParamRequest(
    val currentPassword: String,
    val newPassword: String,
    val confirmPassword: String
)
