package com.arifwidayana.shared.data.local.datasource

import com.arifwidayana.shared.data.local.model.entity.SearchHistoryEntity
import com.arifwidayana.shared.data.local.model.request.SearchHistoryRequest
import com.arifwidayana.shared.data.local.service.SearchDao
import com.arifwidayana.shared.utils.DateUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SearchHistoryDatasource {
    suspend fun postSearchHistory(searchHistoryRequest: SearchHistoryRequest)
    suspend fun getSearchHistory(): List<SearchHistoryEntity>
    suspend fun getFindHistory(searchHistoryRequest: SearchHistoryRequest): Flow<List<SearchHistoryEntity>>
}

class SearchHistoryDatasourceImpl(
    private val searchDao: SearchDao
) : SearchHistoryDatasource {

    override suspend fun postSearchHistory(searchHistoryRequest: SearchHistoryRequest) {
        searchDao.getFindHistory(searchHistoryRequest.searchName).collect {
            return@collect if (it.isNotEmpty()) {
                searchDao.updateSearchHistory(
                    searchName = searchHistoryRequest.searchName,
                    updatedAt = DateUtils.getLocalDateTime()
                )
            } else {
                searchDao.postSearchHistory(
                    SearchHistoryEntity(
                        searchHistoryName = searchHistoryRequest.searchName,
                        createdAt = DateUtils.getLocalDateTime(),
                        updatedAt = DateUtils.getLocalDateTime()
                    )
                )
            }
        }
    }

    override suspend fun getSearchHistory(): List<SearchHistoryEntity> {
        return searchDao.getSearchHistory()
    }

    override suspend fun getFindHistory(searchHistoryRequest: SearchHistoryRequest): Flow<List<SearchHistoryEntity>> = flow {
        searchDao.getFindHistory(searchHistoryRequest.searchName)
    }
}