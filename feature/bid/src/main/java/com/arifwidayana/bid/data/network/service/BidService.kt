package com.arifwidayana.bid.data.network.service

import com.arifwidayana.core.BuildConfig
import com.arifwidayana.shared.data.network.model.request.bid.WishlistProductRequest
import com.arifwidayana.shared.data.network.model.response.bid.StatusWishlistProductResponse
import com.arifwidayana.shared.data.network.model.response.bid.WishlistProductResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductResponse
import com.arifwidayana.shared.utils.Constant.ID_PATH
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BidService {
    @GET(BuildConfig.END_POINT_DETAIL_BUYER_PRODUCT)
    suspend fun detailProduct(@Path(ID_PATH) idProduct: Int): BuyerProductResponse

    @POST(BuildConfig.END_POINT_BUYER_WISHLIST)
    suspend fun postWishlistProduct(@Body wishlistProductRequest: WishlistProductRequest): StatusWishlistProductResponse

    @GET(BuildConfig.END_POINT_BUYER_WISHLIST)
    suspend fun wishlistProduct(): List<WishlistProductResponse>

    @DELETE(BuildConfig.END_POINT_DETAIL_BUYER_WISHLIST)
    suspend fun deleteWishlistProduct(@Path(ID_PATH) idWishlist: Int): StatusWishlistProductResponse
}