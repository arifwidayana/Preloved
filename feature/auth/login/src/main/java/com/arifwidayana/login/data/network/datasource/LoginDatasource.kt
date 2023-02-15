package com.arifwidayana.login.data.network.datasource

import com.arifwidayana.login.data.network.service.LoginService
import com.arifwidayana.shared.data.network.model.request.auth.login.LoginRequest
import com.arifwidayana.shared.data.network.model.response.auth.login.LoginResponse

interface LoginDatasource {
    suspend fun loginUser(loginRequest: LoginRequest): LoginResponse
}

class LoginDatasourceImpl(
    private val loginService: LoginService
) : LoginDatasource {
    override suspend fun loginUser(loginRequest: LoginRequest): LoginResponse {
        return loginService.loginUser(loginRequest)
    }
}