package com.arifwidayana.shared.data.network.model.mapper.home

import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductResponse
import com.arifwidayana.shared.utils.mapper.ListMapper
import com.arifwidayana.shared.utils.mapper.ViewParamMapper

typealias BuyerProductMap = BuyerProductResponse
typealias BuyerProductParamMap = BuyerProductParamResponse
typealias BuyerProductCategoryMap = BuyerProductResponse.Category
typealias BuyerProductCategoryParamMap = BuyerProductParamResponse.Category

object BuyerProductMapper : ViewParamMapper<List<BuyerProductMap>, List<BuyerProductParamMap>> {
    override fun toViewParam(dataObject: List<BuyerProductMap>?): List<BuyerProductParamMap> =
        ListMapper(BuyerProductPagingMapper).toViewParams(dataObject)
}

object BuyerProductPagingMapper : ViewParamMapper<BuyerProductMap, BuyerProductParamMap> {
    override fun toViewParam(dataObject: BuyerProductMap?): BuyerProductParamMap =
        BuyerProductParamMap(
            id = dataObject?.id ?: 0,
            name = dataObject?.name.orEmpty(),
            description = dataObject?.description ?: "No Descriptions",
            basePrice = dataObject?.basePrice ?: 0,
            imageUrl = dataObject?.imageUrl.orEmpty(),
            imageName = dataObject?.imageName.orEmpty(),
            location = dataObject?.location.orEmpty(),
            userId = dataObject?.userId ?: 0,
            status = dataObject?.status.orEmpty(),
            createdAt = dataObject?.createdAt.orEmpty(),
            updatedAt = dataObject?.updatedAt.orEmpty(),
            categories = ListMapper(BuyerProductCategoryMapper).toViewParams(dataObject?.categories)
        )
}

object BuyerProductCategoryMapper :
    ViewParamMapper<BuyerProductCategoryMap, BuyerProductCategoryParamMap> {
    override fun toViewParam(dataObject: BuyerProductCategoryMap?): BuyerProductCategoryParamMap =
        BuyerProductCategoryParamMap(
            id = dataObject?.id ?: 0,
            name = dataObject?.name ?: "No Category"
        )
}