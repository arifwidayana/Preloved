package com.arifwidayana.shared.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.DataResource
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.local.model.UserTokenRequest
import com.arifwidayana.shared.data.local.repository.UserPreferenceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class SetUserTokenUseCase(
    private val userPreferenceRepository: UserPreferenceRepository,
    coroutineDispatcher: CoroutineDispatcher
): BaseUseCase<UserTokenRequest, String>(coroutineDispatcher) {
    override suspend fun execute(param: UserTokenRequest?): Flow<ViewResource<String>> = flow {
        param?.let {
            when(userPreferenceRepository.setUserToken(it.token).first()) {
                is DataResource.Success -> { emit(ViewResource.Success(it.token)) }
                is DataResource.Error -> { emit(ViewResource.Error(IllegalStateException("Failed to save token"))) }
            }
        }
    }
}