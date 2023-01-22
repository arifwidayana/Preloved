package com.arifwidayana.home.presentation.ui.home

import androidx.paging.PagingData
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryParamResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import kotlinx.coroutines.flow.StateFlow

interface HomeContract {
    val categoryProductResult: StateFlow<ViewResource<List<CategoryParamResponse>>>
    val showProductResult: StateFlow<ViewResource<PagingData<BuyerProductParamResponse>>>
    fun categoryProduct()
    fun showProduct(categoryId: Int)
}