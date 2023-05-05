package com.arifwidayana.sale.presentation.ui.sold

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.sale.databinding.FragmentSoldBinding
import com.arifwidayana.sale.presentation.adapter.SoldAdapter
import com.arifwidayana.shared.data.network.model.response.sale.sold.SaleProductSoldParamResponse
import com.arifwidayana.shared.utils.ext.source
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SoldFragment : BaseFragment<FragmentSoldBinding, SoldViewModel>(
    FragmentSoldBinding::inflate
) {
    override val viewModel: SoldViewModel by inject()

    override fun initView() {
        onView()
    }

    private fun onView() {
        viewModel.getProductSold()
    }

    override fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.productSoldResult.collect {
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

    private fun setStateAdapter(data: List<SaleProductSoldParamResponse>?) {
        binding.apply {
            val adapter = SoldAdapter {
            }
            adapter.submitList(data)
            rvProductSold.adapter = adapter
        }
    }
}