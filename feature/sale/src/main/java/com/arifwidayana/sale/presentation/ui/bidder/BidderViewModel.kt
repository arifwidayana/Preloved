package com.arifwidayana.sale.presentation.ui.bidder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.sale.domain.ProductOfferUseCase
import com.arifwidayana.shared.data.network.model.response.sale.order.SaleOrderParamResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BidderViewModel(
    private val productOfferUseCase: ProductOfferUseCase
) : BidderContract, ViewModel() {
    private val _offerDataResult = MutableStateFlow<ViewResource<SaleOrderParamResponse>>(ViewResource.Empty())
    override val offerDataResult: StateFlow<ViewResource<SaleOrderParamResponse>> = _offerDataResult

    override fun offerData(idOffer: Int) {
        viewModelScope.launch {
            productOfferUseCase(idOffer).collect {
                _offerDataResult.value = it
            }
        }
    }
}