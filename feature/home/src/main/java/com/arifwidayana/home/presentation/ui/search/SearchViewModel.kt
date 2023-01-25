package com.arifwidayana.home.presentation.ui.search

import androidx.lifecycle.ViewModel
import com.arifwidayana.home.domain.search.PostSearchHistoryUseCase

class SearchViewModel(
    private val postSearchHistoryUseCase: PostSearchHistoryUseCase
): SearchContract, ViewModel() {
}