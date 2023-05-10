package com.arifwidayana.sale.presentation.ui.product

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.sale.databinding.FragmentProductBinding
import com.arifwidayana.sale.presentation.adapter.ProductAdapter
import com.arifwidayana.shared.data.network.model.response.sale.product.SaleProductParamResponse
import com.arifwidayana.shared.utils.ext.source
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ProductFragment : BaseFragment<FragmentProductBinding, ProductViewModel>(
    FragmentProductBinding::inflate
) {
    override val viewModel: ProductViewModel by inject()

    override fun initView() {
        onView()
    }

    private fun onView() {
        viewModel.getSellerProduct()
    }

    override fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.sellerProductResult.collect {
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

    private fun setStateAdapter(data: List<SaleProductParamResponse>?) {
        binding.apply {
            val adapter = ProductAdapter {
            }
            adapter.submitList(data)
            rvSellerProduct.adapter = adapter
        }
    }
}