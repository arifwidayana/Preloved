package com.arifwidayana.shared.data.network.model.mapper.auth.login

import com.arifwidayana.shared.data.network.model.request.auth.login.LoginParamRequest
import com.arifwidayana.shared.data.network.model.request.auth.login.LoginRequest
import com.arifwidayana.shared.data.network.model.response.auth.login.LoginParamResponse
import com.arifwidayana.shared.data.network.model.response.auth.login.LoginResponse
import com.arifwidayana.shared.utils.mapper.DataObjectMapper
import com.arifwidayana.shared.utils.mapper.ViewParamMapper

object LoginRequestMapper : DataObjectMapper<LoginRequest, LoginParamRequest> {
    override fun toDataObject(viewParam: LoginParamRequest?): LoginRequest =
        LoginRequest(
            email = viewParam?.email,
            password = viewParam?.password
        )
}

object LoginResponseMapper : ViewParamMapper<LoginResponse, LoginParamResponse> {
    override fun toViewParam(dataObject: LoginResponse?): LoginParamResponse =
        LoginParamResponse(
            id = dataObject?.id ?: 0,
            name = dataObject?.name.orEmpty(),
            email = dataObject?.email.orEmpty(),
            accessToken = dataObject?.accessToken.orEmpty()
        )
}