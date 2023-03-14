package com.arifwidayana.account.presentation.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.account.domain.order.OrderUseCase
import com.arifwidayana.core.wrapper.ViewResource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class OrderViewModel(
    private val orderUseCase: OrderUseCase
) : OrderContract, ViewModel() {
    private val _getOrderResult = MutableStateFlow<ViewResource<OrderDataResult>>(ViewResource.Empty())
    override val getOrderResult: StateFlow<ViewResource<OrderDataResult>> = _getOrderResult.asStateFlow()

    override fun getOrder() {
        viewModelScope.launch {
            orderUseCase().collect {
                _getOrderResult.value = it
            }
        }
    }
}