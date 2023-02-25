package com.arifwidayana.bid.data.network.repository

import com.arifwidayana.bid.data.network.datasource.BidDatasource
import com.arifwidayana.core.base.BaseDefaultResponse
import com.arifwidayana.core.wrapper.DataResource
import com.arifwidayana.shared.data.network.model.request.bid.BidProductRequest
import com.arifwidayana.shared.data.network.model.request.bid.order.OrderProductRequest
import com.arifwidayana.shared.data.network.model.request.bid.wishlist.WishlistProductRequest
import com.arifwidayana.shared.data.network.model.response.bid.order.BidProductResponse
import com.arifwidayana.shared.data.network.model.response.bid.order.OrderProductResponse
import com.arifwidayana.shared.data.network.model.response.bid.product.StatusWishlistProductResponse
import com.arifwidayana.shared.data.network.model.response.bid.product.WishlistProductResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductResponse
import com.arifwidayana.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias ProductDataResource = DataResource<BuyerProductResponse>
typealias WishlistProductDataResource = DataResource<List<WishlistProductResponse>>
typealias StatusWishlistProductDataResource = DataResource<StatusWishlistProductResponse>
typealias BidProductDataResource = DataResource<BidProductResponse>
typealias OrderProduct = OrderProductResponse
typealias OrderProductDataResource = DataResource<List<OrderProduct>>
typealias UpdateProductDataResource = DataResource<OrderProduct>
typealias DeleteProductDataResource = DataResource<BaseDefaultResponse>

interface BidRepository {
    suspend fun detailProduct(idProduct: Int): Flow<ProductDataResource>
    suspend fun postWishlistProduct(wishlistProductRequest: WishlistProductRequest): Flow<StatusWishlistProductDataResource>
    suspend fun wishlistProduct(): Flow<WishlistProductDataResource>
    suspend fun deleteWishlistProduct(idWishlist: Int): Flow<StatusWishlistProductDataResource>
    suspend fun postOrderProduct(bidProductRequest: BidProductRequest): Flow<BidProductDataResource>
    suspend fun getOrderProduct(): Flow<OrderProductDataResource>
    suspend fun updateOrderProduct(idOrder: Int, orderProductRequest: OrderProductRequest): Flow<UpdateProductDataResource>
    suspend fun deleteOrderProduct(idOrder: Int): Flow<DeleteProductDataResource>
}

class BidRepositoryImpl(
    private val bidDatasource: BidDatasource
) : BidRepository, Repository() {
    override suspend fun detailProduct(idProduct: Int): Flow<ProductDataResource> = flow {
        emit(safeNetworkCall { bidDatasource.detailProduct(idProduct) })
    }

    override suspend fun postWishlistProduct(wishlistProductRequest: WishlistProductRequest): Flow<StatusWishlistProductDataResource> = flow {
        emit(safeNetworkCall { bidDatasource.postWishlistProduct(wishlistProductRequest) })
    }

    override suspend fun wishlistProduct(): Flow<WishlistProductDataResource> = flow {
        emit(safeNetworkCall { bidDatasource.wishlistProduct() })
    }

    override suspend fun deleteWishlistProduct(idWishlist: Int): Flow<StatusWishlistProductDataResource> = flow {
        emit(safeNetworkCall { bidDatasource.deleteWishlistProduct(idWishlist) })
    }

    override suspend fun postOrderProduct(bidProductRequest: BidProductRequest): Flow<BidProductDataResource> = flow {
        emit(safeNetworkCall { bidDatasource.postOrderProduct(bidProductRequest) })
    }

    override suspend fun getOrderProduct(): Flow<OrderProductDataResource> = flow {
        emit(safeNetworkCall { bidDatasource.getOrderProduct() })
    }

    override suspend fun updateOrderProduct(
        idOrder: Int,
        orderProductRequest: OrderProductRequest
    ): Flow<UpdateProductDataResource> = flow {
        emit(safeNetworkCall { bidDatasource.updateOrderProduct(idOrder, orderProductRequest) })
    }

    override suspend fun deleteOrderProduct(idOrder: Int): Flow<DeleteProductDataResource> = flow {
        emit(safeNetworkCall { bidDatasource.deleteOrderProduct(idOrder) })
    }
}