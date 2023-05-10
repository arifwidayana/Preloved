package com.arifwidayana.shared.data.network.model.mapper.sale

import com.arifwidayana.shared.data.network.model.response.sale.product.SaleProductResponse
import com.arifwidayana.shared.data.network.model.response.sale.sold.SaleProductSoldParamResponse
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.DateUtils
import com.arifwidayana.shared.utils.ext.convertCurrency
import com.arifwidayana.shared.utils.mapper.ListMapper
import com.arifwidayana.shared.utils.mapper.ViewParamMapper

object SaleProductSoldMapper : ViewParamMapper<List<SaleProductResponse>, List<SaleProductSoldParamResponse>> {
    override fun toViewParam(dataObject: List<SaleProductResponse>?): List<SaleProductSoldParamResponse> =
        ListMapper(ListSaleProductSoldMapper).toViewParams(dataObject)
}

object ListSaleProductSoldMapper : ViewParamMapper<SaleProductResponse, SaleProductSoldParamResponse> {
    override fun toViewParam(dataObject: SaleProductResponse?): SaleProductSoldParamResponse =
        SaleProductSoldParamResponse(
            id = dataObject?.id ?: 0,
            name = dataObject?.name.orEmpty(),
            basePrice = convertCurrency(dataObject?.basePrice ?: 0),
            imageUrl = dataObject?.imageUrl.orEmpty(),
            updatedAt = DateUtils.convertDateTime(
                time = dataObject?.updatedAt,
                pattern = Constant.PATTERN_FORMAT_2
            ),
            status = "Product Sold",
            categories = dataObject?.categories?.joinToString { it.name.toString() } ?: "Empty Category"
        )
}