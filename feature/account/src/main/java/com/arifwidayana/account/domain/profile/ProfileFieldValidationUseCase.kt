package com.arifwidayana.account.domain.profile

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.utils.FieldErrorException
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.request.account.profile.ProfileUserParamRequest
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.style.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias FieldUpdateProfileResult = List<Pair<Int, Int>>

class ProfileFieldValidationUseCase(
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<ProfileUserParamRequest, FieldUpdateProfileResult>(coroutineDispatcher) {
    override suspend fun execute(param: ProfileUserParamRequest?): Flow<ViewResource<FieldUpdateProfileResult>> = flow {
        param?.let { res ->
            val result = mutableListOf<Pair<Int, Int>>()
            isFullNameValid(res.fullName)?.let {
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
        } ?: throw IllegalStateException("Param required")
    }

    private fun isFullNameValid(fullName: String): Pair<Int, Int>? {
        return when {
            fullName.isEmpty() -> Pair(Constant.PROFILE_FULLNAME_FIELD, R.string.error_field_fullname_empty)
            else -> null
        }
    }

    private fun isPhoneNumberValid(phoneNumber: String): Pair<Int, Int>? {
        return when {
            phoneNumber.isEmpty() -> Pair(Constant.PROFILE_PHONE_NUMBER_FIELD, R.string.error_field_phone_empty)
            phoneNumber.length < 10 -> Pair(Constant.PROFILE_PHONE_NUMBER_FIELD, R.string.error_field_phone_length_below_min)
            else -> null
        }
    }

    private fun isAddressValid(address: String): Pair<Int, Int>? {
        return when {
            address.isEmpty() -> Pair(Constant.PROFILE_ADDRESS_FIELD, R.string.error_field_address_empty)
            else -> null
        }
    }

    private fun isCityValid(city: String): Pair<Int, Int>? {
        return when {
            city.isEmpty() -> Pair(Constant.PROFILE_CITY_FIELD, R.string.error_field_city_empty)
            else -> null
        }
    }
}