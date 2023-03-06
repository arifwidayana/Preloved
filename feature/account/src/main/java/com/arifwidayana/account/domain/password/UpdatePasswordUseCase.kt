package com.arifwidayana.account.domain.password

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
    private val passwordFieldValidationUseCase: PasswordFieldValidationUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<PasswordParamRequest, String>(coroutineDispatcher) {
    override suspend fun execute(param: PasswordParamRequest?): Flow<ViewResource<String>> = flow {
        emit(ViewResource.Loading())
        param?.let { res ->
            passwordFieldValidationUseCase(res).first().suspendSource(
                doOnSuccess = {
                    accountRepository.updatePassword(PasswordMapper.toDataObject(res)).first().suspendSource(
                        doOnSuccess = { source ->
                            emit(ViewResource.Success(source.payload?.message.toString()))
                        },
                        doOnError = { error ->
                            emit(ViewResource.Error(error.exception))
                        }
                    )
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }
}