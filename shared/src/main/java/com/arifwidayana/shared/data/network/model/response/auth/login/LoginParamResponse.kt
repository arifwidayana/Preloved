package com.arifwidayana.shared.data.network.model.response.auth.login

data class LoginParamResponse(
    val id: Int,
    val name: String,
    val email: String,
    val accessToken: String
)