package com.arifwidayana.shared.data.network.model.response.sale.sold

data class SaleProductSoldParamResponse(
    val id: Int,
    val name: String,
    val basePrice: String,
    val imageUrl: String,
    val updatedAt: String,
    val status: String,
    val categories: String
)