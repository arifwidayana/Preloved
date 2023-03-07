package com.arifwidayana.account.domain.order

import com.arifwidayana.account.data.repository.AccountRepository
import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.mapper.account.order.OrderListMapper
import com.arifwidayana.shared.data.network.model.response.account.order.OrderAccountParamResponse
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

typealias OrderParamDataResource = List<OrderAccountParamResponse>

class OrderUseCase(
    private val accountRepository: AccountRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, OrderParamDataResource>(coroutineDispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<OrderParamDataResource>> = flow {
        emit(ViewResource.Loading())
        accountRepository.getOrder().first().suspendSource(
            doOnSuccess = { source ->
                emit(ViewResource.Success(OrderListMapper.toViewParam(source.payload)))
            },
            doOnError = { error ->
                emit(ViewResource.Error(error.exception))
            }
        )
    }
}