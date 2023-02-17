package com.arifwidayana.register.data.datasource

import com.arifwidayana.register.data.service.RegisterService
import com.arifwidayana.shared.data.network.model.request.auth.register.RegisterRequest
import com.arifwidayana.shared.data.network.model.response.auth.register.RegisterResponse

interface RegisterDatasource {
    suspend fun registerUser(registerRequest: RegisterRequest): RegisterResponse
}

class RegisterDatasourceImpl(
    private val registerService: RegisterService
) : RegisterDatasource {
    override suspend fun registerUser(registerRequest: RegisterRequest): RegisterResponse {
        return registerService.registerUser(registerRequest)
    }
}