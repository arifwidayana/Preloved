package com.arifwidayana.shared.data.network.model.mapper.auth.register

import com.arifwidayana.shared.data.network.model.request.auth.register.RegisterParamRequest
import com.arifwidayana.shared.data.network.model.request.auth.register.RegisterRequest
import com.arifwidayana.shared.data.network.model.response.auth.register.RegisterParamResponse
import com.arifwidayana.shared.data.network.model.response.auth.register.RegisterResponse
import com.arifwidayana.shared.utils.mapper.DataObjectMapper
import com.arifwidayana.shared.utils.mapper.ViewParamMapper

object RegisterRequestMapper : DataObjectMapper<RegisterRequest, RegisterParamRequest> {
    override fun toDataObject(viewParam: RegisterParamRequest?): RegisterRequest =
        RegisterRequest(
            fullName = viewParam?.fullName,
            email = viewParam?.email,
            password = viewParam?.confirmPassword,
            phoneNumber = viewParam?.phoneNumber,
            address = viewParam?.address,
            city = viewParam?.city
        )
}

object RegisterResponseMapper : ViewParamMapper<RegisterResponse, RegisterParamResponse> {
    override fun toViewParam(dataObject: RegisterResponse?): RegisterParamResponse =
        RegisterParamResponse(
            id = dataObject?.id ?: 0,
            fullName = dataObject?.fullName.orEmpty(),
            email = dataObject?.email.orEmpty(),
            password = dataObject?.password.orEmpty(),
            phoneNumber = dataObject?.phoneNumber.orEmpty(),
            address = dataObject?.address.orEmpty(),
            imageUrl = dataObject?.imageUrl.orEmpty(),
            city = dataObject?.city.orEmpty(),
            updatedAt = dataObject?.updatedAt.orEmpty(),
            createdAt = dataObject?.createdAt.orEmpty()
        )
}