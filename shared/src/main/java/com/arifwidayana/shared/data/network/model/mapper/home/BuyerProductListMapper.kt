package com.arifwidayana.shared.data.network.model.mapper.home

import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductResponse
import com.arifwidayana.shared.utils.mapper.ListMapper
import com.arifwidayana.shared.utils.mapper.ViewParamMapper

typealias BuyerProductMap = BuyerProductResponse
typealias BuyerProductParamMap = BuyerProductParamResponse
// typealias BuyerProductCategoryMap = BuyerProductResponse.Category
// typealias BuyerProductCategoryParamMap = BuyerProductParamResponse.Category

object BuyerProductListMapper : ViewParamMapper<List<BuyerProductMap>, List<BuyerProductParamMap>> {
    override fun toViewParam(dataObject: List<BuyerProductMap>?): List<BuyerProductParamMap> =
        ListMapper(BuyerProductMapper).toViewParams(dataObject)
}

object BuyerProductMapper : ViewParamMapper<BuyerProductMap, BuyerProductParamMap> {
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
            status = dataObject?.status.toString().replaceFirstChar {
                it.uppercase()
            },
            createdAt = dataObject?.createdAt.orEmpty(),
            updatedAt = dataObject?.updatedAt.orEmpty(),
//            categories = ListMapper(BuyerProductCategoryMapper).toViewParams(dataObject?.categories),
            categories = dataObject?.categories?.joinToString { it.name.toString() }.orEmpty(),
            user = BuyerProductParamResponse.User(
                id = dataObject?.user?.id ?: 0,
                fullName = dataObject?.user?.fullName.orEmpty(),
                email = dataObject?.user?.email.orEmpty(),
                phoneNumber = dataObject?.user?.phoneNumber.orEmpty(),
                address = dataObject?.user?.address.orEmpty(),
                imageUrl = dataObject?.user?.imageUrl.orEmpty(),
                city = dataObject?.user?.city.orEmpty()
            )
        )
}

// object BuyerProductCategoryMapper :
//    ViewParamMapper<BuyerProductCategoryMap, BuyerProductCategoryParamMap> {
//    override fun toViewParam(dataObject: BuyerProductCategoryMap?): BuyerProductCategoryParamMap =
//        BuyerProductCategoryParamMap(
//            id = dataObject?.id ?: 0,
//            name = dataObject?.name ?: "No Category"
//        )
// }