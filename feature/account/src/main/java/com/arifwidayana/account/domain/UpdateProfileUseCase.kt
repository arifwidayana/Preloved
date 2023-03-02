package com.arifwidayana.account.domain

import com.arifwidayana.account.data.repository.AccountRepository
import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.mapper.account.profile.ProfileRequestMapper
import com.arifwidayana.shared.data.network.model.request.account.profile.ProfileUserParamRequest
import com.arifwidayana.shared.utils.ext.suspendSource
import com.arifwidayana.style.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class UpdateProfileUseCase(
    private val accountRepository: AccountRepository,
    private val profileFieldValidationUseCase: ProfileFieldValidationUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<ProfileUserParamRequest, Int>(coroutineDispatcher) {
    override suspend fun execute(param: ProfileUserParamRequest?): Flow<ViewResource<Int>> = flow {
        emit(ViewResource.Loading())
        param?.let { res ->
            profileFieldValidationUseCase(res).first().suspendSource(
                doOnSuccess = {
                    accountRepository.updateProfileUser(ProfileRequestMapper.toDataObject(res)).first().suspendSource(
                        doOnSuccess = {
                            emit(ViewResource.Success(R.string.message_success_update_profile))
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