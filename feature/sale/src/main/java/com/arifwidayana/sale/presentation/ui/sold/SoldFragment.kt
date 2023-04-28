package com.arifwidayana.sale.presentation.ui.sold

import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.sale.databinding.FragmentSoldBinding
import org.koin.android.ext.android.inject

class SoldFragment : BaseFragment<FragmentSoldBinding, SoldViewModel>(
    FragmentSoldBinding::inflate
) {
    override val viewModel: SoldViewModel by inject()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
    }

    private fun onClick() {
        binding.apply {
        }
    }

    override fun observeData() {
    }
}