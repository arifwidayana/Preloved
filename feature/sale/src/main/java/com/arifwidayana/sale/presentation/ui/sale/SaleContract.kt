package com.arifwidayana.sale.presentation.ui.sale

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import kotlinx.coroutines.flow.StateFlow

interface SaleContract {
    val getUserResult: StateFlow<ViewResource<UserParamResponse>>
    fun getUser()
}