package com.arifwidayana.shared.data.network.model.mapper.home

import androidx.paging.PagingData
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductResponse
import com.arifwidayana.shared.utils.mapper.ListMapper
import com.arifwidayana.shared.utils.mapper.PagingDataMapper
import com.arifwidayana.shared.utils.mapper.ViewParamMapper

typealias BuyerProductMap = BuyerProductResponse.BuyerProductResponseItem
typealias BuyerProductParamMap = BuyerProductParamResponse.BuyerProductResponseItem
typealias BuyerProductCategoryMap = BuyerProductResponse.BuyerProductResponseItem.Category
typealias BuyerProductCategoryParamMap = BuyerProductParamResponse.BuyerProductResponseItem.Category

object BuyerProductPagingMapper : ViewParamMapper<PagingData<BuyerProductMap>, PagingData<BuyerProductParamMap>> {
    override fun toViewParam(dataObject: PagingData<BuyerProductMap>?): PagingData<BuyerProductParamMap> =
        PagingDataMapper(BuyerProductMapper).toViewParams(dataObject)
}

object BuyerProductMapper : ViewParamMapper<BuyerProductMap, BuyerProductParamMap> {
    override fun toViewParam(dataObject: BuyerProductMap?): BuyerProductParamMap =
        BuyerProductParamMap(
            id = dataObject?.id ?: 0,
            name = dataObject?.name.orEmpty(),
            description = dataObject?.description.orEmpty(),
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