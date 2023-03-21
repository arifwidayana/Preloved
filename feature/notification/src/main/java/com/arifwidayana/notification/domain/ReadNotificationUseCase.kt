package com.arifwidayana.notification.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.notification.data.network.repository.NotificationRepository
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class ReadNotificationUseCase(
    private val notificationRepository: NotificationRepository,
    private val notificationUseCase: NotificationUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Int, Nothing>(coroutineDispatcher) {
    override suspend fun execute(param: Int?): Flow<ViewResource<Nothing>> = flow {
        emit(ViewResource.Loading())
        param?.let {
            notificationUseCase().first().suspendSource(
                doOnSuccess = { source ->
                    source.payload?.map { map ->
                        if (!map.read) {
                            notificationRepository.readNotification(it)
                        }
                    }
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }
}