package com.arifwidayana.home.data.network.service

import com.arifwidayana.shared.data.network.model.response.home.category.CategoryResponse
import com.arifwidayana.shared.utils.Constant.CATEGORY_PRODUCT
import retrofit2.http.GET

interface HomeService {
    @GET(CATEGORY_PRODUCT)
    suspend fun categoryProduct(): CategoryResponse
}