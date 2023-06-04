package com.arifwidayana.sale.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.sale.data.repository.SaleRepository
import com.arifwidayana.shared.data.network.model.mapper.sale.SaleBidderMapper
import com.arifwidayana.shared.data.network.model.request.bidder.SaleBidderParamRequest
import com.arifwidayana.shared.data.network.model.response.sale.bidder.SaleBidderParamResponse
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BidderUseCase(
    private val saleRepository: SaleRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<SaleBidderParamRequest, SaleBidderParamResponse>(coroutineDispatcher) {
    override suspend fun execute(param: SaleBidderParamRequest?): Flow<ViewResource<SaleBidderParamResponse>> = flow {
        emit(ViewResource.Loading())
        param?.let { p ->
            saleRepository.patchSellerOrder(p.idOffer, p.status).collect {
                it.suspendSource(
                    doOnSuccess = { source ->
                        emit(ViewResource.Success(SaleBidderMapper.toViewParam(source.payload)))
                    },
                    doOnError = { error ->
                        emit(ViewResource.Error(error.exception))
                    }
                )
            }
        }
    }
}