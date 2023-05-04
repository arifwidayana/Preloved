package com.arifwidayana.shared.data.network.model.response.sale.product

data class SaleProductParamResponse(
    val id: Int,
    val name: String,
    val basePrice: String,
    val imageUrl: String,
    val categories: String
)