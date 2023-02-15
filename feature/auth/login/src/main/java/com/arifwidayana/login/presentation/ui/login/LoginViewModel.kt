package com.arifwidayana.login.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.login.domain.LoginUseCase
import com.arifwidayana.shared.data.network.model.request.auth.login.LoginParamRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : LoginContract, ViewModel() {
    private val _loginUserResult = MutableStateFlow<ViewResource<LoginParamDataResponse>>(ViewResource.Empty())
    override val loginUserResult: StateFlow<ViewResource<LoginParamDataResponse>> = _loginUserResult

    override fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(
                LoginParamRequest(
                    email = email,
                    password = password
                )
            ).collect {
                _loginUserResult.value = it
            }
        }
    }
}