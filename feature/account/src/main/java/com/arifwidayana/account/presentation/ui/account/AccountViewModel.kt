package com.arifwidayana.account.presentation.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import com.arifwidayana.shared.domain.DeleteUserTokenUseCase
import com.arifwidayana.shared.domain.UserUseCase
import com.arifwidayana.shared.domain.ValidateUserTokenUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AccountViewModel(
    private val userUseCase: UserUseCase,
    private val deleteUserTokenUseCase: DeleteUserTokenUseCase,
    private val validateUserTokenUseCase: ValidateUserTokenUseCase
) : AccountContract, ViewModel() {
    private val _getUserResult = MutableStateFlow<ViewResource<UserParamResponse>>(ViewResource.Empty())
    private val _logoutUserResult = MutableStateFlow<ViewResource<String>>(ViewResource.Empty())
    private val _validateTokenUserResult = MutableStateFlow<ViewResource<Boolean>>(ViewResource.Empty())
    override val getUserResult: StateFlow<ViewResource<UserParamResponse>> = _getUserResult
    override val logoutUserResult: StateFlow<ViewResource<String>> = _logoutUserResult
    override val validateTokenUserResult: StateFlow<ViewResource<Boolean>> = _validateTokenUserResult

    override fun getUser() {
        viewModelScope.launch {
            validateUserTokenUseCase().collect { status ->
                when (status.payload) {
                    true -> {
                        userUseCase().collect {
                            _getUserResult.value = it
                        }
                    }
                    else -> {
                        _validateTokenUserResult.emit(status)
                    }
                }
            }
        }
    }

    override fun logoutUser() {
        viewModelScope.launch {
            deleteUserTokenUseCase().collect {
                _logoutUserResult.value = it
            }
        }
    }
}