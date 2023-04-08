package com.arifwidayana.shared.data.network.model.request.sell

import android.os.Parcelable
import com.arifwidayana.shared.data.network.model.request.sell.category.SellCategoryRequest
import kotlinx.parcelize.Parcelize
import java.io.File

@Parcelize
data class SellParamRequest(
    val name: String,
    val description: String,
    val basePrice: Int,
    val categoryId: List<SellCategoryRequest>,
    val location: String,
    val image: File?
) : Parcelable
