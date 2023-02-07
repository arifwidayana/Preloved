package com.arifwidayana.home.presentation.ui.search

import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.home.databinding.FragmentSearchBinding
import com.arifwidayana.home.presentation.adapter.category.SearchHistoryAdapter
import com.arifwidayana.shared.utils.ext.changed
import com.arifwidayana.shared.utils.ext.source
import org.koin.android.ext.android.inject

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(
    FragmentSearchBinding::inflate
) {
    override val viewModel: SearchViewModel by inject()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModel.searchHistory()
    }

    private fun onClick() {
        binding.apply {
            btnBack.setOnClickListener {
                moveNavigateUp()
            }
            svProduct.changed(
                onQueryTextSubmit = {
                    viewModel.saveSearchHistory(it)
                },
                onQueryTextChange = {
                    viewModel.searchHistory(it)
                }
            )
        }
    }

    override fun observeData() {
        lifecycleScope.apply {
            launchWhenStarted {
                viewModel.searchDefaultResult.collect {
                    it.source(
                        doOnSuccess = { result ->
                            setStateSearch(result.payload)
                        },
                        doOnError = { error ->
                            Log.d("SEARCH DEFAULT", error.exception.toString())
                        }
                    )
                }
            }

            viewModel.searchHistoryResult.asLiveData().observe(viewLifecycleOwner) {
                it.source(
                    doOnSuccess = { result ->
                        setStateSearch(result.payload)
                    },
                    doOnError = { error ->
                        Log.d("SEARCH", error.exception.toString())
                    }
                )
            }
        }
    }

    private fun setStateSearch(data: SearchHistoryParamResponse?) {
        binding.apply {
            val adapter = SearchHistoryAdapter {
                viewModel.saveSearchHistory(it)
//                moveNav()
            }
            adapter.submitList(data)
            rvSearchHistory.adapter = adapter
        }
    }
}
