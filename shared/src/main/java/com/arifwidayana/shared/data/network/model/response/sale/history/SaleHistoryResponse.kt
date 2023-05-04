package com.arifwidayana.shared.data.network.model.response.sale.history

import com.google.gson.annotations.SerializedName

data class SaleHistoryResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("product_name")
    val productName: String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("transaction_date")
    val transactionDate: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("user_id")
    val userId: Int?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("product_id")
    val productId: Int?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("Product")
    val product: Product?
) {
    data class Product(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("base_price")
        val basePrice: Int?,
        @SerializedName("image_url")
        val imageUrl: String?,
        @SerializedName("image_name")
        val imageName: String?,
        @SerializedName("location")
        val location: String?,
        @SerializedName("user_id")
        val userId: Int?,
        @SerializedName("status")
        val status: String?,
        @SerializedName("createdAt")
        val createdAt: String?,
        @SerializedName("updatedAt")
        val updatedAt: String?
    )
}