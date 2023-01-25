package com.arifwidayana.home.domain.search

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.local.model.entity.SearchHistoryParamEntity
import com.arifwidayana.shared.data.local.model.mapper.SearchHistoryMapper
import com.arifwidayana.shared.data.repository.SearchHistoryRepository
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias SearchHistoryDataResource = List<SearchHistoryParamEntity>

class GetSearchHistoryUseCase(
    private val searchHistoryRepository: SearchHistoryRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, SearchHistoryDataResource>(coroutineDispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<SearchHistoryDataResource>> = flow {
        searchHistoryRepository.getSearchHistory().collect {
            it.suspendSource(
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