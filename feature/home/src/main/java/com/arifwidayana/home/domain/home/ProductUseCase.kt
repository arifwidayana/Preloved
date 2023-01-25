package com.arifwidayana.home.domain.home

import androidx.paging.PagingData
import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.home.data.network.repository.HomeRepository
import com.arifwidayana.shared.data.network.model.mapper.home.BuyerProductMapper
import com.arifwidayana.shared.data.network.model.request.home.CategoryParamRequest
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias ProductDataResource = PagingData<BuyerProductParamResponse>

class ProductUseCase(
    private val homeRepository: HomeRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<CategoryParamRequest, ProductDataResource>(
    coroutineDispatcher
) {
    override suspend fun execute(param: CategoryParamRequest?): Flow<ViewResource<ProductDataResource>> =
        flow {
            emit(ViewResource.Loading())
            param?.let { request ->
                homeRepository.showProduct(request).collect {
                    it.suspendSource(
                        doOnSuccess = { source ->
                            emit(ViewResource.Success(BuyerProductMapper.toViewParam(source.payload)))
                        },
                        doOnError = { error ->
                            emit(ViewResource.Error(error.exception))
                        }
                    )
                }
            }
        }
}