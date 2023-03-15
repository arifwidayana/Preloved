package com.arifwidayana.home.presentation.ui.search.keyword

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.local.model.entity.SearchHistoryParamEntity
import kotlinx.coroutines.flow.StateFlow

typealias SearchHistoryParamResponse = List<SearchHistoryParamEntity>

interface SearchContract {
    val searchHistoryResult: StateFlow<ViewResource<SearchHistoryParamResponse>>
    val searchDefaultResult: StateFlow<ViewResource<SearchHistoryParamResponse>>
    fun searchHistory(searchName: String? = "")
    fun saveSearchHistory(searchName: String)
}