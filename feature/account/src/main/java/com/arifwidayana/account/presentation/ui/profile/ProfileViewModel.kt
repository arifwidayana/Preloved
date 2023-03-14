package com.arifwidayana.account.presentation.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.account.domain.UserUseCase
import com.arifwidayana.account.domain.profile.UpdateProfileUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.request.account.profile.ProfileUserParamRequest
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userUseCase: UserUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase
) : ProfileContract, ViewModel() {
    private val _getUserResult = MutableStateFlow<ViewResource<UserParamResponse>>(ViewResource.Empty())
    private val _updateProfileResult = MutableStateFlow<ViewResource<Int>>(ViewResource.Empty())
    override val getUserResult: StateFlow<ViewResource<UserParamResponse>> = _getUserResult
    override val updateProfileResult: StateFlow<ViewResource<Int>> = _updateProfileResult

    override fun getUser() {
        viewModelScope.launch {
            userUseCase().collect {
                _getUserResult.value = it
            }
        }
    }

    override fun updateProfile(profileUserParamRequest: ProfileUserParamRequest) {
        viewModelScope.launch {
            updateProfileUseCase(profileUserParamRequest).collect {
                _updateProfileResult.value = it
            }
        }
    }
}