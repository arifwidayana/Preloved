package com.arifwidayana.login.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.utils.FieldErrorException
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.request.auth.login.LoginParamRequest
import com.arifwidayana.shared.utils.Constant.LOGIN_EMAIL_FIELD
import com.arifwidayana.shared.utils.Constant.LOGIN_PASSWORD_FIELD
import com.arifwidayana.shared.utils.StringUtils
import com.arifwidayana.style.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias FieldLoginResult = List<Pair<Int, Int>>

class LoginFieldValidationUseCase(
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<LoginParamRequest, FieldLoginResult>(coroutineDispatcher) {
    override suspend fun execute(param: LoginParamRequest?): Flow<ViewResource<FieldLoginResult>> = flow {
        param?.let { res ->
            val result = mutableListOf<Pair<Int, Int>>()
            isEmailValid(res.email)?.let {
                result.add(it)
            }
            isPasswordValid(res.password)?.let {
                result.add(it)
            }
            when {
                result.isEmpty() -> emit(ViewResource.Success(result))
                else -> emit(ViewResource.Error(FieldErrorException(result)))
            }
        } ?: throw IllegalStateException("Param Required")
    }

    private fun isEmailValid(email: String): Pair<Int, Int>? {
        return when {
            email.isEmpty() -> Pair(LOGIN_EMAIL_FIELD, R.string.error_field_email_empty)
            !(StringUtils.isEmailValid(email) ?: true) -> Pair(LOGIN_EMAIL_FIELD, R.string.error_field_email_not_valid)
            else -> null
        }
    }

    private fun isPasswordValid(password: String): Pair<Int, Int>? {
        return when {
            password.isEmpty() -> Pair(LOGIN_PASSWORD_FIELD, R.string.error_field_password_empty)
            password.length < 6 -> Pair(LOGIN_PASSWORD_FIELD, R.string.error_field_password_length_below_min)
            password.contains(" ") -> Pair(LOGIN_PASSWORD_FIELD, R.string.error_field_password_contains_space)
            else -> null
        }
    }
}