package com.arifwidayana.account.presentation.ui.account

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import kotlinx.coroutines.flow.StateFlow

interface AccountContract {
    val getUserResult: StateFlow<ViewResource<UserParamResponse>>
    val logoutUserResult: StateFlow<ViewResource<String>>
    val validateTokenUserResult: StateFlow<ViewResource<Boolean>>
    fun getUser()
    fun logoutUser()
}