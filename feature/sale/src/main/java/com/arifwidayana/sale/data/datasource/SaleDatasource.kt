package com.arifwidayana.sale.data.datasource

import com.arifwidayana.sale.data.service.SaleService
import com.arifwidayana.shared.data.network.model.request.bidder.SaleBidderRequest
import com.arifwidayana.shared.data.network.model.response.sale.bidder.SaleBidderResponse
import com.arifwidayana.shared.data.network.model.response.sale.history.SaleHistoryResponse
import com.arifwidayana.shared.data.network.model.response.sale.order.SaleOrderResponse
import com.arifwidayana.shared.data.network.model.response.sale.product.SaleProductResponse

interface SaleDatasource {
    suspend fun historyTransaction(): List<SaleHistoryResponse>
    suspend fun getSellerProduct(): List<SaleProductResponse>
    suspend fun getSellerOrder(): List<SaleOrderResponse>
    suspend fun patchSellerOrder(idOffer: Int, status: String): SaleBidderResponse
    suspend fun getProductOffer(idOffer: Int): SaleOrderResponse
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

    override suspend fun patchSellerOrder(idOffer: Int, status: String): SaleBidderResponse {
        return saleService.patchSellerOrder(idOffer, SaleBidderRequest(status))
    }

    override suspend fun getProductOffer(idOffer: Int): SaleOrderResponse {
        return saleService.getProductOffer(idOffer)
    }
}