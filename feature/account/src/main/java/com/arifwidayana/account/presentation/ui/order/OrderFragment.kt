package com.arifwidayana.account.presentation.ui.order

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.arifwidayana.account.databinding.FragmentOrderBinding
import com.arifwidayana.account.presentation.adapter.order.OrderAdapter
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.shared.utils.ext.source
import org.koin.android.ext.android.inject

class OrderFragment : BaseFragment<FragmentOrderBinding, OrderViewModel>(
    FragmentOrderBinding::inflate
) {
    override val viewModel: OrderViewModel by inject()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModel.getOrder()
    }

    private fun onClick() {
        binding.apply {
            btnBack.setOnClickListener {
                moveNavigateUp()
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.getOrderResult.collect {
                it.source(
                    doOnSuccess = { result ->
                        setStateRecycler(result.payload)
                    },
                    doOnError = { error ->
                        showMessageSnackBar(true, exception = error.exception)
                    }
                )
            }
        }
    }

    private fun setStateRecycler(data: OrderDataResult?) {
        binding.apply {
            val adapter = OrderAdapter {
                val parcel = Bundle().apply { putInt("id", it) }
                moveNav(com.arifwidayana.account.R.id.action_orderFragment_to_bid_nav, parcel)
            }
            adapter.submitList(data)
            rvOrder.adapter = adapter
        }
    }
}