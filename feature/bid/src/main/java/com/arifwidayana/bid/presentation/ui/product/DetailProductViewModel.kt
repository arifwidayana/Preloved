package com.arifwidayana.bid.presentation.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.bid.domain.order.OrderProductValidationUseCase
import com.arifwidayana.bid.domain.product.DetailProductUseCase
import com.arifwidayana.bid.domain.wishlist.WishlistProductUseCase
import com.arifwidayana.bid.domain.wishlist.WishlistProductValidationUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.bid.order.OrderProductParamResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailProductViewModel(
    private val detailProductUseCase: DetailProductUseCase,
    private val wishlistProductUseCase: WishlistProductUseCase,
    private val wishlistProductValidationUseCase: WishlistProductValidationUseCase,
    private val orderProductValidationUseCase: OrderProductValidationUseCase
) : DetailProductContract, ViewModel() {
    private val _detailProductResult = MutableStateFlow<ViewResource<ProductParamDataResponse>>(ViewResource.Empty())
    private val _checkWishlistProductResult = MutableStateFlow<ViewResource<Boolean>>(ViewResource.Empty())
    private val _wishlistProductResult = MutableStateFlow<ViewResource<Boolean>>(ViewResource.Empty())
    private val _orderStatusResult = MutableStateFlow<ViewResource<OrderProductParamResponse>>(ViewResource.Empty())
    override val detailProductResult: StateFlow<ViewResource<ProductParamDataResponse>> = _detailProductResult
    override val checkWishlistProductResult: StateFlow<ViewResource<Boolean>> = _checkWishlistProductResult
    override val wishlistProductResult: StateFlow<ViewResource<Boolean>> = _wishlistProductResult
    override val orderStatusResult: StateFlow<ViewResource<OrderProductParamResponse>> = _orderStatusResult

    override fun detailProduct(idProduct: Int) {
        viewModelScope.launch {
            detailProductUseCase(idProduct).collect {
                _detailProductResult.value = it
            }
            wishlistProductValidationUseCase(idProduct).collect {
                _checkWishlistProductResult.value = it
            }
            orderProductValidationUseCase(idProduct).collect {
                _orderStatusResult.value = it
            }
        }
    }

    override fun wishlistProduct(idProduct: Int) {
        viewModelScope.launch {
            wishlistProductUseCase(idProduct).collect {
                _wishlistProductResult.value = it
            }
        }
    }
}