package com.arifwidayana.shared.data.network.model.mapper.home

import com.arifwidayana.shared.data.network.model.response.home.category.CategoryParamResponse
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryResponse
import com.arifwidayana.shared.utils.mapper.ListMapper
import com.arifwidayana.shared.utils.mapper.ViewParamMapper

typealias CategoryMap = CategoryResponse.CategoryResponseItem
typealias CategoryParamMap = CategoryParamResponse.CategoryParamResponseItem

object CategoryListProductMapper : ViewParamMapper<List<CategoryMap>, List<CategoryParamMap>> {
    override fun toViewParam(dataObject: List<CategoryMap>?): List<CategoryParamMap> =
        ListMapper(CategoryProductMapper).toViewParams(dataObject)
}

object CategoryProductMapper : ViewParamMapper<CategoryMap, CategoryParamMap> {
    override fun toViewParam(dataObject: CategoryMap?): CategoryParamMap =
        CategoryParamMap(
            id = dataObject?.id ?: 0,
            name = dataObject?.name.orEmpty(),
            createdAt = dataObject?.createdAt.orEmpty(),
            updatedAt = dataObject?.updatedAt.orEmpty()
        )
}
