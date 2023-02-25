package com.arifwidayana.shared.data.network.model.response.bid.order

data class OrderProductParamResponse(
    val id: Int,
    val productId: Int,
    val status: String,
    val state: Boolean
)