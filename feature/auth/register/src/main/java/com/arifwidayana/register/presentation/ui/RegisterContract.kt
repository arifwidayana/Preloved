package com.arifwidayana.register.presentation.ui

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.request.auth.register.RegisterParamRequest
import com.arifwidayana.shared.data.network.model.response.auth.register.RegisterParamResponse
import kotlinx.coroutines.flow.StateFlow

typealias RegisterDataParamResponse = RegisterParamResponse

interface RegisterContract {
    val registerUserResult: StateFlow<ViewResource<RegisterDataParamResponse>>
    fun registerUser(registerParamRequest: RegisterParamRequest)
}