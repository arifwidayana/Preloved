package com.arifwidayana.shared.data.network.model.response.account.wishlist

data class WishlistAccountParamResponse(
    val id: Int,
    val productId: Int,
    val userId: Int,
    val createdAt: String,
    val updatedAt: String,
    val product: Product
) {
    data class Product(
        val id: Int,
        val name: String,
        val description: String,
        val basePrice: String,
        val imageUrl: String,
        val imageName: String,
        val location: String,
        val userId: Int,
        val status: String,
        val categories: String
    )
}