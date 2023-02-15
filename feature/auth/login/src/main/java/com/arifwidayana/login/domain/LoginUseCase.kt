package com.arifwidayana.login.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.login.data.network.repository.LoginRepository
import com.arifwidayana.shared.data.local.model.request.UserTokenRequest
import com.arifwidayana.shared.data.network.model.mapper.auth.login.LoginRequestMapper
import com.arifwidayana.shared.data.network.model.mapper.auth.login.LoginResponseMapper
import com.arifwidayana.shared.data.network.model.request.auth.login.LoginParamRequest
import com.arifwidayana.shared.data.network.model.response.auth.login.LoginParamResponse
import com.arifwidayana.shared.domain.SetUserTokenUseCase
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

typealias LoginResponseViewResource = LoginParamResponse
typealias LoginRequestViewResource = LoginParamRequest

class LoginUseCase(
    private val loginFieldValidationUseCase: LoginFieldValidationUseCase,
    private val loginRepository: LoginRepository,
    private val setUserTokenUseCase: SetUserTokenUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<LoginRequestViewResource, LoginResponseViewResource>(coroutineDispatcher) {
    override suspend fun execute(param: LoginRequestViewResource?): Flow<ViewResource<LoginResponseViewResource>> =
        flow {
            emit(ViewResource.Loading())
            loginFieldValidationUseCase(param).first().suspendSource(
                doOnSuccess = {
                    param?.let {
                        loginRepository.loginUser(LoginRequestMapper.toDataObject(it))
                            .collect { response ->
                                response.suspendSource(
                                    doOnSuccess = { source ->
                                        val dataMap = LoginResponseMapper.toViewParam(source.payload)
                                        setUserTokenUseCase(UserTokenRequest(token = dataMap.accessToken)).first()
                                        emit(ViewResource.Success(dataMap))
                                    },
                                    doOnError = { error ->
                                        emit(ViewResource.Error(error.exception))
                                    }
                                )
                            }
                    }
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
}