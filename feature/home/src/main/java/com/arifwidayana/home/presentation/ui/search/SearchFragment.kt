package com.arifwidayana.home.presentation.ui.search

import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.home.databinding.FragmentSearchBinding
import org.koin.android.ext.android.inject


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(
    FragmentSearchBinding::inflate
) {
    override val viewModel: SearchViewModel by inject()

    override fun initView() {
    }

    override fun observeData() {
    }
}