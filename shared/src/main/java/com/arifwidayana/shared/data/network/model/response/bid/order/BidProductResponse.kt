package com.arifwidayana.shared.data.network.model.response.bid.order

import com.google.gson.annotations.SerializedName

data class BidProductResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("buyer_id")
    val buyerId: Int?,
    @SerializedName("product_id")
    val productId: Int?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("product_name")
    val productName: String?,
    @SerializedName("base_price")
    val basePrice: Int?,
    @SerializedName("image_product")
    val imageProduct: String?,
    @SerializedName("transaction_date")
    val transactionDate: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("status")
    val status: String?
)