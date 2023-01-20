package com.arifwidayana.home.data.network.datasource

import com.arifwidayana.home.data.network.service.HomeService
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryResponse

interface HomeDatasource {
    suspend fun categoryProduct(): CategoryResponse
}

class HomeDatasourceImpl(
    private val homeService: HomeService
) : HomeDatasource {
    override suspend fun categoryProduct(): CategoryResponse {
        return homeService.categoryProduct()
    }
}