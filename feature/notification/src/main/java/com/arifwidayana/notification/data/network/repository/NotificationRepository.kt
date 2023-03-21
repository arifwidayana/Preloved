package com.arifwidayana.notification.data.network.repository

import com.arifwidayana.core.wrapper.DataResource
import com.arifwidayana.notification.data.network.datasource.NotificationDatasource
import com.arifwidayana.shared.data.network.model.response.notification.NotificationResponse
import com.arifwidayana.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias NotificationDataResource = DataResource<List<NotificationResponse>>

interface NotificationRepository {
    suspend fun getNotification(): Flow<NotificationDataResource>
    suspend fun readNotification(idNotify: Int): Flow<NotificationDataResource>
}

class NotificationRepositoryImpl(
    private val notificationDatasource: NotificationDatasource
) : NotificationRepository, Repository() {
    override suspend fun getNotification(): Flow<NotificationDataResource> = flow {
        emit(safeNetworkCall { notificationDatasource.getNotification() })
    }

    override suspend fun readNotification(idNotify: Int): Flow<NotificationDataResource> = flow {
        emit(safeNetworkCall { notificationDatasource.readNotification(idNotify) })
    }
}