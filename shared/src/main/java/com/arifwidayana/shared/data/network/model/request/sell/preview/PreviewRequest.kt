package com.arifwidayana.shared.data.network.model.request.sell.preview

import android.os.Parcelable
import com.arifwidayana.shared.data.network.model.request.sell.SellParamRequest
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class PreviewRequest(
    val sellParamRequest: SellParamRequest,
    val userParamResponse: UserParamResponse
) : Parcelable
