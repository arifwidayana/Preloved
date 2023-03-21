package com.arifwidayana.notification.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.notification.domain.NotificationUseCase
import com.arifwidayana.notification.domain.ReadNotificationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val notificationUseCase: NotificationUseCase,
    private val readNotificationUseCase: ReadNotificationUseCase
) : NotificationContract, ViewModel() {
    private val _getNotifyResult = MutableStateFlow<ViewResource<NotifyParamDataResult>>(ViewResource.Empty())
    override val getNotifyResult: StateFlow<ViewResource<NotifyParamDataResult>> = _getNotifyResult

    override fun getNotify() {
        viewModelScope.launch {
            notificationUseCase().collect {
                _getNotifyResult.value = it
            }
        }
    }

    override fun readNotification(idNotify: Int) {
        viewModelScope.launch {
            readNotificationUseCase(idNotify).first()
        }
    }
}