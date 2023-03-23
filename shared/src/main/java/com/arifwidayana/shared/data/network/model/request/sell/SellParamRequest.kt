package com.arifwidayana.shared.data.network.model.request.sell

import java.io.File

data class SellParamRequest(
    val name: String,
    val description: String,
    val basePrice: Int,
    val categoryId: List<Int>,
    val location: String,
    val image: File
)
