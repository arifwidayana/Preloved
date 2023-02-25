package com.arifwidayana.bid.presentation.ui.product

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.bid.order.OrderProductParamResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import kotlinx.coroutines.flow.StateFlow

typealias ProductParamDataResponse = BuyerProductParamResponse

interface DetailProductContract {
    val detailProductResult: StateFlow<ViewResource<ProductParamDataResponse>>
    val checkWishlistProductResult: StateFlow<ViewResource<Boolean>>
    val wishlistProductResult: StateFlow<ViewResource<Boolean>>
    val orderStatusResult: StateFlow<ViewResource<OrderProductParamResponse>>
    fun detailProduct(idProduct: Int)
    fun wishlistProduct(idProduct: Int)
}