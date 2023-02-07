package com.arifwidayana.shared.data.local.model.mapper

import com.arifwidayana.shared.data.local.model.entity.SearchHistoryEntity
import com.arifwidayana.shared.data.local.model.entity.SearchHistoryParamEntity
import com.arifwidayana.shared.utils.DateUtils
import com.arifwidayana.shared.utils.mapper.ListMapper
import com.arifwidayana.shared.utils.mapper.ViewParamMapper

typealias SearchHistoryMap = SearchHistoryEntity
typealias SearchHistoryParamMap = SearchHistoryParamEntity

object SearchHistoryMapper : ViewParamMapper<List<SearchHistoryMap>, List<SearchHistoryParamMap>> {
    override fun toViewParam(dataObject: List<SearchHistoryMap>?): List<SearchHistoryParamMap> =
        ListMapper(SearchListHistoryMapper).toViewParams(dataObject)
}

object SearchListHistoryMapper : ViewParamMapper<SearchHistoryMap, SearchHistoryParamMap> {
    override fun toViewParam(dataObject: SearchHistoryMap?): SearchHistoryParamMap =
        SearchHistoryParamMap(
            searchHistoryName = dataObject?.searchHistoryName ?: "No history",
            createdAt = dataObject?.createdAt ?: DateUtils.getLocalDateTime(),
            updatedAt = dataObject?.updatedAt ?: DateUtils.getLocalDateTime()
        )
}