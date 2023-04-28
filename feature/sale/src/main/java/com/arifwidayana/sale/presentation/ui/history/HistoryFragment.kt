package com.arifwidayana.sale.presentation.ui.history

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.sale.R
import com.arifwidayana.sale.databinding.FragmentHistoryBinding
import com.arifwidayana.sale.presentation.adapter.HistoryAdapter
import com.arifwidayana.shared.data.network.model.response.sale.history.HistoryParamResponse
import com.arifwidayana.shared.utils.ext.source
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HistoryFragment : BaseFragment<FragmentHistoryBinding, HistoryViewModel>(
    FragmentHistoryBinding::inflate
) {
    override val viewModel: HistoryViewModel by inject()

    override fun initView() {
        onView()
    }

    private fun onView() {
        viewModel.getHistory()
    }

    override fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.getHistoryResult.collect {
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

    private fun setStateAdapter(data: List<HistoryParamResponse>?) {
        val adapter = HistoryAdapter(requireContext()) {
            val parcel = Bundle().apply { putInt("id", it) }
            moveNav(R.id.action_saleFragment_to_bid_nav, parcel)
        }
        adapter.submitList(data)
        binding.rvHistory.adapter = adapter
    }
}