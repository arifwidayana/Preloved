package com.arifwidayana.account.presentation.ui.password

import com.arifwidayana.account.databinding.FragmentPasswordBinding
import com.arifwidayana.core.base.BaseFragment
import org.koin.android.ext.android.inject

class PasswordFragment : BaseFragment<FragmentPasswordBinding, PasswordViewModel>(
    FragmentPasswordBinding::inflate
) {
    override val viewModel: PasswordViewModel by inject()

    override fun initView() {
    }

    override fun observeData() {
    }
}