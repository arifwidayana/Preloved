package com.arifwidayana.shared.data.network.model.response.sale.order

import android.text.Spannable

data class SaleOrderParamResponse(
    val id: Int,
    val productId: Int,
    val userOfferName: String,
    val userOfferImageUrl: String,
    val userPhoneNumber: String,
    val userOfferLocation: String,
    val price: String,
    val transactionDate: String,
    val productName: String,
    val basePrice: Spannable,
    val imageProductUrl: String,
    val status: String
)