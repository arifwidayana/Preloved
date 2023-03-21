package com.arifwidayana.shared.data.network.model.response.notification

import android.text.Spannable

data class NotificationParamResponse(
    val id: Int,
    val productId: Int,
    val productName: String,
    val basePrice: Spannable,
    val bidPrice: String,
    val imageUrl: String,
    val transactionDate: String,
    val status: String,
    val statusProduct: String,
    val read: Boolean,
    val notificationType: String,
    val state: List<Pair<Int, Int>>
)