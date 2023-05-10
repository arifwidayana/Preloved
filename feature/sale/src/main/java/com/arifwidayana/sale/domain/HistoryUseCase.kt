package com.arifwidayana.sale.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.sale.data.repository.SaleRepository
import com.arifwidayana.shared.data.network.model.mapper.sale.HistoryMapper
import com.arifwidayana.shared.data.network.model.response.sale.history.SaleHistoryParamResponse
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HistoryUseCase(
    private val saleRepository: SaleRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, List<SaleHistoryParamResponse>>(coroutineDispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<List<SaleHistoryParamResponse>>> = flow {
        emit(ViewResource.Loading())
        saleRepository.historyTransaction().collect {
            it.suspendSource(
                doOnSuccess = { source ->
                    emit(ViewResource.Success(HistoryMapper.toViewParam(source.payload)))
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }
}