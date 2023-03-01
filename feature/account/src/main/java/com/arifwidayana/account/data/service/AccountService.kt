package com.arifwidayana.account.data.service

import com.arifwidayana.core.BuildConfig
import com.arifwidayana.shared.data.network.model.request.account.profile.ProfileUserRequest
import com.arifwidayana.shared.data.network.model.response.account.UserResponse
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
}