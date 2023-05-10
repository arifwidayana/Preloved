package com.arifwidayana.shared.data.network.model.response.sale.history

import android.text.Spannable

data class SaleHistoryParamResponse(
    val productName: String,
    val price: String,
    val transactionDate: String,
    val imageUrl: String,
    val product: Product,
    val status: Pair<Int, Int>
) {
    data class Product(
        val id: Int,
        val basePrice: Spannable,
        val status: String
    )
}