package com.arifwidayana.home.presentation.ui.home

import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.map
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.home.databinding.FragmentHomeBinding
import com.arifwidayana.home.presentation.adapter.home.ProductAdapter
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryParamResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import com.arifwidayana.shared.utils.ext.source
import com.google.android.material.tabs.TabLayout
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
                            Log.d("CATEGORY", result.payload.toString())
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
                            Log.d(
                                "CATEGORYPRODUCT",
                                result.payload?.map {
                                    it
                                }.toString()
                            )
                        },
                        doOnError = { error ->
                            Log.d("CATEGORYPRODUCT", error.exception.toString())
                        }
                    )
                }
            }
        }
    }

    private fun tabLayout(data: List<CategoryParamResponse.CategoryParamResponseItem>?) {
        binding.apply {
            tlCategoryItem.addTab(tlCategoryItem.newTab().setText("All").setId(0))
            data?.forEach { res ->
                tlCategoryItem.addTab(tlCategoryItem.newTab().setText(res.name).setId(res.id))
            }
            tlCategoryItem.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    Log.d("TAG", "onTabSelected: ${tab?.id}")
                    if (tab?.id != 0) {
                        viewModel.showProduct(
                            categoryId = tab?.id ?: 0
                        )
                        Log.d("TAG", "onTabSelectedTrue: ${tab?.id}")
                    } else {
                        viewModel.showProduct(
                            categoryId = tab.id
                        )
                        Log.d("TAG", "onTabSelectedFalse: ${tab.id}")
                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun setStateProduct(data: PagingData<BuyerProductParamResponse.BuyerProductResponseItem>?) {
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