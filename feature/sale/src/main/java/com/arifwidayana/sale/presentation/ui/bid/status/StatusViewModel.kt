package com.arifwidayana.sale.presentation.ui.bid.status

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.sale.domain.BidderUseCase
import com.arifwidayana.shared.data.network.model.request.bidder.SaleBidderParamRequest
import com.arifwidayana.shared.data.network.model.response.sale.bidder.SaleBidderParamResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StatusViewModel(
    private val bidderUseCase: BidderUseCase
) : StatusContract, ViewModel() {
    private val _statusProductOfferResult = MutableStateFlow<ViewResource<SaleBidderParamResponse>>(ViewResource.Empty())
    override val statusProductOfferResult: StateFlow<ViewResource<SaleBidderParamResponse>> = _statusProductOfferResult

    override fun statusProductOffer(saleBidderParamRequest: SaleBidderParamRequest) {
        viewModelScope.launch {
            bidderUseCase(saleBidderParamRequest).collect {
                _statusProductOfferResult.value = it
            }
        }
    }
}