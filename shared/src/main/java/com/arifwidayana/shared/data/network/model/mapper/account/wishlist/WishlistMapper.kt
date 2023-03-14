package com.arifwidayana.shared.data.network.model.mapper.account.wishlist

import com.arifwidayana.shared.data.network.model.response.account.wishlist.WishlistAccountParamResponse
import com.arifwidayana.shared.data.network.model.response.account.wishlist.WishlistAccountResponse
import com.arifwidayana.shared.utils.ext.convertCurrency
import com.arifwidayana.shared.utils.mapper.ListMapper
import com.arifwidayana.shared.utils.mapper.ViewParamMapper

object WishlistMapper : ViewParamMapper<List<WishlistAccountResponse>, List<WishlistAccountParamResponse>> {
    override fun toViewParam(dataObject: List<WishlistAccountResponse>?): List<WishlistAccountParamResponse> =
        ListMapper(WishlistResponseMapper).toViewParams(dataObject)
}

object WishlistResponseMapper : ViewParamMapper<WishlistAccountResponse, WishlistAccountParamResponse> {
    override fun toViewParam(dataObject: WishlistAccountResponse?): WishlistAccountParamResponse =
        WishlistAccountParamResponse(
            id = dataObject?.id ?: 0,
            productId = dataObject?.productId ?: 0,
            userId = dataObject?.userId ?: 0,
            createdAt = dataObject?.createdAt.orEmpty(),
            updatedAt = dataObject?.updatedAt.orEmpty(),
            product = WishlistProductMapper.toViewParam(dataObject?.product)
        )
}

object WishlistProductMapper : ViewParamMapper<WishlistAccountResponse.Product, WishlistAccountParamResponse.Product> {
    override fun toViewParam(dataObject: WishlistAccountResponse.Product?): WishlistAccountParamResponse.Product =
        WishlistAccountParamResponse.Product(
            id = dataObject?.id ?: 0,
            name = dataObject?.name.orEmpty(),
            description = dataObject?.description.orEmpty(),
            basePrice = convertCurrency(dataObject?.basePrice ?: 0),
            imageUrl = dataObject?.imageUrl.orEmpty(),
            imageName = dataObject?.imageName.orEmpty(),
            location = dataObject?.location.orEmpty(),
            userId = dataObject?.userId ?: 0,
            status = dataObject?.status.orEmpty(),
            categories = dataObject?.categories?.joinToString { it.name.toString() } ?: "Empty Category"
        )
}