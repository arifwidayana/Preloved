package com.arifwidayana.bid.domain.product

import com.arifwidayana.bid.data.network.repository.BidRepository
import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.mapper.home.BuyerProductMapper
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

typealias ProductDataResource = BuyerProductParamResponse

class DetailProductUseCase(
    private val bidRepository: BidRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Int, ProductDataResource>(coroutineDispatcher) {
    override suspend fun execute(param: Int?): Flow<ViewResource<ProductDataResource>> = flow {
        emit(ViewResource.Loading())
        param?.let {
            bidRepository.detailProduct(it).first().suspendSource(
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