package com.arifwidayana.sale.presentation.ui.product

import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.sale.databinding.FragmentProductBinding
import org.koin.android.ext.android.inject

class ProductFragment : BaseFragment<FragmentProductBinding, ProductViewModel>(
    FragmentProductBinding::inflate
) {
    override val viewModel: ProductViewModel by inject()

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