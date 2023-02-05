package com.arifwidayana.home.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.home.domain.home.BannerUseCase
import com.arifwidayana.home.domain.home.CategoryProductUseCase
import com.arifwidayana.home.domain.home.ProductUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val bannerUseCase: BannerUseCase,
    private val categoryProductUseCase: CategoryProductUseCase,
    private val productUseCase: ProductUseCase
) : HomeContract, ViewModel() {
    private val _bannerProductResult = MutableStateFlow<ViewResource<BannerParamDataResponse>>(ViewResource.Empty())
    private val _categoryProductResult = MutableStateFlow<ViewResource<CategoryParamDataResponse>>(ViewResource.Empty())
    private val _showProductResult = MutableSharedFlow<BuyerProductParamDataResponse>()
    override val bannerProductResult: StateFlow<ViewResource<BannerParamDataResponse>> = _bannerProductResult
    override val categoryProductResult: StateFlow<ViewResource<CategoryParamDataResponse>> = _categoryProductResult
    override val showProductResult: SharedFlow<BuyerProductParamDataResponse> = _showProductResult

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
            productUseCase(categoryId).cachedIn(viewModelScope).collect {
                _showProductResult.emit(it)
            }
        }
    }
}