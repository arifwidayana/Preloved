package com.arifwidayana.home.domain.search

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.DataResource
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.local.model.request.SearchHistoryRequest
import com.arifwidayana.shared.data.repository.SearchHistoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class PostSearchHistoryUseCase(
    private val searchHistoryRepository: SearchHistoryRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<String, String>(coroutineDispatcher) {
    override suspend fun execute(param: String?): Flow<ViewResource<String>> = flow {
        param?.let {
            when (searchHistoryRepository.postSearchHistory(SearchHistoryRequest(searchName = it)).first()) {
                is DataResource.Success -> emit(ViewResource.Success(it))
                is DataResource.Error -> emit(ViewResource.Error(IllegalStateException("Failed to save history")))
            }
        }
    }
}