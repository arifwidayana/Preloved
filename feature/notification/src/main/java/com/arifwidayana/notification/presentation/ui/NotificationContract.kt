package com.arifwidayana.notification.presentation.ui

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.notification.NotificationParamResponse
import kotlinx.coroutines.flow.StateFlow

typealias NotifyParamDataResult = List<NotificationParamResponse>

interface NotificationContract {
    val getNotifyResult: StateFlow<ViewResource<NotifyParamDataResult>>
    fun getNotify()
    fun readNotification(idNotify: Int)
}