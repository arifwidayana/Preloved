package com.arifwidayana.home.presentation.ui.search.keyword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.home.domain.search.GetFindSearchHistoryUseCase
import com.arifwidayana.home.domain.search.GetSearchHistoryUseCase
import com.arifwidayana.home.domain.search.PostSearchHistoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SearchKeywordViewModel(
    private val postSearchHistoryUseCase: PostSearchHistoryUseCase,
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
    private val getFindSearchHistoryUseCase: GetFindSearchHistoryUseCase
) : SearchContract, ViewModel() {
    private val _searchHistoryResult = MutableStateFlow<ViewResource<SearchHistoryParamResponse>>(ViewResource.Empty())
    private val _searchDefaultResult = MutableStateFlow<ViewResource<SearchHistoryParamResponse>>(ViewResource.Empty())
    override val searchHistoryResult: StateFlow<ViewResource<SearchHistoryParamResponse>> = _searchHistoryResult
    override val searchDefaultResult: StateFlow<ViewResource<SearchHistoryParamResponse>> = _searchDefaultResult

    override fun searchHistory(searchName: String?) {
        viewModelScope.launch {
            if (searchName?.isNotEmpty() == true) {
                getFindSearchHistoryUseCase(searchName).collect {
                    _searchHistoryResult.value = it
                }
            } else {
                getSearchHistoryUseCase().collect {
                    _searchDefaultResult.value = it
                }
            }
        }
    }

    override fun saveSearchHistory(searchName: String) {
        viewModelScope.launch {
            postSearchHistoryUseCase(searchName).first()
        }
    }
}