package com.arifwidayana.notification.data.network.datasource

import com.arifwidayana.notification.data.network.service.NotificationService
import com.arifwidayana.shared.data.network.model.response.notification.NotificationResponse

interface NotificationDatasource {
    suspend fun getNotification(): List<NotificationResponse>
    suspend fun readNotification(idNotify: Int): List<NotificationResponse>
}

class NotificationDatasourceImpl(
    private val notificationService: NotificationService
) : NotificationDatasource {
    override suspend fun getNotification(): List<NotificationResponse> {
        return notificationService.getNotification()
    }

    override suspend fun readNotification(idNotify: Int): List<NotificationResponse> {
        return notificationService.readNotification(idNotify)
    }
}