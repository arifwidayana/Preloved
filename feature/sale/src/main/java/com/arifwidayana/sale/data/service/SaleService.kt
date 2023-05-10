package com.arifwidayana.sale.data.service

import com.arifwidayana.core.BuildConfig
import com.arifwidayana.shared.data.network.model.response.sale.history.SaleHistoryResponse
import com.arifwidayana.shared.data.network.model.response.sale.order.SaleOrderResponse
import com.arifwidayana.shared.data.network.model.response.sale.product.SaleProductResponse
import com.arifwidayana.shared.utils.Constant
import retrofit2.http.GET
import retrofit2.http.Query

interface SaleService {
    @GET(BuildConfig.END_POINT_SELLER_PRODUCT)
    suspend fun getSellerProduct(): List<SaleProductResponse>

    @GET(BuildConfig.END_POINT_SELLER_ORDER)
    suspend fun getSellerOrder(@Query("status") status: String = Constant.PENDING): List<SaleOrderResponse>

    @GET(BuildConfig.END_POINT_HISTORY)
    suspend fun historyTransaction(): List<SaleHistoryResponse>
}