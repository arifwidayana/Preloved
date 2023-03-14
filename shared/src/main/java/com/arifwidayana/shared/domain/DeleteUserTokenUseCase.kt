package com.arifwidayana.shared.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.repository.UserPreferenceRepository
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class DeleteUserTokenUseCase(
    private val userPreferenceRepository: UserPreferenceRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, String>(coroutineDispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<String>> = flow {
        userPreferenceRepository.deleteUserToken().first().suspendSource(
            doOnSuccess = {
                emit(ViewResource.Success("Logout success"))
            },
            doOnError = {
                emit(ViewResource.Error(IllegalStateException("Failed to logout account")))
            }
        )
    }
}