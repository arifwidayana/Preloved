package com.arifwidayana.shared.data.network.model.mapper.sale

import com.arifwidayana.shared.data.network.model.response.sale.product.SaleProductParamResponse
import com.arifwidayana.shared.data.network.model.response.sale.product.SaleProductResponse
import com.arifwidayana.shared.utils.ext.convertCurrency
import com.arifwidayana.shared.utils.mapper.ListMapper
import com.arifwidayana.shared.utils.mapper.ViewParamMapper

object SaleProductMapper : ViewParamMapper<List<SaleProductResponse>, List<SaleProductParamResponse>> {
    override fun toViewParam(dataObject: List<SaleProductResponse>?): List<SaleProductParamResponse> =
        ListMapper(ListSaleProductMapper).toViewParams(dataObject)
}

object ListSaleProductMapper : ViewParamMapper<SaleProductResponse, SaleProductParamResponse> {
    override fun toViewParam(dataObject: SaleProductResponse?): SaleProductParamResponse =
        SaleProductParamResponse(
            id = dataObject?.id ?: 0,
            name = dataObject?.name.orEmpty(),
            basePrice = convertCurrency(dataObject?.basePrice ?: 0),
            imageUrl = dataObject?.imageUrl.orEmpty(),
            categories = dataObject?.categories?.joinToString { it.name.toString() } ?: "Empty Category"
        )
}