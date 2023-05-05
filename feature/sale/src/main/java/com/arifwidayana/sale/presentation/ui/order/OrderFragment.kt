package com.arifwidayana.sale.presentation.ui.order

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.sale.databinding.FragmentOrderBinding
import com.arifwidayana.sale.presentation.adapter.OrderAdapter
import com.arifwidayana.shared.data.network.model.response.sale.order.SaleOrderParamResponse
import com.arifwidayana.shared.utils.ext.source
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class OrderFragment : BaseFragment<FragmentOrderBinding, OrderViewModel>(
    FragmentOrderBinding::inflate
) {
    override val viewModel: OrderViewModel by inject()

    override fun initView() {
        onView()
    }

    private fun onView() {
        viewModel.getSellerOrder()
    }

    override fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.orderResult.collect {
                        it.source(
                            doOnLoading = {},
                            doOnSuccess = { source ->
                                setStateAdapter(source.payload)
                            },
                            doOnError = { error ->
                                showMessageSnackBar(true, exception = error.exception)
                            }
                        )
                    }
                }
            }
        }
    }

    private fun setStateAdapter(data: List<SaleOrderParamResponse>?) {
        val adapter = OrderAdapter() {
//            val parcel = Bundle().apply { putInt("id", it) }
//            moveNav(R.id.action_saleFragment_to_bid_nav, parcel)
        }
        adapter.submitList(data)
        binding.rvSellerOrder.adapter = adapter
    }
}