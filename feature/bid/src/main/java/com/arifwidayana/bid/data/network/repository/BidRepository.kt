package com.arifwidayana.bid.data.network.repository

import com.arifwidayana.bid.data.network.datasource.BidDatasource
import com.arifwidayana.core.wrapper.DataResource
import com.arifwidayana.shared.data.network.model.request.bid.WishlistProductRequest
import com.arifwidayana.shared.data.network.model.response.bid.StatusWishlistProductResponse
import com.arifwidayana.shared.data.network.model.response.bid.WishlistProductResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductResponse
import com.arifwidayana.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias ProductDataResource = DataResource<BuyerProductResponse>
typealias WishlistProduct = DataResource<List<WishlistProductResponse>>
typealias StatusWishlistProduct = DataResource<StatusWishlistProductResponse>

interface BidRepository {
    suspend fun detailProduct(idProduct: Int): Flow<ProductDataResource>
    suspend fun postWishlistProduct(wishlistProductRequest: WishlistProductRequest): Flow<StatusWishlistProduct>
    suspend fun wishlistProduct(): Flow<WishlistProduct>
    suspend fun deleteWishlistProduct(idWishlist: Int): Flow<StatusWishlistProduct>
}

class BidRepositoryImpl(
    private val bidDatasource: BidDatasource
) : BidRepository, Repository() {
    override suspend fun detailProduct(idProduct: Int): Flow<ProductDataResource> = flow {
        emit(safeNetworkCall { bidDatasource.detailProduct(idProduct) })
    }

    override suspend fun postWishlistProduct(wishlistProductRequest: WishlistProductRequest): Flow<StatusWishlistProduct> = flow {
        emit(safeNetworkCall { bidDatasource.postWishlistProduct(wishlistProductRequest) })
    }

    override suspend fun wishlistProduct(): Flow<WishlistProduct> = flow {
        emit(safeNetworkCall { bidDatasource.wishlistProduct() })
    }

    override suspend fun deleteWishlistProduct(idWishlist: Int): Flow<StatusWishlistProduct> = flow {
        emit(safeNetworkCall { bidDatasource.deleteWishlistProduct(idWishlist) })
    }
}