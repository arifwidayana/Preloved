package com.arifwidayana.account.presentation.ui.account

import androidx.lifecycle.ViewModel
import com.arifwidayana.account.databinding.FragmentAccountBinding
import com.arifwidayana.core.base.BaseFragment
import org.koin.android.ext.android.inject

class AccountFragment : BaseFragment<FragmentAccountBinding, ViewModel>(
    FragmentAccountBinding::inflate
) {
    override val viewModel: ViewModel by inject()

    override fun initView() {
        binding.root.setOnClickListener {
        }
    }

    override fun observeData() {
    }
}