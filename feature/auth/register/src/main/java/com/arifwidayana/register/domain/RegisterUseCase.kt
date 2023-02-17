package com.arifwidayana.register.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.register.data.repository.RegisterRepository
import com.arifwidayana.shared.data.network.model.mapper.auth.register.RegisterRequestMapper
import com.arifwidayana.shared.data.network.model.mapper.auth.register.RegisterResponseMapper
import com.arifwidayana.shared.data.network.model.request.auth.register.RegisterParamRequest
import com.arifwidayana.shared.data.network.model.response.auth.register.RegisterParamResponse
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class RegisterUseCase(
    private val registerRepository: RegisterRepository,
    private val registerFieldValidationUseCase: RegisterFieldValidationUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<RegisterParamRequest, RegisterParamResponse>(coroutineDispatcher) {
    override suspend fun execute(param: RegisterParamRequest?): Flow<ViewResource<RegisterParamResponse>> = flow {
        emit(ViewResource.Loading())
        registerFieldValidationUseCase(param).first().suspendSource(
            doOnSuccess = {
                param?.let {
                    registerRepository.registerUser(RegisterRequestMapper.toDataObject(it)).collect { response ->
                        response.suspendSource(
                            doOnSuccess = { source ->
                                emit(ViewResource.Success(RegisterResponseMapper.toViewParam(source.payload)))
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