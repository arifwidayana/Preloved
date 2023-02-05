package com.arifwidayana.home.data.network.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arifwidayana.home.data.network.service.HomeService
import com.arifwidayana.shared.data.network.model.request.home.CategoryParamRequest
import com.arifwidayana.shared.data.network.model.response.home.banner.BannerResponse
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductResponse
import com.arifwidayana.shared.utils.Constant.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow

interface HomeDatasource {
    suspend fun showBanner(): List<BannerResponse>
    suspend fun categoryProduct(): List<CategoryResponse>

//    suspend fun showProduct(productParamRequest: ProductParamRequest): List<BuyerProductResponse>
    suspend fun showProduct(categoryParamRequest: CategoryParamRequest): Flow<PagingData<BuyerProductResponse>>
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

//    override suspend fun showProduct(productParamRequest: ProductParamRequest): List<BuyerProductResponse> {
//        return if (productParamRequest.categoryId != 0) {
//            homeService.showProduct(
//                page = productParamRequest.page,
//                perPage = productParamRequest.perPage,
//                categoryId = productParamRequest.categoryId
//            )
//        } else {
//            homeService.showAllProduct(
//                page = productParamRequest.page,
//                perPage = productParamRequest.perPage
//            )
//        }
//    }

    override suspend fun showProduct(categoryParamRequest: CategoryParamRequest): Flow<PagingData<BuyerProductResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PagingDatasource(
                    homeService = homeService,
                    categoryParamRequest = categoryParamRequest
                )
            }
        ).flow
    }
}