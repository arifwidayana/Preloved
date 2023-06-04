package com.arifwidayana.shared.data.network.model.mapper.sale

import com.arifwidayana.shared.data.network.model.response.sale.bidder.SaleBidderParamResponse
import com.arifwidayana.shared.data.network.model.response.sale.bidder.SaleBidderResponse
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.mapper.ViewParamMapper

object SaleBidderMapper : ViewParamMapper<SaleBidderResponse, SaleBidderParamResponse> {
    override fun toViewParam(dataObject: SaleBidderResponse?): SaleBidderParamResponse =
        SaleBidderParamResponse(
            status = when (dataObject?.status) {
                Constant.ACCEPTED -> "You was accepted the offer"
                Constant.DECLINED -> "You have been declined"
                else -> "Unknown status"
            }
        )
}