package com.arifwidayana.account.data.datasource

import com.arifwidayana.account.data.service.AccountService
import com.arifwidayana.core.base.BaseDefaultResponse
import com.arifwidayana.shared.data.network.model.request.account.password.PasswordRequest
import com.arifwidayana.shared.data.network.model.request.account.profile.ProfileUserRequest
import com.arifwidayana.shared.data.network.model.response.account.UserResponse
import com.arifwidayana.shared.data.network.model.response.account.order.OrderAccountResponse
import com.arifwidayana.shared.data.network.model.response.account.wishlist.WishlistAccountResponse
import java.io.File

interface AccountDatasource {
    suspend fun getUser(): UserResponse
    suspend fun uploadImageProfile(image: File): UserResponse
    suspend fun updateProfileUser(profileUserRequest: ProfileUserRequest): UserResponse
    suspend fun updatePassword(passwordRequest: PasswordRequest): BaseDefaultResponse
    suspend fun getOrder(): List<OrderAccountResponse>
    suspend fun getWishlist(): List<WishlistAccountResponse>
}

class AccountDatasourceImpl(
    private val accountService: AccountService
) : AccountDatasource {
    override suspend fun getUser(): UserResponse {
        return accountService.getUser()
    }

    override suspend fun uploadImageProfile(image: File): UserResponse {
        return accountService.uploadImageProfile(image)
    }

    override suspend fun updateProfileUser(profileUserRequest: ProfileUserRequest): UserResponse {
        return accountService.updateProfileUser(profileUserRequest)
    }

    override suspend fun updatePassword(passwordRequest: PasswordRequest): BaseDefaultResponse {
        return accountService.updatePassword(passwordRequest)
    }

    override suspend fun getOrder(): List<OrderAccountResponse> {
        return accountService.getOrder()
    }

    override suspend fun getWishlist(): List<WishlistAccountResponse> {
        return accountService.getWishlist()
    }
}