package com.arifwidayana.sale.data.datasource

import com.arifwidayana.sale.data.service.SaleService
import com.arifwidayana.shared.data.network.model.response.sale.history.SaleHistoryResponse
import com.arifwidayana.shared.data.network.model.response.sale.order.SaleOrderResponse
import com.arifwidayana.shared.data.network.model.response.sale.product.SaleProductResponse

interface SaleDatasource {
    suspend fun historyTransaction(): List<SaleHistoryResponse>
    suspend fun getSellerProduct(): List<SaleProductResponse>
    suspend fun getSellerOrder(): List<SaleOrderResponse>
}

class SaleDatasourceImpl(
    private val saleService: SaleService
) : SaleDatasource {
    override suspend fun historyTransaction(): List<SaleHistoryResponse> {
        return saleService.historyTransaction()
    }

    override suspend fun getSellerProduct(): List<SaleProductResponse> {
        return saleService.getSellerProduct()
    }

    override suspend fun getSellerOrder(): List<SaleOrderResponse> {
        return saleService.getSellerOrder()
    }
}