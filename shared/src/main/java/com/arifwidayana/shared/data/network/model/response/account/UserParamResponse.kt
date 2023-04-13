package com.arifwidayana.shared.data.network.model.response.account

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserParamResponse(
    val id: Int,
    val fullName: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val address: String,
    val imageUrl: String,
    val city: String,
    val createdAt: String,
    val updatedAt: String
) : Parcelable