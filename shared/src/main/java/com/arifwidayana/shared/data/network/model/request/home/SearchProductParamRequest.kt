package com.arifwidayana.shared.data.network.model.request.home

data class SearchProductParamRequest(
    val categoryId: Int = 0,
    val searchProduct: String = ""
)