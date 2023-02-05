package com.arifwidayana.shared.data.repository

import com.arifwidayana.core.wrapper.DataResource
import com.arifwidayana.shared.data.local.datasource.SearchHistoryDatasource
import com.arifwidayana.shared.data.local.model.entity.SearchHistoryEntity
import com.arifwidayana.shared.data.local.model.request.SearchHistoryRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

interface SearchHistoryRepository {
    suspend fun postSearchHistory(searchHistoryRequest: SearchHistoryRequest): Flow<DataResource<Unit>>
    suspend fun getSearchHistory(): Flow<DataResource<List<SearchHistoryEntity>>>
    suspend fun getFindHistory(searchHistoryRequest: SearchHistoryRequest): Flow<DataResource<List<SearchHistoryEntity>>>
}

class SearchHistoryRepositoryImpl(
    private val searchHistoryDatasource: SearchHistoryDatasource
) : SearchHistoryRepository, Repository() {
    override suspend fun postSearchHistory(searchHistoryRequest: SearchHistoryRequest): Flow<DataResource<Unit>> = flow {
        emit(proceed { searchHistoryDatasource.postSearchHistory(searchHistoryRequest) })
    }

    override suspend fun getSearchHistory(): Flow<DataResource<List<SearchHistoryEntity>>> = flow {
        emit(proceed { searchHistoryDatasource.getSearchHistory().first() })
    }

    override suspend fun getFindHistory(searchHistoryRequest: SearchHistoryRequest): Flow<DataResource<List<SearchHistoryEntity>>> = flow {
        emit(proceed { searchHistoryDatasource.getFindHistory(searchHistoryRequest).first() })
    }
}