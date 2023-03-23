package com.arifwidayana.sell.data.repository

import com.arifwidayana.core.wrapper.DataResource
import com.arifwidayana.sell.data.datasource.SellDatasource
import com.arifwidayana.shared.data.network.model.request.sell.SellRequest
import com.arifwidayana.shared.data.network.model.response.sell.SellResponse
import com.arifwidayana.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias SellDataResource = DataResource<SellResponse>

interface SellRepository {
    suspend fun createProduct(sellRequest: SellRequest): Flow<SellDataResource>
}

class SellRepositoryImpl(
    private val sellDatasource: SellDatasource
) : SellRepository, Repository() {
    override suspend fun createProduct(sellRequest: SellRequest): Flow<SellDataResource> = flow {
        emit(safeNetworkCall { sellDatasource.createProduct(sellRequest) })
    }
}