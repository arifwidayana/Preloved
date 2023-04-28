package com.arifwidayana.sale.data.datasource

import com.arifwidayana.sale.data.service.SaleService
import com.arifwidayana.shared.data.network.model.response.sale.history.HistoryResponse

interface SaleDatasource {
    suspend fun historyTransaction(): List<HistoryResponse>
}

class SaleDatasourceImpl(
    private val saleService: SaleService
) : SaleDatasource {
    override suspend fun historyTransaction(): List<HistoryResponse> {
        return saleService.historyTransaction()
    }
}