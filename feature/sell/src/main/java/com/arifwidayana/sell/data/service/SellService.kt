package com.arifwidayana.sell.data.service

import com.arifwidayana.core.BuildConfig
import com.arifwidayana.shared.data.network.model.request.sell.SellRequest
import com.arifwidayana.shared.data.network.model.response.sell.SellResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SellService {
    @POST(BuildConfig.END_POINT_SELLER_PRODUCT)
    suspend fun createProduct(@Body sellRequest: SellRequest): SellResponse
//
//    @GET(BuildConfig.END_POINT_SELLER_PRODUCT)
//    suspend fun getProduct(): List<SellResponse>
//
//    @GET(BuildConfig.END_POINT_DETAIL_SELLER_PRODUCT)
//    suspend fun getDetailProduct(): SellResponse
//
//    @PUT(BuildConfig.END_POINT_DETAIL_SELLER_PRODUCT)
//    suspend fun updateProduct()
//
//    @PATCH(BuildConfig.END_POINT_DETAIL_SELLER_PRODUCT)
//    suspend fun statusProduct()
//
//    @DELETE(BuildConfig.END_POINT_DETAIL_SELLER_PRODUCT)
//    suspend fun deleteProduct()
}