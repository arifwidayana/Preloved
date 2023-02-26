package com.arifwidayana.account.presentation.ui.order

import com.arifwidayana.account.databinding.FragmentOrderBinding
import com.arifwidayana.core.base.BaseFragment
import org.koin.android.ext.android.inject

class OrderFragment : BaseFragment<FragmentOrderBinding, OrderViewModel>(
    FragmentOrderBinding::inflate
) {
    override val viewModel: OrderViewModel by inject()

    override fun initView() {
    }

    override fun observeData() {
    }
}