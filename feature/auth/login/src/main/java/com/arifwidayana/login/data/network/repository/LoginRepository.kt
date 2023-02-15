package com.arifwidayana.login.data.network.repository

import com.arifwidayana.core.wrapper.DataResource
import com.arifwidayana.login.data.network.datasource.LoginDatasource
import com.arifwidayana.shared.data.network.model.request.auth.login.LoginRequest
import com.arifwidayana.shared.data.network.model.response.auth.login.LoginResponse
import com.arifwidayana.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias LoginDataResource = DataResource<LoginResponse>

interface LoginRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Flow<LoginDataResource>
}

class LoginRepositoryImpl(
    private val loginDatasource: LoginDatasource
) : LoginRepository, Repository() {
    override suspend fun loginUser(loginRequest: LoginRequest): Flow<LoginDataResource> = flow {
        emit(safeNetworkCall { loginDatasource.loginUser(loginRequest) })
    }
}