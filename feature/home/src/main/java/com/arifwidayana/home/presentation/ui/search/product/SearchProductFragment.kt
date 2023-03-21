package com.arifwidayana.home.presentation.ui.search.product

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.home.R
import com.arifwidayana.home.databinding.FragmentSearchProductBinding
import com.arifwidayana.home.presentation.adapter.home.ProductAdapter
import com.arifwidayana.shared.utils.ext.changed
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SearchProductFragment : BaseFragment<FragmentSearchProductBinding, SearchProductViewModel>(
    FragmentSearchProductBinding::inflate
) {
    override val viewModel: SearchProductViewModel by inject()
    private val args by navArgs<SearchProductFragmentArgs>()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        binding.apply {
            svProduct.setQuery(args.search, false)
            viewModel.searchProduct(args.search)
        }
    }

    private fun onClick() {
        binding.apply {
            btnBack.setOnClickListener {
                moveNavigateUp()
            }
            svProduct.changed(onQueryTextSubmit = {
                viewModel.searchProduct(it)
            })
        }
    }

    override fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.searchProductResult.collect {
                    setStateProduct(it)
                }
            }
        }
    }

    private fun setStateProduct(data: SearchProductParamDataResult?) {
        binding.apply {
            data?.let {
                val adapter = ProductAdapter { productId ->
                    val parcel = Bundle().apply { putInt("id", productId) }
                    moveNav(R.id.action_searchProductFragment_to_bid_nav, parcel)
                }
                adapter.submitData(lifecycle, it)
                rvSearchProduct.adapter = adapter
            }
        }
    }
}