package com.arifwidayana.sale.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.sale.data.repository.SaleRepository
import com.arifwidayana.shared.data.network.model.mapper.sale.SaleOrderMapper
import com.arifwidayana.shared.data.network.model.response.sale.order.SaleOrderParamResponse
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OrderUseCase(
    private val saleRepository: SaleRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, List<SaleOrderParamResponse>>(coroutineDispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<List<SaleOrderParamResponse>>> = flow {
        emit(ViewResource.Loading())
        saleRepository.getSellerOrder().collect {
            it.suspendSource(
                doOnSuccess = { source ->
                    emit(ViewResource.Success(SaleOrderMapper.toViewParam(source.payload)))
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }
}