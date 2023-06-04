package com.arifwidayana.sale.presentation.ui.bid.status

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.request.bidder.SaleBidderParamRequest
import com.arifwidayana.shared.data.network.model.response.sale.bidder.SaleBidderParamResponse
import kotlinx.coroutines.flow.StateFlow

interface StatusContract {
    val statusProductOfferResult: StateFlow<ViewResource<SaleBidderParamResponse>>

    fun statusProductOffer(saleBidderParamRequest: SaleBidderParamRequest)
}