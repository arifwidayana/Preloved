package com.arifwidayana.bid.data.network.datasource

import com.arifwidayana.bid.data.network.service.BidService
import com.arifwidayana.shared.data.network.model.request.bid.WishlistProductRequest
import com.arifwidayana.shared.data.network.model.response.bid.StatusWishlistProductResponse
import com.arifwidayana.shared.data.network.model.response.bid.WishlistProductResponse
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductResponse

interface BidDatasource {
    suspend fun detailProduct(idProduct: Int): BuyerProductResponse
    suspend fun postWishlistProduct(wishlistProductRequest: WishlistProductRequest): StatusWishlistProductResponse
    suspend fun wishlistProduct(): List<WishlistProductResponse>
    suspend fun deleteWishlistProduct(idWishlist: Int): StatusWishlistProductResponse
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
}