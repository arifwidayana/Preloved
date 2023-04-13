package com.arifwidayana.sell.data.repository

import com.arifwidayana.core.wrapper.DataResource
import com.arifwidayana.sell.data.datasource.SellDatasource
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryResponse
import com.arifwidayana.shared.data.network.model.response.sell.SellResponse
import com.arifwidayana.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.RequestBody

typealias SellDataResource = DataResource<SellResponse>
typealias CategoryDataResource = DataResource<List<CategoryResponse>>

interface SellRepository {
    suspend fun createProduct(requestBody: RequestBody): Flow<SellDataResource>
    suspend fun getCategoryProduct(): Flow<CategoryDataResource>
}

class SellRepositoryImpl(
    private val sellDatasource: SellDatasource
) : SellRepository, Repository() {
    override suspend fun createProduct(requestBody: RequestBody): Flow<SellDataResource> = flow {
        emit(safeNetworkCall { sellDatasource.createProduct(requestBody) })
    }

    override suspend fun getCategoryProduct(): Flow<CategoryDataResource> = flow {
        emit(safeNetworkCall { sellDatasource.getCategoryProduct() })
    }
}