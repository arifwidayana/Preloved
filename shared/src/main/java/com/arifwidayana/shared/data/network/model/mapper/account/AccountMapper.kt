package com.arifwidayana.shared.data.network.model.mapper.account

import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import com.arifwidayana.shared.data.network.model.response.account.UserResponse
import com.arifwidayana.shared.utils.mapper.ViewParamMapper

object AccountMapper : ViewParamMapper<UserResponse, UserParamResponse> {
    override fun toViewParam(dataObject: UserResponse?): UserParamResponse =
        UserParamResponse(
            id = dataObject?.id ?: 0,
            fullName = dataObject?.fullName.orEmpty(),
            email = dataObject?.email.orEmpty(),
            password = dataObject?.password.orEmpty(),
            phoneNumber = dataObject?.phoneNumber.orEmpty(),
            address = dataObject?.address.orEmpty(),
            city = dataObject?.city.orEmpty(),
            imageUrl = dataObject?.imageUrl.orEmpty(),
            createdAt = dataObject?.createdAt.orEmpty(),
            updatedAt = dataObject?.updatedAt.orEmpty()
        )
}