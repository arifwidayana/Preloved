package com.arifwidayana.register.data.service

import com.arifwidayana.core.BuildConfig
import com.arifwidayana.shared.data.network.model.request.auth.register.RegisterRequest
import com.arifwidayana.shared.data.network.model.response.auth.register.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST(BuildConfig.END_POINT_REGISTER)
    suspend fun registerUser(@Body registerRequest: RegisterRequest): RegisterResponse
}