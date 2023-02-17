package com.arifwidayana.register.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.utils.FieldErrorException
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.request.auth.register.RegisterParamRequest
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.StringUtils
import com.arifwidayana.style.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias FieldRegisterResult = List<Pair<Int, Int>>

class RegisterFieldValidationUseCase(
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<RegisterParamRequest, FieldRegisterResult>(coroutineDispatcher) {
    override suspend fun execute(param: RegisterParamRequest?): Flow<ViewResource<FieldRegisterResult>> = flow {
        param?.let { res ->
            val result = mutableListOf<Pair<Int, Int>>()
            isFullNameValid(res.fullName)?.let {
                result.add(it)
            }
            isEmailValid(res.email)?.let {
                result.add(it)
            }
            isPasswordValid(res.password)?.let {
                result.add(it)
            }
            isConfirmPasswordValid(res.password, res.confirmPassword)?.let {
                result.add(it)
            }
            isPhoneNumberValid(res.phoneNumber)?.let {
                result.add(it)
            }
            isAddressValid(res.address)?.let {
                result.add(it)
            }
            isCityValid(res.city)?.let {
                result.add(it)
            }
            when {
                result.isEmpty() -> emit(ViewResource.Success(result))
                else -> emit(ViewResource.Error(FieldErrorException(result)))
            }
        } ?: throw IllegalStateException("Param Required")
    }

    private fun isFullNameValid(fullName: String): Pair<Int, Int>? {
        return when {
            fullName.isEmpty() -> Pair(Constant.REGISTER_FULLNAME_FIELD, R.string.error_field_fullname_empty)
            else -> null
        }
    }

    private fun isEmailValid(email: String): Pair<Int, Int>? {
        return when {
            email.isEmpty() -> Pair(Constant.REGISTER_EMAIL_FIELD, R.string.error_field_email_empty)
            !(StringUtils.isEmailValid(email) ?: true) -> Pair(Constant.REGISTER_EMAIL_FIELD, R.string.error_field_email_not_valid)
            else -> null
        }
    }

    private fun isPasswordValid(password: String): Pair<Int, Int>? {
        return when {
            password.isEmpty() -> Pair(Constant.REGISTER_PASSWORD_FIELD, R.string.error_field_password_empty)
            password.length < 6 -> Pair(Constant.REGISTER_PASSWORD_FIELD, R.string.error_field_password_length_below_min)
            password.contains(" ") -> Pair(Constant.REGISTER_PASSWORD_FIELD, R.string.error_field_password_contains_space)
            else -> null
        }
    }

    private fun isConfirmPasswordValid(password: String, cPassword: String): Pair<Int, Int>? {
        return when {
            cPassword.isEmpty() -> Pair(Constant.REGISTER_CONFIRM_PASSWORD_FIELD, R.string.error_field_confirm_password_empty)
            cPassword != password -> Pair(Constant.REGISTER_CONFIRM_PASSWORD_FIELD, R.string.error_field_confirm_password_not_same)
            else -> null
        }
    }

    private fun isPhoneNumberValid(phoneNumber: String): Pair<Int, Int>? {
        return when {
            phoneNumber.isEmpty() -> Pair(Constant.REGISTER_PHONE_FIELD, R.string.error_field_phone_empty)
            phoneNumber.length < 10 -> Pair(Constant.REGISTER_PHONE_FIELD, R.string.error_field_phone_length_below_min)
            else -> null
        }
    }

    private fun isAddressValid(address: String): Pair<Int, Int>? {
        return when {
            address.isEmpty() -> Pair(Constant.REGISTER_ADDRESS_FIELD, R.string.error_field_address_empty)
            else -> null
        }
    }

    private fun isCityValid(city: String): Pair<Int, Int>? {
        return when {
            city.isEmpty() -> Pair(Constant.REGISTER_CITY_FIELD, R.string.error_field_city_empty)
            else -> null
        }
    }
}