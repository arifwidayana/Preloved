package com.arifwidayana.home.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.home.domain.home.BannerUseCase
import com.arifwidayana.home.domain.home.CategoryProductUseCase
import com.arifwidayana.home.domain.home.ProductUseCase
import com.arifwidayana.shared.data.network.model.request.home.CategoryParamRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val bannerUseCase: BannerUseCase,
    private val categoryProductUseCase: CategoryProductUseCase,
    private val productUseCase: ProductUseCase
) : HomeContract, ViewModel() {
    private val _bannerProductResult = MutableStateFlow<ViewResource<BannerParamDataResponse>>(ViewResource.Empty())
    private val _categoryProductResult = MutableStateFlow<ViewResource<CategoryParamDataResponse>>(ViewResource.Empty())
    private val _showProductResult = MutableStateFlow<ViewResource<BuyerProductParamDataResponse>>(ViewResource.Empty())
    override val bannerProductResult: StateFlow<ViewResource<BannerParamDataResponse>> = _bannerProductResult
    override val categoryProductResult: StateFlow<ViewResource<CategoryParamDataResponse>> = _categoryProductResult
    override val showProductResult: StateFlow<ViewResource<BuyerProductParamDataResponse>> = _showProductResult

    override fun categoryProduct() {
        viewModelScope.launch {
            categoryProductUseCase().collect {
                _categoryProductResult.value = it
            }
            bannerUseCase().collect {
                _bannerProductResult.value = it
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