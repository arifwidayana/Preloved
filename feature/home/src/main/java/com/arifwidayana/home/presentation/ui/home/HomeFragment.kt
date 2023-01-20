package com.arifwidayana.home.presentation.ui.home

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.home.databinding.FragmentHomeBinding
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryParamResponse
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
        viewModel.categoryProduct()
    }

    override fun observeData() {
        lifecycleScope.apply {
            launchWhenStarted {
                viewModel.categoryProductResult.collect {
                    it.source(
                        doOnSuccess = { result ->
                            tabLayout(result.payload)
                            Log.d("OBSERVE", it.payload.toString())
                        },
                        doOnError = { error ->
                            showMessageSnackBar(true, exception = error.exception)
                            Log.d("OBSERVE", it.message.toString())
                        }
                    )
                }
            }
            launchWhenStarted { }
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
                        Log.d("TAG", "onTabSelectedTrue: ${tab?.id}")
                    } else {
                        Log.d("TAG", "onTabSelectedFalse: ${tab.id}")
                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }
}