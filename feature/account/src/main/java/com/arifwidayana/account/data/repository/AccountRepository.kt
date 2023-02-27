package com.arifwidayana.account.data.repository

import com.arifwidayana.account.data.datasource.AccountDatasource
import com.arifwidayana.core.wrapper.DataResource
import com.arifwidayana.shared.data.network.model.response.account.UserResponse
import com.arifwidayana.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias UserDataResource = DataResource<UserResponse>

interface AccountRepository {
    suspend fun getUser(): Flow<UserDataResource>
}

class AccountRepositoryImpl(
    private val accountDatasource: AccountDatasource
) : AccountRepository, Repository() {
    override suspend fun getUser(): Flow<UserDataResource> = flow {
        emit(safeNetworkCall { accountDatasource.getUser() })
    }
}