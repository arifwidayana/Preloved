package com.arifwidayana.bid.domain.order

import com.arifwidayana.bid.data.network.repository.BidRepository
import com.arifwidayana.bid.domain.product.DetailProductUseCase
import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.mapper.bid.BidProductResponseMapper
import com.arifwidayana.shared.data.network.model.mapper.bid.BidResponseMapper
import com.arifwidayana.shared.data.network.model.response.bid.order.OrderProductParamResponse
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class OrderProductValidationUseCase(
    private val bidRepository: BidRepository,
    private val detailProductUseCase: DetailProductUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Int, OrderProductParamResponse>(coroutineDispatcher) {
    override suspend fun execute(param: Int?): Flow<ViewResource<OrderProductParamResponse>> = flow {
        emit(ViewResource.Loading())
        detailProductUseCase(param).collect {
            it.suspendSource(
                doOnSuccess = { source ->
                    when (source.payload?.status) {
                        Constant.AVAILABLE -> {
                            bidRepository.getOrderProduct().first().suspendSource(
                                doOnSuccess = { result ->
                                    result.payload?.firstOrNull { data ->
                                        data.productId == param
                                    }?.let { res ->
                                        emit(ViewResource.Success(BidResponseMapper.toViewParam(res)))
                                    }
                                },
                                doOnError = { error ->
                                    emit(ViewResource.Error(error.exception))
                                }
                            )
                        }
                        Constant.SOLD -> {
                            emit(ViewResource.Success(BidProductResponseMapper.toViewParam(source.payload)))
                        }
                    }
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }
}