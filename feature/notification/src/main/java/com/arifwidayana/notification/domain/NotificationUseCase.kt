package com.arifwidayana.notification.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.notification.data.network.repository.NotificationRepository
import com.arifwidayana.shared.data.network.model.mapper.notification.NotificationMapper
import com.arifwidayana.shared.data.network.model.response.notification.NotificationParamResponse
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias NotifyParamDataResource = List<NotificationParamResponse>

class NotificationUseCase(
    private val notificationRepository: NotificationRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, NotifyParamDataResource>(coroutineDispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<NotifyParamDataResource>> = flow {
        emit(ViewResource.Loading())
        notificationRepository.getNotification().collect {
            it.suspendSource(
                doOnSuccess = { source ->
                    emit(ViewResource.Success(NotificationMapper.toViewParam(source.payload)))
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }
}