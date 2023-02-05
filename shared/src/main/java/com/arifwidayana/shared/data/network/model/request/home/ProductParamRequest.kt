package com.arifwidayana.shared.data.network.model.request.home

data class ProductParamRequest(
    val page: Int,
    val perPage: Int,
    val categoryId: Int
)
