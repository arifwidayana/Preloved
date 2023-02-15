package com.arifwidayana.login.data.network.service

import com.arifwidayana.core.BuildConfig
import com.arifwidayana.shared.data.network.model.request.auth.login.LoginRequest
import com.arifwidayana.shared.data.network.model.response.auth.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST(BuildConfig.END_POINT_LOGIN)
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse
}