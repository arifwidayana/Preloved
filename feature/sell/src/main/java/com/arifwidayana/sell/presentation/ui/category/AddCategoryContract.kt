package com.arifwidayana.sell.presentation.ui.category

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryParamResponse
import kotlinx.coroutines.flow.StateFlow

typealias CategoryDataResult = List<CategoryParamResponse>

interface AddCategoryContract {
    val categoryProductResult: StateFlow<ViewResource<CategoryDataResult>>
    fun categoryProduct()
}