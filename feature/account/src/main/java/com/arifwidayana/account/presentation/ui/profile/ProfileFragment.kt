package com.arifwidayana.account.presentation.ui.profile

import android.app.Activity
import android.os.Environment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import coil.size.Scale
import com.arifwidayana.account.databinding.FragmentProfileBinding
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.core.utils.FieldErrorException
import com.arifwidayana.shared.data.network.model.request.account.profile.ProfileUserParamRequest
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.ext.changed
import com.arifwidayana.shared.utils.ext.source
import com.arifwidayana.shared.utils.ext.uriToFile
import com.arifwidayana.style.R
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.io.File

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>(
    FragmentProfileBinding::inflate
) {
    override val viewModel: ProfileViewModel by inject()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModel.getUser()
        resetErrorField()
    }

    private fun onClick() {
        binding.apply {
            btnBack.setOnClickListener {
                moveNavigateUp()
            }
            sivUploadImage.setOnClickListener {
                pickImage()
            }
            btnSave.setOnClickListener {
                viewModel.updateProfile(
                    ProfileUserParamRequest(
                        fullName = etFullName.text.toString(),
                        phoneNumber = etPhone.text.toString(),
                        address = etAddress.text.toString(),
                        city = etCity.text.toString()
                    )
                )
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.getUserResult.collect {
                        it.source(
                            doOnSuccess = { result ->
                                setStateView(result.payload)
                            },
                            doOnError = { error ->
                                showMessageSnackBar(true, exception = error.exception)
                            }
                        )
                    }
                }
                launch {
                    viewModel.uploadImageResult.collect {
                        it.source(
                            doOnSuccess = { result ->
                                setStateImage(result.payload)
                            },
                            doOnError = { error ->
                                showMessageSnackBar(true, exception = error.exception)
                            }
                        )
                    }
                }
                launch {
                    viewModel.updateProfileResult.collect {
                        it.source(
                            doOnSuccess = { result ->
                                result.payload?.let { message ->
                                    showMessageSnackBar(true, getString(message))
                                }
                            },
                            doOnError = { error ->
                                when (error.exception) {
                                    is FieldErrorException -> handleStateErrorField(error.exception as FieldErrorException)
                                    else -> showMessageSnackBar(true, exception = error.exception)
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    private fun setStateImage(data: String?) {
        binding.sivProfile.load(data) {
            scale(Scale.FILL)
        }
    }

    private fun setStateView(data: UserParamResponse?) {
        binding.apply {
            sivProfile.load(data?.imageUrl) {
                placeholder(R.drawable.ic_profile)
                scale(Scale.FILL)
            }
            etFullName.setText(data?.fullName)
            etEmail.setText(data?.email)
            etPhone.setText(data?.phoneNumber)
            etCity.setText(data?.city)
            etAddress.setText(data?.address)
        }
    }

    private fun resetErrorField() {
        binding.apply {
            etFullName.changed(onTextChanged = {
                tilFullName.isErrorEnabled = false
            })
            etPhone.changed(onTextChanged = {
                tilPhone.isErrorEnabled = false
            })
            etAddress.changed(onTextChanged = {
                tilAddress.isErrorEnabled = false
            })
            etCity.changed(onTextChanged = {
                tilCity.isErrorEnabled = false
            })
        }
    }

    private fun handleStateErrorField(fieldErrorException: FieldErrorException) {
        binding.apply {
            fieldErrorException.apply {
                errorFields.forEach {
                    if (it.first == Constant.PROFILE_FULLNAME_FIELD) {
                        tilFullName.error = getString(it.second)
                    }
                    if (it.first == Constant.PROFILE_PHONE_NUMBER_FIELD) {
                        tilPhone.error = getString(it.second)
                    }
                    if (it.first == Constant.PROFILE_ADDRESS_FIELD) {
                        tilAddress.error = getString(it.second)
                    }
                    if (it.first == Constant.PROFILE_CITY_FIELD) {
                        tilCity.error = getString(it.second)
                    }
                }
            }
        }
    }

    private fun pickImage() {
        requireContext().getExternalFilesDir(Environment.DIRECTORY_DCIM)?.let {
            ImagePicker.with(this@ProfileFragment)
                .crop()
                .saveDir(File(requireContext().externalCacheDir, "image"))
                .galleryMimeTypes(
                    mimeTypes = arrayOf(
                        "image/png",
                        "image/jpg",
                        "image/jpeg"
                    )
                )
                .compress(1024)
                .maxResultSize(1080, 1080)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    result.data?.data?.let {
                        viewModel.uploadImage(uriToFile(requireContext(), it))
                    }
                }
                ImagePicker.RESULT_ERROR -> {
                    showMessageToast(true, ImagePicker.getError(result.data))
                }
                else -> {
                    showMessageToast(
                        false,
                        getString(R.string.task_cancelled)
                    )
                }
            }
        }
}