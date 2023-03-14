package com.arifwidayana.shared.data.network.model.request.account.profile

data class ProfileUserParamRequest(
    val fullName: String,
    val phoneNumber: String,
    val address: String,
    val city: String
)
