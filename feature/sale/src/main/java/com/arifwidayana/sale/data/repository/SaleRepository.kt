package com.arifwidayana.sale.data.repository

import com.arifwidayana.core.wrapper.DataResource
import com.arifwidayana.sale.data.datasource.SaleDatasource
import com.arifwidayana.shared.data.network.model.response.sale.history.SaleHistoryResponse
import com.arifwidayana.shared.data.network.model.response.sale.order.SaleOrderResponse
import com.arifwidayana.shared.data.network.model.response.sale.product.SaleProductResponse
import com.arifwidayana.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias HistoryDataResource = DataResource<List<SaleHistoryResponse>>
typealias ProductDataResource = DataResource<List<SaleProductResponse>>
typealias OrderDataResource = DataResource<List<SaleOrderResponse>>

interface SaleRepository {
    suspend fun historyTransaction(): Flow<HistoryDataResource>
    suspend fun getSellerProduct(): Flow<ProductDataResource>
    suspend fun getSellerOrder(): Flow<OrderDataResource>
}

class SaleRepositoryImpl(
    private val saleDatasource: SaleDatasource
) : SaleRepository, Repository() {
    override suspend fun historyTransaction(): Flow<HistoryDataResource> = flow {
        emit(safeNetworkCall { saleDatasource.historyTransaction() })
    }

    override suspend fun getSellerProduct(): Flow<ProductDataResource> = flow {
        emit(safeNetworkCall { saleDatasource.getSellerProduct() })
    }

    override suspend fun getSellerOrder(): Flow<OrderDataResource> = flow {
        emit(safeNetworkCall { saleDatasource.getSellerOrder() })
    }
}