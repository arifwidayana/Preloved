package com.arifwidayana.account.presentation.ui.profile

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.request.account.profile.ProfileUserParamRequest
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import kotlinx.coroutines.flow.StateFlow
import java.io.File

interface ProfileContract {
    val getUserResult: StateFlow<ViewResource<UserParamResponse>>
    val updateProfileResult: StateFlow<ViewResource<Int>>
    val uploadImageResult: StateFlow<ViewResource<String>>
    fun getUser()
    fun updateProfile(profileUserParamRequest: ProfileUserParamRequest)
    fun uploadImage(image: File?)
}