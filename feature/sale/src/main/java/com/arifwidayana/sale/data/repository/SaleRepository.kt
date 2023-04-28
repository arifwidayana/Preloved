package com.arifwidayana.sale.data.repository

import com.arifwidayana.core.wrapper.DataResource
import com.arifwidayana.sale.data.datasource.SaleDatasource
import com.arifwidayana.shared.data.network.model.response.sale.history.HistoryResponse
import com.arifwidayana.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias HistoryDataResource = DataResource<List<HistoryResponse>>

interface SaleRepository {
    suspend fun historyTransaction(): Flow<HistoryDataResource>
}

class SaleRepositoryImpl(
    private val saleDatasource: SaleDatasource
) : SaleRepository, Repository() {
    override suspend fun historyTransaction(): Flow<HistoryDataResource> = flow {
        emit(safeNetworkCall { saleDatasource.historyTransaction() })
    }
}