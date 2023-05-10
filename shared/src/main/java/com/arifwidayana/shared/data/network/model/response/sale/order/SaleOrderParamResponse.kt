package com.arifwidayana.shared.data.network.model.response.sale.order

import android.text.Spannable

data class SaleOrderParamResponse(
    val id: Int,
    val productId: Int,
    val price: String,
    val transactionDate: String,
    val productName: String,
    val basePrice: Spannable,
    val imageProduct: String,
    val status: String
)