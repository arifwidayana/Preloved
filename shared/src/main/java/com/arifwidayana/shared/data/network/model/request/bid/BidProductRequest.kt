package com.arifwidayana.shared.data.network.model.request.bid

import com.google.gson.annotations.SerializedName

data class BidProductRequest(
    @SerializedName("product_id")
    val productId: Int?,
    @SerializedName("bid_price")
    val bidPrice: Int?
)