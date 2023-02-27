package com.arifwidayana.account.data.service

import com.arifwidayana.core.BuildConfig
import com.arifwidayana.shared.data.network.model.response.account.UserResponse
import retrofit2.http.GET

interface AccountService {
    @GET(BuildConfig.END_POINT_USER)
    suspend fun getUser(): UserResponse
}