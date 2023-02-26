package com.arifwidayana.account.presentation.ui.account

import com.arifwidayana.account.databinding.FragmentAccountBinding
import com.arifwidayana.core.base.BaseFragment
import org.koin.android.ext.android.inject

class AccountFragment : BaseFragment<FragmentAccountBinding, AccountViewModel>(
    FragmentAccountBinding::inflate
) {
    override val viewModel: AccountViewModel by inject()

    override fun initView() {

    }

    override fun observeData() {
    }
}