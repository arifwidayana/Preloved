package com.arifwidayana.shared.data.network.model.mapper.bid

import com.arifwidayana.shared.data.network.model.request.bid.BidProductParamRequest
import com.arifwidayana.shared.data.network.model.request.bid.BidProductRequest
import com.arifwidayana.shared.data.network.model.response.bid.order.OrderProductParamResponse
import com.arifwidayana.shared.data.network.model.response.bid.order.OrderProductResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.mapper.DataObjectMapper
import com.arifwidayana.shared.utils.mapper.ViewParamMapper

object BidRequestMapper : DataObjectMapper<BidProductRequest, BidProductParamRequest> {
    override fun toDataObject(viewParam: BidProductParamRequest?): BidProductRequest =
        BidProductRequest(
            productId = viewParam?.productId,
            bidPrice = viewParam?.bidPrice
        )
}

object BidResponseMapper : ViewParamMapper<OrderProductResponse, OrderProductParamResponse> {
    override fun toViewParam(dataObject: OrderProductResponse?): OrderProductParamResponse =
        OrderProductParamResponse(
            id = dataObject?.id ?: 0,
            productId = dataObject?.productId ?: 0,
            status = dataObject?.status.toString().replaceFirstChar { it.uppercase() },
            state = dataObject?.status != Constant.PENDING
        )
}

object BidProductResponseMapper :
    ViewParamMapper<BuyerProductParamResponse, OrderProductParamResponse> {
    override fun toViewParam(dataObject: BuyerProductParamResponse?): OrderProductParamResponse =
        OrderProductParamResponse(
            id = 0,
            productId = dataObject?.id ?: 0,
            status = dataObject?.status.orEmpty(),
            state = dataObject?.status != Constant.SOLD
        )
}