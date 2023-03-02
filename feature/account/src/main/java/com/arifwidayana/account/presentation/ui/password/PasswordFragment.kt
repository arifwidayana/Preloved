package com.arifwidayana.account.presentation.ui.password

import androidx.lifecycle.lifecycleScope
import com.arifwidayana.account.databinding.FragmentPasswordBinding
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.core.utils.FieldErrorException
import com.arifwidayana.shared.data.network.model.request.account.password.PasswordParamRequest
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.ext.changed
import com.arifwidayana.shared.utils.ext.source
import org.koin.android.ext.android.inject

class PasswordFragment : BaseFragment<FragmentPasswordBinding, PasswordViewModel>(
    FragmentPasswordBinding::inflate
) {
    override val viewModel: PasswordViewModel by inject()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        resetErrorField()
    }

    private fun onClick() {
        binding.apply {
            sivBack.setOnClickListener {
                moveNavigateUp()
            }
            btnCancel.setOnClickListener {
                moveNavigateUp()
            }
            btnSave.setOnClickListener {
                viewModel.updatePassword(
                    PasswordParamRequest(
                        currentPassword = etCurrentPassword.text.toString(),
                        newPassword = etNewPassword.text.toString(),
                        confirmPassword = etConfirmPassword.text.toString()
                    )
                )
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.updatePasswordResult.collect {
                it.source(
                    doOnSuccess = { result ->
                        moveNavigateUp()
                        showMessageSnackBar(true, result.payload)
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

    private fun resetErrorField() {
        binding.apply {
            etCurrentPassword.changed(onTextChanged = {
                tilCurrentPassword.isErrorEnabled = false
            })
            etNewPassword.changed(onTextChanged = {
                tilNewPassword.isErrorEnabled = false
            })
            etConfirmPassword.changed(onTextChanged = {
                tilConfirmPassword.isErrorEnabled = false
            })
        }
    }

    private fun handleStateErrorField(fieldErrorException: FieldErrorException) {
        binding.apply {
            fieldErrorException.apply {
                errorFields.forEach {
                    if (it.first == Constant.PASSWORD_CURRENT_FIELD) {
                        tilCurrentPassword.error = getString(it.second)
                    }
                    if (it.first == Constant.PASSWORD_NEW_FIELD) {
                        tilNewPassword.error = getString(it.second)
                    }
                    if (it.first == Constant.PASSWORD_CONFIRM_NEW_FIELD) {
                        tilConfirmPassword.error = getString(it.second)
                    }
                }
            }
        }
    }
}