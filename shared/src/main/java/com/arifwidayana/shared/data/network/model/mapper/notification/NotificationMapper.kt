package com.arifwidayana.shared.data.network.model.mapper.notification

import android.text.Spannable
import androidx.core.text.toSpannable
import com.arifwidayana.shared.data.network.model.response.notification.NotificationParamResponse
import com.arifwidayana.shared.data.network.model.response.notification.NotificationResponse
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.DateUtils
import com.arifwidayana.shared.utils.StringUtils
import com.arifwidayana.shared.utils.ext.convertCurrency
import com.arifwidayana.shared.utils.mapper.ListMapper
import com.arifwidayana.shared.utils.mapper.ViewParamMapper
import com.arifwidayana.style.R

typealias NotifyMap = NotificationResponse
typealias NotifyParamMap = NotificationParamResponse

object NotificationMapper : ViewParamMapper<List<NotifyMap>, List<NotifyParamMap>> {
    override fun toViewParam(dataObject: List<NotifyMap>?): List<NotifyParamMap> =
        ListMapper(NotificationResponseMapper).toViewParams(dataObject)
}

object NotificationResponseMapper : ViewParamMapper<NotifyMap, NotifyParamMap> {
    override fun toViewParam(dataObject: NotifyMap?): NotifyParamMap =
        NotifyParamMap(
            id = dataObject?.id ?: 0,
            productId = dataObject?.productId ?: 0,
            productName = dataObject?.productName.orEmpty(),
            basePrice = NotifyMapper.isBasePrice(dataObject),
            bidPrice = "${StringUtils.firstCharUpperCase(dataObject?.status.orEmpty())} ${convertCurrency(dataObject?.bidPrice ?: 0)}",
            imageUrl = dataObject?.imageUrl.orEmpty(),
            transactionDate = DateUtils.convertDateTime(
                time = dataObject?.transactionDate,
                pattern = Constant.PATTERN_FORMAT_2
            ),
            status = dataObject?.status.orEmpty(),
            statusProduct = "Product ${StringUtils.firstCharUpperCase(dataObject?.status.orEmpty())}",
            read = dataObject?.read ?: false,
            notificationType = dataObject?.notificationType.orEmpty(),
            state = NotifyMapper.setStateResult(dataObject)
        )
}

object NotifyMapper {
    fun isBasePrice(dataObject: NotifyMap?): Spannable {
        return when (dataObject?.status) {
            Constant.BID, Constant.DECLINED, Constant.ACCEPTED -> {
                StringUtils.strikeThrough(convertCurrency(dataObject.basePrice?.toInt() ?: 0))
            }
            else -> {
                convertCurrency(dataObject?.basePrice?.toInt() ?: 0).toSpannable()
            }
        }
    }

    fun setStateResult(dataObject: NotifyMap?): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()
        dataObject?.let { res ->
            isNotificationMessage(res).let {
                result.add(it)
            }
        }
        return result
    }

    private fun isNotificationMessage(dataObject: NotifyMap?): Pair<Int, Int> {
        return when (dataObject?.status) {
            Constant.BID -> {
                when (dataObject.notificationType) {
                    Constant.BUYER -> {
                        Pair(Constant.NOTIFICATION_MESSAGE, R.string.message_product_bid_buyer)
                    }
                    Constant.SELLER -> {
                        if (dataObject.product.status == Constant.SOLD) {
                            Pair(Constant.NOTIFICATION_MESSAGE, R.string.message_product_bid_sold)
                        } else {
                            Pair(Constant.NOTIFICATION_MESSAGE, R.string.message_product_bid_seller)
                        }
                    }
                    else -> {
                        Pair(Constant.NOTIFICATION_MESSAGE, R.string.message_product_null)
                    }
                }
            }
            Constant.DECLINED -> {
                when (dataObject.notificationType) {
                    Constant.BUYER -> {
                        Pair(Constant.NOTIFICATION_MESSAGE, R.string.message_product_declined_buyer)
                    }
                    Constant.SELLER -> {
                        Pair(Constant.NOTIFICATION_MESSAGE, R.string.message_product_declined_seller)
                    }
                    else -> {
                        Pair(Constant.NOTIFICATION_MESSAGE, R.string.message_product_null)
                    }
                }
            }
            Constant.ACCEPTED -> {
                when (dataObject.notificationType) {
                    Constant.BUYER -> {
                        Pair(Constant.NOTIFICATION_MESSAGE, R.string.message_product_accepted_buyer)
                    }
                    Constant.SELLER -> {
                        Pair(Constant.NOTIFICATION_MESSAGE, R.string.message_product_accepted_seller)
                    }
                    else -> {
                        Pair(Constant.NOTIFICATION_MESSAGE, R.string.message_product_null)
                    }
                }
            }
            else -> {
                Pair(Constant.NOTIFICATION_MESSAGE, R.string.message_product_added)
            }
        }
    }
}