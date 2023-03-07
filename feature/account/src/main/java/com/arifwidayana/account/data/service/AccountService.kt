package com.arifwidayana.account.data.service

import com.arifwidayana.core.BuildConfig
import com.arifwidayana.core.base.BaseDefaultResponse
import com.arifwidayana.shared.data.network.model.request.account.password.PasswordRequest
import com.arifwidayana.shared.data.network.model.request.account.profile.ProfileUserRequest
import com.arifwidayana.shared.data.network.model.response.account.UserResponse
import com.arifwidayana.shared.data.network.model.response.account.order.OrderAccountResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import java.io.File

interface AccountService {
    @GET(BuildConfig.END_POINT_USER)
    suspend fun getUser(): UserResponse

    @PUT(BuildConfig.END_POINT_USER)
    suspend fun uploadImageProfile(@Body image: File): UserResponse

    @PUT(BuildConfig.END_POINT_USER)
    suspend fun updateProfileUser(@Body profileUserRequest: ProfileUserRequest): UserResponse

    @PUT(BuildConfig.END_POINT_CHANGE_PASSWORD)
    suspend fun updatePassword(@Body passwordRequest: PasswordRequest): BaseDefaultResponse

    @GET(BuildConfig.END_POINT_BUYER_ORDER)
    suspend fun getOrder(): List<OrderAccountResponse>
}