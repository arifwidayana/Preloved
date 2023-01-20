package com.arifwidayana.home.presentation.ui.category

import androidx.lifecycle.lifecycleScope
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.home.databinding.FragmentCategoryBinding
import org.koin.android.ext.android.inject

class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>(
    FragmentCategoryBinding::inflate
) {
    override val viewModel: CategoryViewModel by inject()

    override fun initView() {
        onView()
    }

    private fun onView() {
        viewModel.apply { }
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted { }
    }
}