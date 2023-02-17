package com.arifwidayana.register.data.repository

import com.arifwidayana.core.wrapper.DataResource
import com.arifwidayana.register.data.datasource.RegisterDatasource
import com.arifwidayana.shared.data.network.model.request.auth.register.RegisterRequest
import com.arifwidayana.shared.data.network.model.response.auth.register.RegisterResponse
import com.arifwidayana.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias RegisterDataResource = DataResource<RegisterResponse>

interface RegisterRepository {
    suspend fun registerUser(registerRequest: RegisterRequest): Flow<RegisterDataResource>
}

class RegisterRepositoryImpl(
    private val registerDatasource: RegisterDatasource
) : RegisterRepository, Repository() {
    override suspend fun registerUser(registerRequest: RegisterRequest): Flow<RegisterDataResource> = flow {
        emit(safeNetworkCall { registerDatasource.registerUser(registerRequest) })
    }
}