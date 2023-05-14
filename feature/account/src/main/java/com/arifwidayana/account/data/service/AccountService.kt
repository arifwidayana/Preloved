package com.arifwidayana.account.data.service

import com.arifwidayana.core.BuildConfig
import com.arifwidayana.core.base.BaseDefaultResponse
import com.arifwidayana.shared.data.network.model.request.account.password.PasswordRequest
import com.arifwidayana.shared.data.network.model.request.account.profile.ProfileUserRequest
import com.arifwidayana.shared.data.network.model.response.account.UserResponse
import com.arifwidayana.shared.data.network.model.response.account.order.OrderAccountResponse
import com.arifwidayana.shared.data.network.model.response.account.wishlist.WishlistAccountResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface AccountService {
    @PUT(BuildConfig.END_POINT_USER)
    suspend fun uploadImageProfile(@Body requestBody: RequestBody): UserResponse

    @PUT(BuildConfig.END_POINT_USER)
    suspend fun updateProfileUser(@Body profileUserRequest: ProfileUserRequest): UserResponse

    @PUT(BuildConfig.END_POINT_CHANGE_PASSWORD)
    suspend fun updatePassword(@Body passwordRequest: PasswordRequest): BaseDefaultResponse

    @GET(BuildConfig.END_POINT_BUYER_ORDER)
    suspend fun getOrder(): List<OrderAccountResponse>

    @GET(BuildConfig.END_POINT_BUYER_WISHLIST)
    suspend fun getWishlist(): List<WishlistAccountResponse>
}