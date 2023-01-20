package com.arifwidayana.home.data.network.repository

import com.arifwidayana.core.wrapper.DataResource
import com.arifwidayana.home.data.network.datasource.HomeDatasource
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryResponse
import com.arifwidayana.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias HomeDataResource = DataResource<CategoryResponse>

interface HomeRepository {
    suspend fun categoryProduct(): Flow<HomeDataResource>
}

class HomeRepositoryImpl(
    private val homeDatasource: HomeDatasource
) : HomeRepository, Repository() {
    override suspend fun categoryProduct(): Flow<HomeDataResource> = flow {
        emit(safeNetworkCall { homeDatasource.categoryProduct() })
    }
}