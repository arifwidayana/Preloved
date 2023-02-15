package com.arifwidayana.home.presentation.ui.home

import android.os.Bundle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.home.R
import com.arifwidayana.home.databinding.FragmentHomeBinding
import com.arifwidayana.home.presentation.adapter.home.BannerAdapter
import com.arifwidayana.home.presentation.adapter.home.ProductAdapter
import com.arifwidayana.shared.utils.ext.changed
import com.arifwidayana.shared.utils.ext.source
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel: HomeViewModel by inject()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onClick() {
        binding.apply {
            svSearchItem.setOnClickListener {
                moveNav(R.id.action_homeFragment_to_searchFragment)
            }
        }
    }

    private fun onView() {
        viewModel.apply {
            categoryProduct()
            showProduct()
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
                        }
                    )
                }
            }
            launchWhenStarted {
                viewModel.showProductResult.collect {
                    setStateProduct(it)
                }
            }
            launchWhenStarted {
                viewModel.bannerProductResult.collect {
                    it.source(
                        doOnSuccess = { result ->
                            setStateBanner(result.payload)
                        },
                        doOnError = { error ->
                            showMessageSnackBar(true, exception = error.exception)
                        }
                    )
                }
            }
        }
    }

    private fun setStateBanner(data: BannerParamDataResponse?) {
        binding.apply {
            val adapter = BannerAdapter()
            adapter.submitList(data)
            vpBanner.adapter = adapter
        }
    }

    private fun tabLayout(data: CategoryParamDataResponse?) {
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

    private fun setStateProduct(data: BuyerProductParamDataResponse?) {
        binding.apply {
            data?.let {
                val adapter = ProductAdapter { productId ->
                    val parcel = Bundle().apply { putInt("id", productId) }
                    moveNav(R.id.action_homeFragment_to_bid_nav, parcel)
                }
                adapter.submitData(lifecycle, it)
                adapter.loadStateFlow.asLiveData().observe(viewLifecycleOwner) { obs ->
                    when (obs.source.refresh) {
                        is LoadState.Loading -> {
                        }
                        is LoadState.Error -> {
                        }
                        is LoadState.NotLoading -> {
                        }
                    }
                }
                rvListProduct.adapter = adapter
            }
        }
    }
}