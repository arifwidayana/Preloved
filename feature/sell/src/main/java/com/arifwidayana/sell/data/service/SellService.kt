package com.arifwidayana.sell.data.service

import com.arifwidayana.core.BuildConfig
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryResponse
import com.arifwidayana.shared.data.network.model.response.sell.SellResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SellService {
    @POST(BuildConfig.END_POINT_SELLER_PRODUCT)
    suspend fun createProduct(@Body requestBody: RequestBody): SellResponse

    @GET(BuildConfig.END_POINT_SELLER_CATEGORY)
    suspend fun getCategoryProduct(): List<CategoryResponse>
}