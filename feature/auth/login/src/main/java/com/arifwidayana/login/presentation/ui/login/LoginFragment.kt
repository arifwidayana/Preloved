package com.arifwidayana.login.presentation.ui.login

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.core.utils.FieldErrorException
import com.arifwidayana.login.R
import com.arifwidayana.login.databinding.FragmentLoginBinding
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.ext.changed
import com.arifwidayana.shared.utils.ext.source
import org.koin.android.ext.android.inject

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    FragmentLoginBinding::inflate
) {
    override val viewModel: LoginViewModel by inject()

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
            btnLogin.setOnClickListener {
                viewModel.loginUser(
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString()
                )
            }
            tvNoHaveAccount.setOnClickListener {
                moveNav(R.id.action_loginFragment_to_register_nav)
            }
        }
    }

    private fun resetFieldError() {
        binding.apply {
            etEmail.changed(onTextChanged = {
                tilEmail.isErrorEnabled = false
            })
            etPassword.changed(onTextChanged = {
                tilPassword.isErrorEnabled = false
            })
        }
    }

    override fun showLoading(isVisible: Boolean) {
        binding.containerLoading.isVisible = isVisible
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.loginUserResult.collect {
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
                    if (it.first == Constant.LOGIN_EMAIL_FIELD) {
                        tilEmail.error = getString(it.second)
                    }
                    if (it.first == Constant.LOGIN_PASSWORD_FIELD) {
                        tilPassword.error = getString(it.second)
                    }
                }
            }
        }
    }
}