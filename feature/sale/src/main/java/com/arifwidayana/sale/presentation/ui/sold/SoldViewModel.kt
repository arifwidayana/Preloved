package com.arifwidayana.sale.presentation.ui.sold

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.sale.domain.SoldUseCase
import com.arifwidayana.shared.data.network.model.response.sale.sold.SaleProductSoldParamResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SoldViewModel(
    private val soldUseCase: SoldUseCase
) : SoldContract, ViewModel() {
    private val _productSoldResult = MutableStateFlow<ViewResource<List<SaleProductSoldParamResponse>>>(ViewResource.Empty())
    override val productSoldResult: StateFlow<ViewResource<List<SaleProductSoldParamResponse>>> = _productSoldResult

    override fun getProductSold() {
        viewModelScope.launch {
            soldUseCase().collect {
                _productSoldResult.value = it
            }
        }
    }
}