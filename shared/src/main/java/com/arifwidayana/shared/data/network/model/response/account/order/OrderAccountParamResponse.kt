package com.arifwidayana.shared.data.network.model.response.account.order

data class OrderAccountParamResponse(
    val id: Int,
    val productId: Int,
    val buyerId: Int,
    val price: String,
    val transactionDate: String,
    val productName: String,
    val basePrice: String,
    val imageProduct: String,
    val status: String,
    val product: Product,
    val user: User
) {
    data class Product(
        val name: String,
        val description: String,
        val basePrice: String,
        val imageUrl: String,
        val imageName: String,
        val location: String,
        val userId: Int,
        val status: String,
        val user: User
    ) {
        data class User(
            val id: Int,
            val fullName: String,
            val email: String,
            val phoneNumber: String,
            val address: String,
            val city: String
        )
    }

    data class User(
        val id: Int,
        val fullName: String,
        val email: String,
        val phoneNumber: String,
        val address: String,
        val city: String
    )
}