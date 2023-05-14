package com.arifwidayana.account.presentation.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.account.domain.profile.UpdateProfileUseCase
import com.arifwidayana.account.domain.profile.UploadImageProfileUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.request.account.profile.ProfileUserParamRequest
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import com.arifwidayana.shared.domain.UserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File

class ProfileViewModel(
    private val userUseCase: UserUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val uploadImageProfileUseCase: UploadImageProfileUseCase
) : ProfileContract, ViewModel() {
    private val _getUserResult = MutableStateFlow<ViewResource<UserParamResponse>>(ViewResource.Empty())
    private val _updateProfileResult = MutableStateFlow<ViewResource<Int>>(ViewResource.Empty())
    private val _uploadImageResult = MutableStateFlow<ViewResource<String>>(ViewResource.Empty())
    override val getUserResult: StateFlow<ViewResource<UserParamResponse>> = _getUserResult
    override val updateProfileResult: StateFlow<ViewResource<Int>> = _updateProfileResult
    override val uploadImageResult: StateFlow<ViewResource<String>> = _uploadImageResult

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

    override fun uploadImage(image: File?) {
        viewModelScope.launch {
            uploadImageProfileUseCase(image).collect {
                _uploadImageResult.value = it
            }
        }
    }
}