package com.arifwidayana.shared.data.network.datasource

import com.arifwidayana.shared.data.network.model.response.account.UserResponse
import com.arifwidayana.shared.data.network.service.UserService

interface UserDatasource {
    suspend fun getUser(): UserResponse
}

class UserDatasourceImpl(
    private val userService: UserService
) : UserDatasource {
    override suspend fun getUser(): UserResponse {
        return userService.getUser()
    }
}