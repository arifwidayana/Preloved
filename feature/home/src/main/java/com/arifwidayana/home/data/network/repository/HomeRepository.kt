package com.arifwidayana.home.data.network.repository

import androidx.paging.PagingData
import com.arifwidayana.core.wrapper.DataResource
import com.arifwidayana.home.data.network.datasource.HomeDatasource
import com.arifwidayana.shared.data.network.model.request.home.CategoryParamRequest
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductResponse
import com.arifwidayana.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

typealias CategoryDataResource = DataResource<CategoryResponse>
typealias ProductDataResource = DataResource<PagingData<BuyerProductResponse.BuyerProductResponseItem>>

interface HomeRepository {
    suspend fun categoryProduct(): Flow<CategoryDataResource>
    suspend fun showProduct(categoryParamRequest: CategoryParamRequest): Flow<ProductDataResource>
}

class HomeRepositoryImpl(
    private val homeDatasource: HomeDatasource
) : HomeRepository, Repository() {
    override suspend fun categoryProduct(): Flow<CategoryDataResource> = flow {
        emit(safeNetworkCall { homeDatasource.categoryProduct() })
    }

    override suspend fun showProduct(categoryParamRequest: CategoryParamRequest): Flow<ProductDataResource> = flow {
        emit(safeNetworkCall { homeDatasource.showProduct(categoryParamRequest).first() })
    }
}