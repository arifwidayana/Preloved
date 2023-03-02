package com.arifwidayana.account.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.utils.FieldErrorException
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.request.account.password.PasswordParamRequest
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.style.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias FieldPasswordResult = List<Pair<Int, Int>>

class PasswordFieldValidationUseCase(
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<PasswordParamRequest, FieldPasswordResult>(coroutineDispatcher) {
    override suspend fun execute(param: PasswordParamRequest?): Flow<ViewResource<FieldPasswordResult>> = flow {
        param?.let { res ->
            val result = mutableListOf<Pair<Int, Int>>()
            isCurrentPasswordValid(res.currentPassword)?.let {
                result.add(it)
            }
            isNewPasswordValid(res.newPassword)?.let {
                result.add(it)
            }
            isConfirmNewPasswordValid(res.newPassword, res.confirmPassword)?.let {
                result.add(it)
            }
            when {
                result.isEmpty() -> emit(ViewResource.Success(result))
                else -> emit(ViewResource.Error(FieldErrorException(result)))
            }
        } ?: throw IllegalArgumentException("Param required")
    }

    private fun isCurrentPasswordValid(currentPassword: String): Pair<Int, Int>? {
        return when {
            currentPassword.isEmpty() -> Pair(Constant.PASSWORD_CURRENT_FIELD, R.string.error_field_current_password_empty)
            else -> null
        }
    }

    private fun isNewPasswordValid(newPassword: String): Pair<Int, Int>? {
        return when {
            newPassword.isEmpty() -> Pair(Constant.PASSWORD_NEW_FIELD, R.string.error_field_new_password_empty)
            newPassword.length < 6 -> Pair(Constant.PASSWORD_NEW_FIELD, R.string.error_field_new_password_length_below_min)
            newPassword.contains(" ") -> Pair(Constant.PASSWORD_NEW_FIELD, R.string.error_field_new_password_contains_space)
            else -> null
        }
    }

    private fun isConfirmNewPasswordValid(newPassword: String, confirmPassword: String): Pair<Int, Int>? {
        return when {
            confirmPassword.isEmpty() -> Pair(Constant.PASSWORD_CONFIRM_NEW_FIELD, R.string.error_field_confirm_new_password_empty)
            confirmPassword != newPassword -> Pair(Constant.PASSWORD_CONFIRM_NEW_FIELD, R.string.error_field_confirm_new_password_not_same)
            else -> null
        }
    }
}