package com.arifwidayana.shared.data.network.model.request.account.profile

import com.google.gson.annotations.SerializedName

data class ProfileUserRequest(
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("phone_number")
    val phoneNumber: String?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("city")
    val city: String?
)