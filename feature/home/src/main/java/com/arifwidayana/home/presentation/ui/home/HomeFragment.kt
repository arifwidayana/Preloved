package com.arifwidayana.home.presentation.ui.home

import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.home.databinding.FragmentHomeBinding
import com.arifwidayana.home.presentation.adapter.home.ProductAdapter
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryParamResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import com.arifwidayana.shared.utils.ext.changed
import com.arifwidayana.shared.utils.ext.source
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel: HomeViewModel by inject()

    override fun initView() {
        onView()
    }

    private fun onView() {
        viewModel.apply {
            categoryProduct()
            showProduct(categoryId = 0)
        }
    }

    override fun observeData() {
        lifecycleScope.apply {
            launchWhenStarted {
                viewModel.categoryProductResult.collect {
                    it.source(
                        doOnSuccess = { result ->
                            tabLayout(result.payload)
                        },
                        doOnError = { error ->
                            showMessageSnackBar(true, exception = error.exception)
                            Log.d("CATEGORY", error.exception.toString())
                        }
                    )
                }
            }
            launchWhenStarted {
                viewModel.showProductResult.collect {
                    it.source(
                        doOnSuccess = { result ->
                            setStateProduct(result.payload)
                        },
                        doOnError = { error ->
                            Log.d("CATEGORYPRODUCT", error.exception.toString())
                        }
                    )
                }
            }
        }
    }

    private fun tabLayout(data: List<CategoryParamResponse>?) {
        binding.apply {
            tlCategoryItem.addTab(tlCategoryItem.newTab().setText("All").setId(0))
            data?.forEach { res ->
                tlCategoryItem.addTab(tlCategoryItem.newTab().setText(res.name).setId(res.id))
            }
            tlCategoryItem.changed(onTabSelected = {
                viewModel.showProduct(categoryId = it)
            })
        }
    }

    private fun setStateProduct(data: PagingData<BuyerProductParamResponse>?) {
        binding.apply {
            val adapter = ProductAdapter {
                it
            }
            adapter.loadStateFlow.asLiveData().observe(viewLifecycleOwner) {
                when (it.source.refresh) {
                    is LoadState.Loading -> {
                    }
                    is LoadState.Error -> {
                    }
                    is LoadState.NotLoading -> {
                    }
                }
            }
            data?.let {
                adapter.submitData(lifecycle, it)
            }
            rvListProduct.adapter = adapter
        }
    }
}