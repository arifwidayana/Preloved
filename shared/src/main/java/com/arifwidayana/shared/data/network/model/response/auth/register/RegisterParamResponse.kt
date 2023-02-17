package com.arifwidayana.shared.data.network.model.response.auth.register

data class RegisterParamResponse(
    val id: Int,
    val fullName: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val address: String,
    val imageUrl: String,
    val city: String,
    val updatedAt: String,
    val createdAt: String
)
