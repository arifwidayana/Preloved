package com.arifwidayana.home.domain

import androidx.paging.PagingData
import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.home.data.network.repository.HomeRepository
import com.arifwidayana.shared.data.network.model.mapper.home.BuyerProductPagingMapper
import com.arifwidayana.shared.data.network.model.request.home.CategoryParamRequest
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias BuyerProductDataResource = PagingData<BuyerProductParamResponse.BuyerProductResponseItem>

class ProductUseCase(
    private val homeRepository: HomeRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<CategoryParamRequest, BuyerProductDataResource>(
    coroutineDispatcher
) {
    override suspend fun execute(param: CategoryParamRequest?): Flow<ViewResource<BuyerProductDataResource>> =
        flow {
            emit(ViewResource.Loading())
            param?.let {
                homeRepository.showProduct(it).collect { response ->
                    response.suspendSource(
                        doOnSuccess = { result ->
                            emit(ViewResource.Success(BuyerProductPagingMapper.toViewParam(result.payload)))
                        },
                        doOnError = { error ->
                            emit(ViewResource.Error(error.exception))
                        }
                    )
                }
            }
        }
}