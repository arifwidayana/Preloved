package com.arifwidayana.sale.presentation.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.sale.domain.ProductUseCase
import com.arifwidayana.shared.data.network.model.response.sale.product.SaleProductParamResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productUseCase: ProductUseCase
) : ProductContract, ViewModel() {
    private val _sellerProductResult = MutableStateFlow<ViewResource<List<SaleProductParamResponse>>>(ViewResource.Empty())
    override val sellerProductResult: StateFlow<ViewResource<List<SaleProductParamResponse>>> = _sellerProductResult

    override fun getSellerProduct() {
        viewModelScope.launch {
            productUseCase().collect {
                _sellerProductResult.value = it
            }
        }
    }
}