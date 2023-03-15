package com.arifwidayana.home.data.network.service

import com.arifwidayana.core.BuildConfig
import com.arifwidayana.shared.data.network.model.response.home.banner.BannerResponse
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductResponse
import com.arifwidayana.shared.utils.Constant.CATEGORY_ID
import com.arifwidayana.shared.utils.Constant.PAGE
import com.arifwidayana.shared.utils.Constant.PER_PAGE
import com.arifwidayana.shared.utils.Constant.SEARCH
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {
    @GET(BuildConfig.END_POINT_SELLER_BANNER)
    suspend fun showBanner(): List<BannerResponse>

    @GET(BuildConfig.END_POINT_SELLER_CATEGORY)
    suspend fun categoryProduct(): List<CategoryResponse>

    @GET(BuildConfig.END_POINT_BUYER_PRODUCT)
    suspend fun showProduct(
        @Query(PAGE) page: Int,
        @Query(PER_PAGE) perPage: Int,
        @Query(CATEGORY_ID) categoryId: Int
    ): List<BuyerProductResponse>

    @GET(BuildConfig.END_POINT_BUYER_PRODUCT)
    suspend fun showAllProduct(
        @Query(PAGE) page: Int,
        @Query(PER_PAGE) perPage: Int
    ): List<BuyerProductResponse>

    @GET(BuildConfig.END_POINT_BUYER_PRODUCT)
    suspend fun searchProduct(
        @Query(SEARCH) search: String,
        @Query(PAGE) page: Int,
        @Query(PER_PAGE) perPage: Int
    ): List<BuyerProductResponse>
}