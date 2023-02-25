package com.arifwidayana.bid.presentation.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.bid.domain.bid.BidProductUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.request.bid.BidProductParamRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BidProductViewModel(
    private val bidProductUseCase: BidProductUseCase
) : BidProductContract, ViewModel() {
    private val _bidProductResult = MutableStateFlow<ViewResource<Boolean>>(ViewResource.Empty())
    override val bidProductResult: StateFlow<ViewResource<Boolean>> = _bidProductResult

    override fun bidProduct(bidProductParamRequest: BidProductParamRequest) {
        viewModelScope.launch {
            bidProductUseCase(bidProductParamRequest).collect {
                _bidProductResult.value = it
            }
        }
    }
}