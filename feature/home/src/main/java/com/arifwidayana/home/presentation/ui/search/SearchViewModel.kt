package com.arifwidayana.home.presentation.ui.search

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

class SearchViewModel(
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
            if (searchName.isNullOrEmpty()) {
                postSearchHistoryUseCase.execute(searchName).first()
                getFindSearchHistoryUseCase.execute(searchName).collect {
                    ViewResource.Success(it)
                }
            } else {
                getSearchHistoryUseCase.execute().collect {
                    ViewResource.Success(it)
                }
            }
        }
    }

    override fun saveSearchHistory(searchName: String) {
        viewModelScope.launch {
            postSearchHistoryUseCase.execute(searchName).first()
        }
    }
}