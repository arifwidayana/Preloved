package com.arifwidayana.account.data.datasource

import com.arifwidayana.account.data.service.AccountService
import com.arifwidayana.shared.data.network.model.response.account.UserResponse

interface AccountDatasource {
    suspend fun getUser(): UserResponse
}

class AccountDatasourceImpl(
    private val accountService: AccountService
) : AccountDatasource {
    override suspend fun getUser(): UserResponse {
        return accountService.getUser()
    }
}