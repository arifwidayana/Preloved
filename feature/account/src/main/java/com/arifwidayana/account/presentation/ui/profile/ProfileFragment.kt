package com.arifwidayana.account.presentation.ui.profile

import com.arifwidayana.account.databinding.FragmentProfileBinding
import com.arifwidayana.core.base.BaseFragment
import org.koin.android.ext.android.inject

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>(
    FragmentProfileBinding::inflate
) {
    override val viewModel: ProfileViewModel by inject()

    override fun initView() {
    }

    override fun observeData() {
    }
}