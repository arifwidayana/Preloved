package com.arifwidayana.home.presentation.ui.search.product

import androidx.paging.PagingData
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import kotlinx.coroutines.flow.StateFlow

typealias SearchProductParamDataResult = PagingData<BuyerProductParamResponse>

interface SearchProductContract {
    val searchProductResult: StateFlow<SearchProductParamDataResult>
    fun searchProduct(search: String)
}