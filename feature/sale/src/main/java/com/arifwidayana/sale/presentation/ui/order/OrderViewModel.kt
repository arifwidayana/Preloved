package com.arifwidayana.sale.presentation.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.sale.domain.OrderUseCase
import com.arifwidayana.shared.data.network.model.response.sale.order.SaleOrderParamResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrderViewModel(
    private val orderUseCase: OrderUseCase
) : OrderContract, ViewModel() {
    private val _orderResult = MutableStateFlow<ViewResource<List<SaleOrderParamResponse>>>(ViewResource.Empty())
    override val orderResult: StateFlow<ViewResource<List<SaleOrderParamResponse>>> = _orderResult

    override fun getSellerOrder() {
        viewModelScope.launch {
            orderUseCase().collect {
                _orderResult.value = it
            }
        }
    }
}