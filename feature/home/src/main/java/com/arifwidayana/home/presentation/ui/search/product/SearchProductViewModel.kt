package com.arifwidayana.home.presentation.ui.search.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.arifwidayana.home.domain.search.product.SearchProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchProductViewModel(
    private val searchProductUseCase: SearchProductUseCase
) : SearchProductContract, ViewModel() {
    private val _searchProductResult = MutableStateFlow<SearchProductParamDataResult>(PagingData.empty())
    override val searchProductResult: StateFlow<SearchProductParamDataResult> = _searchProductResult

    override fun searchProduct(search: String) {
        viewModelScope.launch {
            searchProductUseCase(search).cachedIn(viewModelScope).collect {
                _searchProductResult.value = it
            }
        }
    }
}