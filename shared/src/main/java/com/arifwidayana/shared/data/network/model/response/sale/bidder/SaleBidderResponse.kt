package com.arifwidayana.shared.data.network.model.response.sale.bidder

import com.google.gson.annotations.SerializedName

data class SaleBidderResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("product_id")
    val productId: Int?,
    @SerializedName("buyer_id")
    val buyerId: Int?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("transaction_date")
    val transactionDate: String?,
    @SerializedName("product_name")
    val productName: String?,
    @SerializedName("base_price")
    val basePrice: Int?,
    @SerializedName("image_product")
    val imageProduct: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)