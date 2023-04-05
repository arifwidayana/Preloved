package com.arifwidayana.sell.presentation.ui.category

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.arifwidayana.core.base.BaseBottomSheetDialogFragment
import com.arifwidayana.sell.databinding.FragmentAddCategoryBinding
import com.arifwidayana.sell.presentation.adapter.CategoryAdapter
import com.arifwidayana.sell.presentation.ui.sell.SellViewModel
import com.arifwidayana.shared.utils.ext.source
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class AddCategoryFragment(
    private val vm: SellViewModel,
    private val refresh: () -> Unit
) : BaseBottomSheetDialogFragment<FragmentAddCategoryBinding, AddCategoryViewModel>(
    FragmentAddCategoryBinding::inflate
) {
    override val viewModel: AddCategoryViewModel by inject()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        isCancelable = false
        viewModel.categoryProduct()
    }

    private fun onClick() {
        binding.apply {
            btnSubmit.setOnClickListener {
                refresh.invoke()
                dismiss()
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.categoryProductResult.collect {
                    it.source(doOnLoading = {}, doOnSuccess = { result ->
                        setStateCategory(result.payload)
                    }, doOnError = {})
                }
            }
        }
    }

    private fun setStateCategory(data: CategoryDataResult?) {
        binding.apply {
            val adapter = CategoryAdapter(vm)
            adapter.submitList(data)
            rvCategory.adapter = adapter
        }
    }
}