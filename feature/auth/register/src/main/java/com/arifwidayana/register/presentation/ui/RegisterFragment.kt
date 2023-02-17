package com.arifwidayana.register.presentation.ui

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.core.utils.FieldErrorException
import com.arifwidayana.register.databinding.FragmentRegisterBinding
import com.arifwidayana.shared.data.network.model.request.auth.register.RegisterParamRequest
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.ext.changed
import com.arifwidayana.shared.utils.ext.source
import org.koin.android.ext.android.inject

class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(
    FragmentRegisterBinding::inflate
) {
    override val viewModel: RegisterViewModel by inject()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        resetFieldError()
    }

    private fun onClick() {
        binding.apply {
            btnBack.setOnClickListener {
                moveNavigateUp()
            }
            btnRegister.setOnClickListener {
                viewModel.registerUser(
                    RegisterParamRequest(
                        fullName = etFullName.text.toString(),
                        email = etEmail.text.toString(),
                        password = etPassword.text.toString(),
                        confirmPassword = etConfirmPassword.text.toString(),
                        phoneNumber = etPhoneNumber.text.toString(),
                        address = etAddress.text.toString(),
                        city = etCity.text.toString()
                    )
                )
            }
            tvLogin.setOnClickListener {
                moveNavigateUp()
            }
        }
    }

    private fun resetFieldError() {
        binding.apply {
            etFullName.changed(onTextChanged = {
                tilFullName.isErrorEnabled = false
            })
            etEmail.changed(onTextChanged = {
                tilEmail.isErrorEnabled = false
            })
            etPassword.changed(onTextChanged = {
                tilPassword.isErrorEnabled = false
            })
            etConfirmPassword.changed(onTextChanged = {
                tilConfirmPassword.isErrorEnabled = false
            })
            etPhoneNumber.changed(onTextChanged = {
                tilPhoneNumber.isErrorEnabled = false
            })
            etAddress.changed(onTextChanged = {
                tilAddress.isErrorEnabled = false
            })
            etCity.changed(onTextChanged = {
                tilCity.isErrorEnabled = false
            })
        }
    }

    override fun showLoading(isVisible: Boolean) {
        binding.containerLoading.isVisible = isVisible
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.registerUserResult.collect {
                it.source(
                    doOnLoading = {
                        showLoading(true)
                    },
                    doOnSuccess = {
                        showLoading(false)
                        moveNavigateUp()
                    },
                    doOnError = { error ->
                        showLoading(false)
                        when (error.exception) {
                            is FieldErrorException -> handleErrorField(error.exception as FieldErrorException)
                            else -> showMessageSnackBar(true, exception = error.exception)
                        }
                    }
                )
            }
        }
    }

    private fun handleErrorField(fieldErrorException: FieldErrorException) {
        binding.apply {
            fieldErrorException.apply {
                errorFields.forEach {
                    if (it.first == Constant.REGISTER_FULLNAME_FIELD) {
                        tilFullName.error = getString(it.second)
                    }
                    if (it.first == Constant.REGISTER_EMAIL_FIELD) {
                        tilEmail.error = getString(it.second)
                    }
                    if (it.first == Constant.REGISTER_PASSWORD_FIELD) {
                        tilPassword.error = getString(it.second)
                    }
                    if (it.first == Constant.REGISTER_CONFIRM_PASSWORD_FIELD) {
                        tilConfirmPassword.error = getString(it.second)
                    }
                    if (it.first == Constant.REGISTER_PHONE_FIELD) {
                        tilPhoneNumber.error = getString(it.second)
                    }
                    if (it.first == Constant.REGISTER_ADDRESS_FIELD) {
                        tilAddress.error = getString(it.second)
                    }
                    if (it.first == Constant.REGISTER_CITY_FIELD) {
                        tilCity.error = getString(it.second)
                    }
                }
            }
        }
    }
}