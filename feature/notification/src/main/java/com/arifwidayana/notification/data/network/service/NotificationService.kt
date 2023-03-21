package com.arifwidayana.notification.data.network.service

import com.arifwidayana.core.BuildConfig
import com.arifwidayana.shared.data.network.model.response.notification.NotificationResponse
import com.arifwidayana.shared.utils.Constant.ID_PATH
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface NotificationService {
    @GET(BuildConfig.END_POINT_NOTIFICATION)
    suspend fun getNotification(): List<NotificationResponse>

    @PATCH(BuildConfig.END_POINT_NOTIFICATION)
    suspend fun readNotification(@Path(ID_PATH) idNotify: Int): List<NotificationResponse>
}