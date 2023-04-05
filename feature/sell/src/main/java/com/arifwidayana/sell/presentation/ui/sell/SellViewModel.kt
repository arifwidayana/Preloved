package com.arifwidayana.sell.presentation.ui.sell

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.sell.domain.CreateProductUseCase
import com.arifwidayana.shared.data.network.model.request.sell.SellParamRequest
import com.arifwidayana.shared.data.network.model.request.sell.category.SellCategoryRequest
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import com.arifwidayana.shared.data.network.model.response.sell.SellResponse
import com.arifwidayana.shared.domain.UserUseCase
import com.arifwidayana.shared.domain.ValidateUserTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SellViewModel(
    private val userUseCase: UserUseCase,
    private val createProductUseCase: CreateProductUseCase,
    private val validateUserTokenUseCase: ValidateUserTokenUseCase
) : SellContract, ViewModel() {
    private val _getUserResult = MutableStateFlow<ViewResource<UserParamResponse>>(ViewResource.Empty())
    private val _validateTokenUserResult = MutableStateFlow<ViewResource<Boolean>>(ViewResource.Empty())
    private val _createProductResult = MutableStateFlow<ViewResource<SellResponse>>(ViewResource.Empty())
    private val _listCategoryResult = mutableListOf<SellCategoryRequest>()
    override val getUserResult: StateFlow<ViewResource<UserParamResponse>> = _getUserResult
    override val validateTokenUserResult: StateFlow<ViewResource<Boolean>> = _validateTokenUserResult
    override val createProductResult: StateFlow<ViewResource<SellResponse>> = _createProductResult
    override val listCategoryResult: List<SellCategoryRequest> = _listCategoryResult

    override fun getUser() {
        viewModelScope.launch {
            validateUserTokenUseCase().collect { status ->
                when (status.payload) {
                    true -> {
                        userUseCase().collect {
                            _getUserResult.value = it
                        }
                    }
                    else -> {
                        _validateTokenUserResult.emit(status)
                    }
                }
            }
        }
    }

    override fun createProduct(sellParamRequest: SellParamRequest) {
        viewModelScope.launch {
            createProductUseCase(sellParamRequest).collect {
                _createProductResult.value = it
            }
        }
    }

    override fun listCategory(sellCategoryRequest: SellCategoryRequest) {
        val result = listCategoryResult.map { it.id }
        if (!result.contains(sellCategoryRequest.id)) {
            _listCategoryResult.add(sellCategoryRequest)
        } else {
            _listCategoryResult.remove(sellCategoryRequest)
        }
    }
}