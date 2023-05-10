package com.arifwidayana.sale.presentation.ui.product

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.sale.product.SaleProductParamResponse
import kotlinx.coroutines.flow.StateFlow

interface ProductContract {
    val sellerProductResult: StateFlow<ViewResource<List<SaleProductParamResponse>>>
    fun getSellerProduct()
}