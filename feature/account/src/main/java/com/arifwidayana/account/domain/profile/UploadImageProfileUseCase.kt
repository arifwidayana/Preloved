package com.arifwidayana.account.domain.profile

import com.arifwidayana.account.data.repository.AccountRepository
import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class UploadImageProfileUseCase(
    private val accountRepository: AccountRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<File, String>(coroutineDispatcher) {
    override suspend fun execute(param: File?): Flow<ViewResource<String>> = flow {
        emit(ViewResource.Loading())
        val requestImage = MultipartBody.Builder()
        param?.let {
            requestImage.setType(MultipartBody.FORM)
                .addFormDataPart("image", it.name, it.asRequestBody("image/jpg".toMediaType()))
        }
        accountRepository.uploadImageProfile(requestImage.build()).collect {
            it.suspendSource(
                doOnSuccess = { source ->
                    emit(ViewResource.Success(source.payload?.imageUrl.orEmpty()))
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }
}