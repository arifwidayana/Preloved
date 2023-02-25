package com.arifwidayana.shared.data.network.model.response.bid.order

import com.google.gson.annotations.SerializedName

data class OrderProductResponse(
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
    @SerializedName("Product")
    val product: Product?,
    @SerializedName("User")
    val user: User?
) {
    data class Product(
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
        @SerializedName("User")
        val user: User?
    ) {
        data class User(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("full_name")
            val fullName: String?,
            @SerializedName("email")
            val email: String?,
            @SerializedName("phone_number")
            val phoneNumber: String?,
            @SerializedName("address")
            val address: String?,
            @SerializedName("city")
            val city: String?
        )
    }

    data class User(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("full_name")
        val fullName: String?,
        @SerializedName("email")
        val email: String?,
        @SerializedName("phone_number")
        val phoneNumber: String?,
        @SerializedName("address")
        val address: String?,
        @SerializedName("city")
        val city: String?
    )
}