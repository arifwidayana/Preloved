package com.arifwidayana.shared.data.network.model.request.auth.register

data class RegisterRequest(
    val fullName: String?,
    val email: String?,
    val password: String?,
    val phoneNumber: String?,
    val address: String?,
    val city: String?
)
