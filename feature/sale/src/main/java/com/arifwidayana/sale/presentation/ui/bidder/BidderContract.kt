package com.arifwidayana.sale.presentation.ui.bidder

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.sale.order.SaleOrderParamResponse
import kotlinx.coroutines.flow.StateFlow

interface BidderContract {
    val offerDataResult: StateFlow<ViewResource<SaleOrderParamResponse>>
    fun offerData(idOffer: Int)
}