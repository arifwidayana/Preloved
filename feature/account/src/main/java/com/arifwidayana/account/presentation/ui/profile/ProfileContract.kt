package com.arifwidayana.account.presentation.ui.profile

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import kotlinx.coroutines.flow.StateFlow

interface ProfileContract {
    val getUserResult: StateFlow<ViewResource<UserParamResponse>>
    fun getUser()
}