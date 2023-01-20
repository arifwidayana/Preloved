package com.arifwidayana.shared.data.network.model.response.home.category

import com.google.gson.annotations.SerializedName

class CategoryResponse : ArrayList<CategoryResponse.CategoryResponseItem>() {
    data class CategoryResponseItem(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("createdAt")
        val createdAt: String?,
        @SerializedName("updatedAt")
        val updatedAt: String?
    )
}