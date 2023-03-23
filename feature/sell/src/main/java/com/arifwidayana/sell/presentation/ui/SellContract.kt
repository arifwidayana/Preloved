package com.arifwidayana.sell.presentation.ui

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.request.sell.SellParamRequest
import com.arifwidayana.shared.data.network.model.response.sell.SellResponse
import kotlinx.coroutines.flow.StateFlow

interface SellContract {
    val createProductResult: StateFlow<ViewResource<SellResponse>>
    fun createProduct(sellParamRequest: SellParamRequest)
}