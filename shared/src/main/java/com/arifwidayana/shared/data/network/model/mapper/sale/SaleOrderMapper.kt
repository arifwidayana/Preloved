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
            userOfferName = StringUtils.firstCharUpperCase(dataObject?.user?.fullName.orEmpty()),
            userOfferImageUrl = dataObject?.user?.imageUrl.orEmpty(),
            userPhoneNumber = dataObject?.user?.phoneNumber.orEmpty(),
            userOfferLocation = StringUtils.firstCharUpperCase(dataObject?.user?.city.orEmpty()),
            price = "Offer Price in ${convertCurrency(dataObject?.price ?: 0)}",
            transactionDate = DateUtils.convertDateTime(
                time = dataObject?.transactionDate,
                pattern = Constant.PATTERN_FORMAT_2
            ),
            productName = StringUtils.firstCharUpperCase(dataObject?.productName.orEmpty()),
            basePrice = StringUtils.strikeThrough(convertCurrency(dataObject?.basePrice ?: 0)),
            imageProductUrl = dataObject?.imageProduct.orEmpty(),
            status = "Product Offer"
        )
}