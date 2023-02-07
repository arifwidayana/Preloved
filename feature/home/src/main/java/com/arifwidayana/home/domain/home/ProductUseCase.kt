package com.arifwidayana.home.domain.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arifwidayana.home.data.network.datasource.PagingDatasource
import com.arifwidayana.home.data.network.repository.HomeRepository
import com.arifwidayana.shared.data.network.model.request.home.CategoryParamRequest
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import com.arifwidayana.shared.utils.Constant
import kotlinx.coroutines.flow.Flow

typealias ProductDataResource = PagingData<BuyerProductParamResponse>

class ProductUseCase(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(param: Int): Flow<ProductDataResource> {
        return Pager(
            config = PagingConfig(
                pageSize = Constant.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PagingDatasource(
                    homeRepository = homeRepository,
                    categoryParamRequest = CategoryParamRequest(param)
                )
            }
        ).flow
    }
}