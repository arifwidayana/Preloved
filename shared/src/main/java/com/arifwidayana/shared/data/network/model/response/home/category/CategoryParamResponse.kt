package com.arifwidayana.shared.data.network.model.response.home.category

class CategoryParamResponse : ArrayList<CategoryParamResponse.CategoryParamResponseItem>() {
    data class CategoryParamResponseItem(
        val id: Int,
        val name: String,
        val createdAt: String,
        val updatedAt: String
    )
}