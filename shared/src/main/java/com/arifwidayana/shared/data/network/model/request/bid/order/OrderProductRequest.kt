package com.arifwidayana.shared.data.network.model.request.bid.order

import com.google.gson.annotations.SerializedName

data class OrderProductRequest(
    @SerializedName("bid_price")
    val bidPrice: Int?
)