package com.arifwidayana.bid.presentation.ui.product

import androidx.lifecycle.lifecycleScope
import com.arifwidayana.bid.databinding.FragmentDetailProductBinding
import com.arifwidayana.core.base.BaseFragment
import org.koin.android.ext.android.inject

class DetailProductFragment : BaseFragment<FragmentDetailProductBinding, DetailProductViewModel>(
    FragmentDetailProductBinding::inflate
) {
    override val viewModel: DetailProductViewModel by inject()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
//        viewModel
    }

    private fun onClick() {
        binding.apply {

        }
    }

    override fun observeData() {
        lifecycleScope.apply {
            launchWhenStarted {

            }
        }
    }
}