package com.arifwidayana.shared.data.network.model.response.home.product

data class BuyerProductParamResponse(
    val id: Int,
    val name: String,
    val description: String,
    val basePrice: String,
    val imageUrl: String,
    val imageName: String,
    val location: String,
    val userId: Int,
    val status: String,
    val createdAt: String,
    val updatedAt: String,
    val categories: String,
    val user: User
) {
    data class User(
        val id: Int,
        val fullName: String,
        val email: String,
        val phoneNumber: String,
        val address: String,
        val imageUrl: String,
        val city: String
    )
}