package com.arifwidayana.shared.data.network.model.mapper.account.profile

import com.arifwidayana.shared.data.network.model.request.account.profile.ProfileUserParamRequest
import com.arifwidayana.shared.data.network.model.request.account.profile.ProfileUserRequest
import com.arifwidayana.shared.utils.mapper.DataObjectMapper

object ProfileRequestMapper : DataObjectMapper<ProfileUserRequest, ProfileUserParamRequest> {
    override fun toDataObject(viewParam: ProfileUserParamRequest?): ProfileUserRequest =
        ProfileUserRequest(
            fullName = viewParam?.fullName,
            phoneNumber = viewParam?.phoneNumber,
            address = viewParam?.address,
            city = viewParam?.city
        )
}