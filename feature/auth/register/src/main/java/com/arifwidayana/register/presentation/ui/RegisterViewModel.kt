package com.arifwidayana.register.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.register.domain.RegisterUseCase
import com.arifwidayana.shared.data.network.model.request.auth.register.RegisterParamRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : RegisterContract, ViewModel() {
    private val _registerUserResult = MutableStateFlow<ViewResource<RegisterDataParamResponse>>(ViewResource.Empty())
    override val registerUserResult: StateFlow<ViewResource<RegisterDataParamResponse>> = _registerUserResult

    override fun registerUser(registerParamRequest: RegisterParamRequest) {
        viewModelScope.launch {
            registerUseCase(registerParamRequest).collect {
                _registerUserResult.value = it
            }
        }
    }
}