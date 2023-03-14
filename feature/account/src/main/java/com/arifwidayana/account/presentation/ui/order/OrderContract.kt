package com.arifwidayana.account.presentation.ui.order

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.account.order.OrderAccountParamResponse
import kotlinx.coroutines.flow.StateFlow

typealias OrderDataResult = List<OrderAccountParamResponse>

interface OrderContract {
    val getOrderResult: StateFlow<ViewResource<OrderDataResult>>
    fun getOrder()
}