package com.arifwidayana.sell.data.datasource

import com.arifwidayana.sell.data.service.SellService
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryResponse
import com.arifwidayana.shared.data.network.model.response.sell.SellResponse
import okhttp3.RequestBody

interface SellDatasource {
    suspend fun createProduct(requestBody: RequestBody): SellResponse
    suspend fun getCategoryProduct(): List<CategoryResponse>
}

class SellDatasourceImpl(
    private val sellService: SellService
) : SellDatasource {
    override suspend fun createProduct(requestBody: RequestBody): SellResponse {
        return sellService.createProduct(requestBody)
    }

    override suspend fun getCategoryProduct(): List<CategoryResponse> {
        return sellService.getCategoryProduct()
    }
}