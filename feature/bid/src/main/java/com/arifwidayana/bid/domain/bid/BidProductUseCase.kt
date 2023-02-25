package com.arifwidayana.bid.domain.bid

import com.arifwidayana.bid.data.network.repository.BidRepository
import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.mapper.bid.BidRequestMapper
import com.arifwidayana.shared.data.network.model.request.bid.BidProductParamRequest
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class BidProductUseCase(
    private val bidRepository: BidRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<BidProductParamRequest, Boolean>(coroutineDispatcher) {
    override suspend fun execute(param: BidProductParamRequest?): Flow<ViewResource<Boolean>> = flow {
        emit(ViewResource.Loading())
        param?.let {
            bidRepository.postOrderProduct(BidRequestMapper.toDataObject(it)).first().suspendSource(
                doOnSuccess = { source ->
                    emit(ViewResource.Success(source.payload?.status == Constant.PENDING, "Bid product success"))
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }
}