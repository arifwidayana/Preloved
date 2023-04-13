package com.arifwidayana.shared.data.network.model.request.sell.category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SellCategoryRequest(
    val id: Int,
    val name: String
) : Parcelable