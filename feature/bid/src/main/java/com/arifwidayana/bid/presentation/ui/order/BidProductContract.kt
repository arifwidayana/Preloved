package com.arifwidayana.bid.presentation.ui.order

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.request.bid.BidProductParamRequest
import kotlinx.coroutines.flow.StateFlow

interface BidProductContract {
    val bidProductResult: StateFlow<ViewResource<Boolean>>
    fun bidProduct(bidProductParamRequest: BidProductParamRequest)
}