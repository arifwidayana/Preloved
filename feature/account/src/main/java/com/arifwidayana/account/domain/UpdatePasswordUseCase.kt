package com.arifwidayana.account.domain

import com.arifwidayana.account.data.repository.AccountRepository
import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.mapper.account.password.PasswordMapper
import com.arifwidayana.shared.data.network.model.request.account.password.PasswordParamRequest
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class UpdatePasswordUseCase(
    private val accountRepository: AccountRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<PasswordParamRequest, String>(coroutineDispatcher) {
    override suspend fun execute(param: PasswordParamRequest?): Flow<ViewResource<String>> = flow {
        emit(ViewResource.Loading())
        param?.let {
            accountRepository.updatePassword(PasswordMapper.toDataObject(it)).first().suspendSource(
                doOnSuccess = { source ->
                    emit(ViewResource.Success(source.payload?.message.toString()))
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }
}