package com.arifwidayana.home.domain.search.product

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arifwidayana.home.data.network.datasource.PagingDatasource
import com.arifwidayana.home.data.network.repository.HomeRepository
import com.arifwidayana.shared.data.network.model.request.home.SearchProductParamRequest
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import com.arifwidayana.shared.utils.Constant
import kotlinx.coroutines.flow.Flow

typealias SearchProductDataResource = PagingData<BuyerProductParamResponse>

class SearchProductUseCase(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(param: String): Flow<SearchProductDataResource> {
        return Pager(
            config = PagingConfig(
                pageSize = Constant.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PagingDatasource(
                    homeRepository = homeRepository,
                    searchProductParamRequest = SearchProductParamRequest(searchProduct = param)
                )
            }
        ).flow
    }
}