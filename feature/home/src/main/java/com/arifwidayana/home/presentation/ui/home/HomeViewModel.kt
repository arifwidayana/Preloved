package com.arifwidayana.home.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.home.domain.CategoryProductUseCase
import com.arifwidayana.home.domain.HomeUseCase
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryParamResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

typealias CategoryDataResponse = List<CategoryParamResponse.CategoryParamResponseItem>

class HomeViewModel(
    private val categoryProductUseCase: CategoryProductUseCase,
    private val homeUseCase: HomeUseCase
) : HomeContract, ViewModel() {
    private val _categoryProductResult = MutableStateFlow<ViewResource<CategoryDataResponse>>(ViewResource.Empty())
    override val categoryProductResult: StateFlow<ViewResource<CategoryDataResponse>> = _categoryProductResult

    override fun categoryProduct() {
        viewModelScope.launch {
            categoryProductUseCase().collect {
                _categoryProductResult.value = it
            }
        }
    }
}