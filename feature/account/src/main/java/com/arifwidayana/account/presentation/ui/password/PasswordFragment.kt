package com.arifwidayana.account.presentation.ui.password

import androidx.lifecycle.lifecycleScope
import com.arifwidayana.account.databinding.FragmentPasswordBinding
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.shared.data.network.model.request.account.password.PasswordParamRequest
import com.arifwidayana.shared.utils.ext.source
import org.koin.android.ext.android.inject

class PasswordFragment : BaseFragment<FragmentPasswordBinding, PasswordViewModel>(
    FragmentPasswordBinding::inflate
) {
    override val viewModel: PasswordViewModel by inject()

    override fun initView() {
        onClick()
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
                        showMessageSnackBar(true, exception = error.exception)
                    }
                )
            }
        }
    }
}