package com.arifwidayana.shared.data.network.model.mapper

import com.arifwidayana.shared.data.network.model.response.home.category.CategoryParamResponse
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryResponse
import com.arifwidayana.shared.utils.mapper.ListMapper
import com.arifwidayana.shared.utils.mapper.ViewParamMapper

object CategoryProductMapper :
    ViewParamMapper<List<CategoryResponse.CategoryResponseItem>, List<CategoryParamResponse.CategoryParamResponseItem>> {
    override fun toViewParam(dataObject: List<CategoryResponse.CategoryResponseItem>?): List<CategoryParamResponse.CategoryParamResponseItem> =
        ListMapper(CategoryMapper).toViewParams(dataObject)
}

object CategoryMapper :
    ViewParamMapper<CategoryResponse.CategoryResponseItem, CategoryParamResponse.CategoryParamResponseItem> {
    override fun toViewParam(dataObject: CategoryResponse.CategoryResponseItem?): CategoryParamResponse.CategoryParamResponseItem =
        CategoryParamResponse.CategoryParamResponseItem(
            id = dataObject?.id ?: 0,
            name = dataObject?.name.orEmpty(),
            createdAt = dataObject?.createdAt.orEmpty(),
            updatedAt = dataObject?.updatedAt.orEmpty()
        )
}
