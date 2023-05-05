package com.arifwidayana.sale.presentation.ui.sold

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.sale.sold.SaleProductSoldParamResponse
import kotlinx.coroutines.flow.StateFlow

interface SoldContract {
    val productSoldResult: StateFlow<ViewResource<List<SaleProductSoldParamResponse>>>
    fun getProductSold()
}