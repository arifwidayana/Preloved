package com.arifwidayana.sale.data.service

import com.arifwidayana.core.BuildConfig
import com.arifwidayana.shared.data.network.model.request.bidder.SaleBidderRequest
import com.arifwidayana.shared.data.network.model.response.sale.bidder.SaleBidderResponse
import com.arifwidayana.shared.data.network.model.response.sale.history.SaleHistoryResponse
import com.arifwidayana.shared.data.network.model.response.sale.order.SaleOrderResponse
import com.arifwidayana.shared.data.network.model.response.sale.product.SaleProductResponse
import com.arifwidayana.shared.utils.Constant
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface SaleService {
    @GET(BuildConfig.END_POINT_SELLER_PRODUCT)
    suspend fun getSellerProduct(): List<SaleProductResponse>

    @GET(BuildConfig.END_POINT_SELLER_ORDER)
    suspend fun getSellerOrder(@Query("status") status: String = Constant.PENDING): List<SaleOrderResponse>

    @GET(BuildConfig.END_POINT_DETAIL_SELLER_ORDER)
    suspend fun getProductOffer(@Path(Constant.ID_PATH) idOffer: Int): SaleOrderResponse

    @PATCH(BuildConfig.END_POINT_DETAIL_SELLER_ORDER)
    suspend fun patchSellerOrder(@Path(Constant.ID_PATH) idOffer: Int, @Body saleBidderRequest: SaleBidderRequest): SaleBidderResponse

    @GET(BuildConfig.END_POINT_HISTORY)
    suspend fun historyTransaction(): List<SaleHistoryResponse>
}