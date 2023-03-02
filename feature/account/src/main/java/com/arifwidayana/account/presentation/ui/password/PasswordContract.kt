package com.arifwidayana.account.presentation.ui.password

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.request.account.password.PasswordParamRequest
import kotlinx.coroutines.flow.StateFlow

interface PasswordContract {
    val updatePasswordResult: StateFlow<ViewResource<String>>
    fun updatePassword(passwordParamRequest: PasswordParamRequest)
}