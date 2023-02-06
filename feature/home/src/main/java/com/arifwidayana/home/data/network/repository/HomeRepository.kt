package com.arifwidayana.home.data.network.repository

import com.arifwidayana.core.wrapper.DataResource
import com.arifwidayana.home.data.network.datasource.HomeDatasource
import com.arifwidayana.shared.data.network.model.request.home.ProductParamRequest
import com.arifwidayana.shared.data.network.model.response.home.banner.BannerResponse
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductResponse
import com.arifwidayana.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias BannerDataResource = DataResource<List<BannerResponse>>
typealias CategoryDataResource = DataResource<List<CategoryResponse>>
typealias ProductDataResource = List<BuyerProductResponse>

interface HomeRepository {
    suspend fun showBanner(): Flow<BannerDataResource>
    suspend fun categoryProduct(): Flow<CategoryDataResource>
    suspend fun showProduct(productParamRequest: ProductParamRequest): ProductDataResource
}

class HomeRepositoryImpl(
    private val homeDatasource: HomeDatasource
) : HomeRepository, Repository() {
    override suspend fun showBanner(): Flow<BannerDataResource> = flow {
        emit(safeNetworkCall { homeDatasource.showBanner() })
    }

    override suspend fun categoryProduct(): Flow<CategoryDataResource> = flow {
        emit(safeNetworkCall { homeDatasource.categoryProduct() })
    }

    override suspend fun showProduct(productParamRequest: ProductParamRequest): ProductDataResource {
        return homeDatasource.showProduct(productParamRequest)
    }
}