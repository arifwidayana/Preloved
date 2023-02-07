package com.arifwidayana.shared.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.repository.UserPreferenceRepository
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserTokenUseCase(
    private val userPreferenceRepository: UserPreferenceRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, String>(coroutineDispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<String>> = flow {
        userPreferenceRepository.getUserToken().collect {
            it.suspendSource(
                doOnSuccess = { result ->
                    emit(ViewResource.Success(result.payload.orEmpty()))
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }
}