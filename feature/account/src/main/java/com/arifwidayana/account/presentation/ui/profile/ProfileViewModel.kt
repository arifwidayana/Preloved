package com.arifwidayana.account.presentation.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.account.domain.UserUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userUseCase: UserUseCase
) : ProfileContract, ViewModel() {
    private val _getUserResult = MutableStateFlow<ViewResource<UserParamResponse>>(ViewResource.Empty())
    override val getUserResult: StateFlow<ViewResource<UserParamResponse>> = _getUserResult

    override fun getUser() {
        viewModelScope.launch {
            userUseCase().collect {
                _getUserResult.value = it
            }
        }
    }
}