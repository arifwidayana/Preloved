package com.arifwidayana.shared.data.network.model.request.sell

import com.google.gson.annotations.SerializedName
import java.io.File

data class SellRequest(
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("base_price")
    val basePrice: Int?,
    @SerializedName("category_ids")
    val categoryId: List<Int>?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("image")
    val image: File?
)
