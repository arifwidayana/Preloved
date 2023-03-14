package com.arifwidayana.shared.data.network.model.mapper.account.order

import com.arifwidayana.shared.data.network.model.response.account.order.OrderAccountParamResponse
import com.arifwidayana.shared.data.network.model.response.account.order.OrderAccountResponse
import com.arifwidayana.shared.utils.DateUtils
import com.arifwidayana.shared.utils.ext.convertCurrency
import com.arifwidayana.shared.utils.mapper.ListMapper
import com.arifwidayana.shared.utils.mapper.ViewParamMapper

object OrderListMapper : ViewParamMapper<List<OrderAccountResponse>, List<OrderAccountParamResponse>> {
    override fun toViewParam(dataObject: List<OrderAccountResponse>?): List<OrderAccountParamResponse> =
        ListMapper(OrderMapper).toViewParams(dataObject)
}

object OrderMapper : ViewParamMapper<OrderAccountResponse, OrderAccountParamResponse> {
    override fun toViewParam(dataObject: OrderAccountResponse?): OrderAccountParamResponse =
        OrderAccountParamResponse(
            id = dataObject?.id ?: 0,
            productId = dataObject?.productId ?: 0,
            buyerId = dataObject?.buyerId ?: 0,
            price = convertCurrency(dataObject?.price ?: 0),
            transactionDate = DateUtils.convertDateTime(dataObject?.transactionDate.orEmpty()),
            productName = dataObject?.productName.orEmpty(),
            basePrice = convertCurrency(dataObject?.basePrice ?: 0),
            imageProduct = dataObject?.imageProduct.orEmpty(),
            status = dataObject?.status.orEmpty(),
            user = OrderUserMapper.toViewParam(dataObject?.user),
            product = OrderProductMapper.toViewParam(dataObject?.product)
        )
}

object OrderUserMapper : ViewParamMapper<OrderAccountResponse.User, OrderAccountParamResponse.User> {
    override fun toViewParam(dataObject: OrderAccountResponse.User?): OrderAccountParamResponse.User =
        OrderAccountParamResponse.User(
            id = dataObject?.id ?: 0,
            fullName = dataObject?.fullName.orEmpty(),
            email = dataObject?.email.orEmpty(),
            phoneNumber = dataObject?.phoneNumber.orEmpty(),
            address = dataObject?.address.orEmpty(),
            city = dataObject?.city.orEmpty()
        )
}

object OrderProductMapper : ViewParamMapper<OrderAccountResponse.Product, OrderAccountParamResponse.Product> {
    override fun toViewParam(dataObject: OrderAccountResponse.Product?): OrderAccountParamResponse.Product =
        OrderAccountParamResponse.Product(
            name = dataObject?.name.orEmpty(),
            description = dataObject?.description.orEmpty(),
            basePrice = convertCurrency(dataObject?.basePrice ?: 0),
            imageUrl = dataObject?.imageUrl.orEmpty(),
            imageName = dataObject?.imageName.orEmpty(),
            location = dataObject?.location.orEmpty(),
            userId = dataObject?.userId ?: 0,
            status = dataObject?.status.orEmpty(),
            user = OrderProductUserMapper.toViewParam(dataObject?.user)
        )
}

object OrderProductUserMapper : ViewParamMapper<OrderAccountResponse.Product.User, OrderAccountParamResponse.Product.User> {
    override fun toViewParam(dataObject: OrderAccountResponse.Product.User?): OrderAccountParamResponse.Product.User =
        OrderAccountParamResponse.Product.User(
            id = dataObject?.id ?: 0,
            fullName = dataObject?.fullName.orEmpty(),
            email = dataObject?.email.orEmpty(),
            phoneNumber = dataObject?.phoneNumber.orEmpty(),
            address = dataObject?.address.orEmpty(),
            city = dataObject?.city.orEmpty()
        )
}