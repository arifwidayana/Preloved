package com.arifwidayana.sale.data.service

import com.arifwidayana.core.BuildConfig
import com.arifwidayana.shared.data.network.model.response.sale.history.HistoryResponse
import retrofit2.http.GET

interface SaleService {
    @GET(BuildConfig.END_POINT_HISTORY)
    suspend fun historyTransaction(): List<HistoryResponse>
}