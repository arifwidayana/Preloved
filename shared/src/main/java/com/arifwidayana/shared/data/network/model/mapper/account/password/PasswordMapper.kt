package com.arifwidayana.shared.data.network.model.mapper.account.password

import com.arifwidayana.shared.data.network.model.request.account.password.PasswordParamRequest
import com.arifwidayana.shared.data.network.model.request.account.password.PasswordRequest
import com.arifwidayana.shared.utils.mapper.DataObjectMapper

object PasswordMapper : DataObjectMapper<PasswordRequest, PasswordParamRequest> {
    override fun toDataObject(viewParam: PasswordParamRequest?): PasswordRequest =
        PasswordRequest(
            currentPassword = viewParam?.currentPassword,
            newPassword = viewParam?.newPassword,
            confirmPassword = viewParam?.confirmPassword
        )
}