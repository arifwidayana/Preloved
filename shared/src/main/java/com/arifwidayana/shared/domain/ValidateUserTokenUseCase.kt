package com.arifwidayana.shared.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class ValidateUserTokenUseCase(
    private val getUserTokenUseCase: GetUserTokenUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, Boolean>(coroutineDispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<Boolean>> = flow {
        getUserTokenUseCase().first().suspendSource(
            doOnSuccess = {
                emit(ViewResource.Success(it.payload?.isNotEmpty() == true))
            },
            doOnError = { error ->
                emit(ViewResource.Error(error.exception))
            }
        )
    }
}