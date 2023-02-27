package com.arifwidayana.account.domain

import com.arifwidayana.account.data.repository.AccountRepository
import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.mapper.account.AccountMapper
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class UserUseCase(
    private val accountRepository: AccountRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, UserParamResponse>(coroutineDispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<UserParamResponse>> = flow {
        emit(ViewResource.Loading())
        accountRepository.getUser().first().suspendSource(
            doOnSuccess = { source ->
                emit(ViewResource.Success(AccountMapper.toViewParam(source.payload)))
            },
            doOnError = { error ->
                emit(ViewResource.Error(error.exception))
            }
        )
    }
}