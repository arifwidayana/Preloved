package com.arifwidayana.sell.presentation.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.sell.domain.CategoryProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddCategoryViewModel(
    private val categoryProductUseCase: CategoryProductUseCase
) : AddCategoryContract, ViewModel() {
    private val _categoryProductResult = MutableStateFlow<ViewResource<CategoryDataResult>>(ViewResource.Empty())
    override val categoryProductResult: StateFlow<ViewResource<CategoryDataResult>> = _categoryProductResult

    override fun categoryProduct() {
        viewModelScope.launch {
            categoryProductUseCase().collect {
                _categoryProductResult.value = it
            }
        }
    }
}