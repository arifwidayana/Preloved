package com.arifwidayana.shared.data.repository

import com.arifwidayana.core.wrapper.DataResource
import com.arifwidayana.shared.data.network.datasource.UserDatasource
import com.arifwidayana.shared.data.network.model.response.account.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias UserDataResource = DataResource<UserResponse>

interface UserRepository {
    suspend fun getUser(): Flow<UserDataResource>
}

class UserRepositoryImpl(
    private val userDatasource: UserDatasource
) : UserRepository, Repository() {
    override suspend fun getUser(): Flow<UserDataResource> = flow {
        emit(safeNetworkCall { userDatasource.getUser() })
    }
}