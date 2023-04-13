package com.arifwidayana.sell.presentation.ui.sell

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.request.sell.SellParamRequest
import com.arifwidayana.shared.data.network.model.request.sell.category.SellCategoryRequest
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import com.arifwidayana.shared.data.network.model.response.sell.SellResponse
import kotlinx.coroutines.flow.StateFlow

interface SellContract {
    val getUserResult: StateFlow<ViewResource<UserParamResponse>>
    val validateTokenUserResult: StateFlow<ViewResource<Boolean>>
    val createProductResult: StateFlow<ViewResource<SellResponse>>
    val listCategoryResult: List<SellCategoryRequest>
    fun getUser()
    fun createProduct(sellParamRequest: SellParamRequest)
    fun listCategory(sellCategoryRequest: SellCategoryRequest)
}