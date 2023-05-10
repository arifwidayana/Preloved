package com.arifwidayana.sale.presentation.ui.order

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.sale.order.SaleOrderParamResponse
import kotlinx.coroutines.flow.StateFlow

interface OrderContract {
    val orderResult: StateFlow<ViewResource<List<SaleOrderParamResponse>>>
    fun getSellerOrder()
}