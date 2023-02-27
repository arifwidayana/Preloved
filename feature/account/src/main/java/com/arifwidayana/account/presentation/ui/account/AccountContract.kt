package com.arifwidayana.account.presentation.ui.account

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import kotlinx.coroutines.flow.StateFlow

interface AccountContract {
    val getUserResult: StateFlow<ViewResource<UserParamResponse>>
    fun getUser()
}