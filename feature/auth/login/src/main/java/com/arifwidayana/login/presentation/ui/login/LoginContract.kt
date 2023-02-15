package com.arifwidayana.login.presentation.ui.login

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.auth.login.LoginParamResponse
import kotlinx.coroutines.flow.StateFlow

typealias LoginParamDataResponse = LoginParamResponse

interface LoginContract {
    val loginUserResult: StateFlow<ViewResource<LoginParamDataResponse>>
    fun loginUser(email: String, password: String)
}