package com.arifwidayana.shared.data.network.model.mapper.sale

import com.arifwidayana.shared.data.network.model.response.sale.history.HistoryParamResponse
import com.arifwidayana.shared.data.network.model.response.sale.history.HistoryResponse
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.DateUtils
import com.arifwidayana.shared.utils.StringUtils
import com.arifwidayana.shared.utils.ext.convertCurrency
import com.arifwidayana.shared.utils.mapper.ListMapper
import com.arifwidayana.shared.utils.mapper.ViewParamMapper
import com.arifwidayana.style.R

typealias ListHistoryMap = List<HistoryResponse>
typealias ListHistoryParamMap = List<HistoryParamResponse>

object HistoryMapper : ViewParamMapper<ListHistoryMap, ListHistoryParamMap> {
    override fun toViewParam(dataObject: ListHistoryMap?): ListHistoryParamMap =
        ListMapper(ListHistoryMapper).toViewParams(dataObject)
}

object ListHistoryMapper : ViewParamMapper<HistoryResponse, HistoryParamResponse> {
    override fun toViewParam(dataObject: HistoryResponse?): HistoryParamResponse =
        HistoryParamResponse(
            productName = dataObject?.productName.orEmpty(),
            price = "Offered ${convertCurrency(dataObject?.price ?: 0)}",
            transactionDate = DateUtils.convertDateTime(
                time = dataObject?.transactionDate,
                pattern = Constant.PATTERN_FORMAT_3
            ),
            imageUrl = dataObject?.imageUrl.orEmpty(),
            product = HistoryProductMapper.toViewParam(dataObject?.product),
            status = isNotificationMessage(dataObject)
        )

    private fun isNotificationMessage(dataObject: HistoryResponse?): Pair<Int, Int> {
        return when (dataObject?.status) {
            Constant.DECLINED -> Pair(
                Constant.SALE_HISTORY_STATUS,
                R.string.status_sale_history_declined
            )
            Constant.ACCEPTED -> Pair(
                Constant.SALE_HISTORY_STATUS,
                R.string.status_sale_history_accepted
            )
            else -> Pair(Constant.NOTIFICATION_MESSAGE, R.string.status_sale_history_unknown)
        }
    }
}

object HistoryProductMapper :
    ViewParamMapper<HistoryResponse.Product, HistoryParamResponse.Product> {
    override fun toViewParam(dataObject: HistoryResponse.Product?): HistoryParamResponse.Product =
        HistoryParamResponse.Product(
            id = dataObject?.id ?: 0,
            basePrice = StringUtils.strikeThrough(convertCurrency(dataObject?.basePrice ?: 0)),
            status = dataObject?.status.orEmpty()
        )
}