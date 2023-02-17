package com.arifwidayana.shared.data.network.model.request.auth.register

data class RegisterParamRequest(
    val fullName: String,
    val email: String,
    val password: String,
    val confirmPassword: String,
    val phoneNumber: String,
    val address: String,
    val city: String
)
