package com.arifwidayana.shared.data.local.model.request

import java.util.*

data class SearchHistoryRequest(
    val searchName: String,
    val createdAt: Date,
    val updatedAt: Date
)
