package com.arifwidayana.home.presentation.ui.home

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryParamResponse
import kotlinx.coroutines.flow.StateFlow

interface HomeContract {
    val categoryProductResult: StateFlow<ViewResource<List<CategoryParamResponse.CategoryParamResponseItem>>>
    fun categoryProduct()
}