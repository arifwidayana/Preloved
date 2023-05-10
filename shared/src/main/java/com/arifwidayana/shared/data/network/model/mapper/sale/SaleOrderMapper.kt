package com.arifwidayana.shared.data.network.model.mapper.sale

import com.arifwidayana.shared.data.network.model.response.sale.order.SaleOrderParamResponse
import com.arifwidayana.shared.data.network.model.response.sale.order.SaleOrderResponse
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.DateUtils
import com.arifwidayana.shared.utils.StringUtils
import com.arifwidayana.shared.utils.ext.convertCurrency
import com.arifwidayana.shared.utils.mapper.ListMapper
import com.arifwidayana.shared.utils.mapper.ViewParamMapper

object SaleOrderMapper : ViewParamMapper<List<SaleOrderResponse>, List<SaleOrderParamResponse>> {
    override fun toViewParam(dataObject: List<SaleOrderResponse>?): List<SaleOrderParamResponse> =
        ListMapper(ListSaleOrderMapper).toViewParams(dataObject)
}

object ListSaleOrderMapper : ViewParamMapper<SaleOrderResponse, SaleOrderParamResponse> {
    override fun toViewParam(dataObject: SaleOrderResponse?): SaleOrderParamResponse =
        SaleOrderParamResponse(
            id = dataObject?.id ?: 0,
            productId = dataObject?.productId ?: 0,
            price = "Offer Price in ${convertCurrency(dataObject?.price ?: 0)}",
            transactionDate = DateUtils.convertDateTime(
                time = dataObject?.transactionDate,
                pattern = Constant.PATTERN_FORMAT_2
            ),
            productName = StringUtils.firstCharUpperCase(
                words = dataObject?.productName.orEmpty()
            ),
            basePrice = StringUtils.strikeThrough(convertCurrency(dataObject?.basePrice ?: 0)),
            imageProduct = dataObject?.imageProduct.orEmpty(),
            status = "Product Offer"
        )
}