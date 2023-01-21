package com.arifwidayana.home.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.home.domain.CategoryProductUseCase
import com.arifwidayana.home.domain.ProductUseCase
import com.arifwidayana.shared.data.network.model.request.home.CategoryParamRequest
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryParamResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

typealias CategoryDataResponse = List<CategoryParamResponse.CategoryParamResponseItem>
typealias BuyerProductParamDataResponse = PagingData<BuyerProductParamResponse.BuyerProductResponseItem>

class HomeViewModel(
    private val categoryProductUseCase: CategoryProductUseCase,
    private val productUseCase: ProductUseCase
) : HomeContract, ViewModel() {
    private val _categoryProductResult = MutableStateFlow<ViewResource<CategoryDataResponse>>(ViewResource.Empty())
    private val _showProductResult = MutableStateFlow<ViewResource<BuyerProductParamDataResponse>>(ViewResource.Empty())
    override val categoryProductResult: StateFlow<ViewResource<CategoryDataResponse>> = _categoryProductResult
    override val showProductResult: StateFlow<ViewResource<BuyerProductParamDataResponse>> = _showProductResult

    override fun categoryProduct() {
        viewModelScope.launch {
            categoryProductUseCase().collect {
                _categoryProductResult.value = it
            }
        }
    }

    override fun showProduct(categoryId: Int) {
        viewModelScope.launch {
            productUseCase(CategoryParamRequest(categoryId = categoryId)).collect {
                _showProductResult.value = it
            }
        }
    }
}