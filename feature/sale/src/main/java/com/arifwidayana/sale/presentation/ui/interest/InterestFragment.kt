package com.arifwidayana.sale.presentation.ui.interest

import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.sale.databinding.FragmentInterestBinding
import org.koin.android.ext.android.inject

class InterestFragment : BaseFragment<FragmentInterestBinding, InterestViewModel>(
    FragmentInterestBinding::inflate
) {
    override val viewModel: InterestViewModel by inject()

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