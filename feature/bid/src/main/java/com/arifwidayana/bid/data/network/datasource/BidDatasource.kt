package com.arifwidayana.bid.data.network.datasource

import com.arifwidayana.bid.data.network.service.BidService
import com.arifwidayana.core.base.BaseDefaultResponse
import com.arifwidayana.shared.data.network.model.request.bid.BidProductRequest
import com.arifwidayana.shared.data.network.model.request.bid.order.OrderProductRequest
import com.arifwidayana.shared.data.network.model.request.bid.wishlist.WishlistProductRequest
import com.arifwidayana.shared.data.network.model.response.bid.order.BidProductResponse
import com.arifwidayana.shared.data.network.model.response.bid.order.OrderProductResponse
import com.arifwidayana.shared.data.network.model.response.bid.product.StatusWishlistProductResponse
import com.arifwidayana.shared.data.network.model.response.bid.product.WishlistProductResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductResponse

interface BidDatasource {
    suspend fun detailProduct(idProduct: Int): BuyerProductResponse
    suspend fun postWishlistProduct(wishlistProductRequest: WishlistProductRequest): StatusWishlistProductResponse
    suspend fun wishlistProduct(): List<WishlistProductResponse>
    suspend fun deleteWishlistProduct(idWishlist: Int): StatusWishlistProductResponse
    suspend fun postOrderProduct(bidProductRequest: BidProductRequest): BidProductResponse
    suspend fun getOrderProduct(): List<OrderProductResponse>
    suspend fun updateOrderProduct(idOrder: Int, orderProductRequest: OrderProductRequest): OrderProductResponse
    suspend fun deleteOrderProduct(idOrder: Int): BaseDefaultResponse
}

class BidDatasourceImpl(
    private val bidService: BidService
) : BidDatasource {
    override suspend fun detailProduct(idProduct: Int): BuyerProductResponse {
        return bidService.detailProduct(idProduct)
    }

    override suspend fun postWishlistProduct(wishlistProductRequest: WishlistProductRequest): StatusWishlistProductResponse {
        return bidService.postWishlistProduct(wishlistProductRequest)
    }

    override suspend fun wishlistProduct(): List<WishlistProductResponse> {
        return bidService.wishlistProduct()
    }

    override suspend fun deleteWishlistProduct(idWishlist: Int): StatusWishlistProductResponse {
        return bidService.deleteWishlistProduct(idWishlist)
    }

    override suspend fun postOrderProduct(bidProductRequest: BidProductRequest): BidProductResponse {
        return bidService.postOrderProduct(bidProductRequest)
    }

    override suspend fun getOrderProduct(): List<OrderProductResponse> {
        return bidService.getOrderProduct()
    }

    override suspend fun updateOrderProduct(
        idOrder: Int,
        orderProductRequest: OrderProductRequest
    ): OrderProductResponse {
        return bidService.updateOrderProduct(
            idOrder = idOrder,
            orderProductRequest = orderProductRequest
        )
    }

    override suspend fun deleteOrderProduct(idOrder: Int): BaseDefaultResponse {
        return bidService.deleteOrderProduct(idOrder)
    }
}