package com.arifwidayana.account.presentation.ui.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.account.domain.password.UpdatePasswordUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.request.account.password.PasswordParamRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PasswordViewModel(
    private val updatePasswordUseCase: UpdatePasswordUseCase
) : PasswordContract, ViewModel() {
    private val _updatePasswordResult = MutableStateFlow<ViewResource<String>>(ViewResource.Empty())
    override val updatePasswordResult: StateFlow<ViewResource<String>> = _updatePasswordResult

    override fun updatePassword(passwordParamRequest: PasswordParamRequest) {
        viewModelScope.launch {
            updatePasswordUseCase(passwordParamRequest).collect {
                _updatePasswordResult.value = it
            }
        }
    }
}