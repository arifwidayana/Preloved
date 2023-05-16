package com.arifwidayana.sale.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.sale.data.repository.SaleRepository
import com.arifwidayana.shared.data.network.model.mapper.sale.ListSaleOrderMapper
import com.arifwidayana.shared.data.network.model.response.sale.order.SaleOrderParamResponse
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductOfferUseCase(
    private val saleRepository: SaleRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Int, SaleOrderParamResponse>(coroutineDispatcher) {
    override suspend fun execute(param: Int?): Flow<ViewResource<SaleOrderParamResponse>> = flow {
        emit(ViewResource.Loading())
        param?.let { p ->
            saleRepository.getProductOffer(p).collect {
                it.suspendSource(
                    doOnSuccess = { source ->
                        emit(ViewResource.Success(ListSaleOrderMapper.toViewParam(source.payload)))
                    },
                    doOnError = { error ->
                        emit(ViewResource.Error(error.exception))
                    }
                )
            }
        }
    }
}