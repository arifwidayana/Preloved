package com.arifwidayana.bid.presentation.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.bid.domain.product.DetailProductUseCase
import com.arifwidayana.bid.domain.product.ValidateWishlistProductUseCase
import com.arifwidayana.bid.domain.product.WishlistProductUseCase
import com.arifwidayana.core.wrapper.ViewResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailProductViewModel(
    private val detailProductUseCase: DetailProductUseCase,
    private val wishlistProductUseCase: WishlistProductUseCase,
    private val validateWishlistProductUseCase: ValidateWishlistProductUseCase
) : DetailProductContract, ViewModel() {
    private val _detailProductResult = MutableStateFlow<ViewResource<ProductParamDataResponse>>(ViewResource.Empty())
    private val _checkWishlistProductResult = MutableStateFlow<ViewResource<Boolean>>(ViewResource.Empty())
    private val _wishlistProductResult = MutableStateFlow<ViewResource<Boolean>>(ViewResource.Empty())
    override val detailProductResult: StateFlow<ViewResource<ProductParamDataResponse>> = _detailProductResult
    override val checkWishlistProductResult: StateFlow<ViewResource<Boolean>> = _checkWishlistProductResult
    override val wishlistProductResult: StateFlow<ViewResource<Boolean>> = _wishlistProductResult

    override fun detailProduct(idProduct: Int) {
        viewModelScope.launch {
            detailProductUseCase(idProduct).collect {
                _detailProductResult.value = it
            }
//            validateWishlistProductUseCase(idProduct).collect {
//                _checkWishlistProductResult.value = it
//            }
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