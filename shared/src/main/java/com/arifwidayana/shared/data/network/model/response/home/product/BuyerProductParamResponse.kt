package com.arifwidayana.shared.data.network.model.response.home.product

data class BuyerProductParamResponse(
    val id: Int,
    val name: String,
    val description: String,
    val basePrice: Int,
    val imageUrl: String,
    val imageName: String,
    val location: String,
    val userId: Int,
    val status: String,
    val createdAt: String,
    val updatedAt: String,
    val categories: List<Category>
) {
    data class Category(
        val id: Int,
        val name: String
    )
}