package com.arifwidayana.account.presentation.ui.profile

import androidx.lifecycle.lifecycleScope
import coil.load
import coil.size.Scale
import com.arifwidayana.style.R
import com.arifwidayana.account.databinding.FragmentProfileBinding
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.shared.data.network.model.request.account.profile.ProfileUserParamRequest
import com.arifwidayana.shared.data.network.model.response.account.UserParamResponse
import com.arifwidayana.shared.utils.ext.source
import org.koin.android.ext.android.inject

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
    }

    private fun onClick() {
        binding.apply {
            btnBack.setOnClickListener {
                moveNavigateUp()
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
        lifecycleScope.apply {
            launchWhenStarted {
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
            launchWhenStarted {
                viewModel.updateProfileResult.collect {
                    it.source(
                        doOnSuccess = { result ->
                            result.payload?.let { message ->
                                showMessageSnackBar(true, getString(message))
                            }
                        },
                        doOnError = { error ->
                            showMessageSnackBar(true, exception = error.exception)
                        }
                    )
                }
            }
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
}