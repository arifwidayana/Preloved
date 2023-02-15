package com.arifwidayana.home.presentation.ui.home

import androidx.paging.PagingData
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.home.banner.BannerParamResponse
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryParamResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import kotlinx.coroutines.flow.StateFlow

typealias BannerParamDataResponse = List<BannerParamResponse>
typealias CategoryParamDataResponse = List<CategoryParamResponse>
typealias BuyerProductParamDataResponse = PagingData<BuyerProductParamResponse>

interface HomeContract {
    val bannerProductResult: StateFlow<ViewResource<BannerParamDataResponse>>
    val categoryProductResult: StateFlow<ViewResource<CategoryParamDataResponse>>
    val showProductResult: StateFlow<BuyerProductParamDataResponse>
    fun categoryProduct()
    fun showProduct(categoryId: Int? = 0)
}