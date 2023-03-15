package com.arifwidayana.home.data.network.datasource

import com.arifwidayana.home.data.network.service.HomeService
import com.arifwidayana.shared.data.network.model.request.home.ProductParamRequest
import com.arifwidayana.shared.data.network.model.response.home.banner.BannerResponse
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductResponse

interface HomeDatasource {
    suspend fun showBanner(): List<BannerResponse>
    suspend fun categoryProduct(): List<CategoryResponse>
    suspend fun showProduct(productParamRequest: ProductParamRequest): List<BuyerProductResponse>
}

class HomeDatasourceImpl(
    private val homeService: HomeService
) : HomeDatasource {
    override suspend fun showBanner(): List<BannerResponse> {
        return homeService.showBanner()
    }

    override suspend fun categoryProduct(): List<CategoryResponse> {
        return homeService.categoryProduct()
    }

    override suspend fun showProduct(productParamRequest: ProductParamRequest): List<BuyerProductResponse> {
        return when {
            productParamRequest.search.isNotEmpty() -> {
                homeService.searchProduct(
                    page = productParamRequest.page,
                    perPage = productParamRequest.perPage,
                    search = productParamRequest.search
                )
            }
            productParamRequest.categoryId != 0 -> {
                homeService.showProduct(
                    page = productParamRequest.page,
                    perPage = productParamRequest.perPage,
                    categoryId = productParamRequest.categoryId
                )
            }
            else -> {
                homeService.showAllProduct(
                    page = productParamRequest.page,
                    perPage = productParamRequest.perPage
                )
            }
        }
    }
}