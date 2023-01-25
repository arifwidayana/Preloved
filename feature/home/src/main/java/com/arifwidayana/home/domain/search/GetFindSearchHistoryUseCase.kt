package com.arifwidayana.home.domain.search

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.local.model.mapper.SearchHistoryMapper
import com.arifwidayana.shared.data.local.model.request.SearchHistoryRequest
import com.arifwidayana.shared.data.repository.SearchHistoryRepository
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFindSearchHistoryUseCase(
    private val searchHistoryRepository: SearchHistoryRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<String, SearchHistoryDataResource>(coroutineDispatcher) {
    override suspend fun execute(param: String?): Flow<ViewResource<SearchHistoryDataResource>> =
        flow {
            param?.let {
                searchHistoryRepository.getFindHistory(SearchHistoryRequest(searchName = it))
                    .collect { result ->
                        result.suspendSource(
                            doOnSuccess = { source ->
                                emit(ViewResource.Success(SearchHistoryMapper.toViewParam(source.payload)))
                            },
                            doOnError = { error ->
                                emit(ViewResource.Error(error.exception))
                            }
                        )
                    }
            }
        }
}