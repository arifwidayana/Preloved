package com.arifwidayana.shared.data.network.model.request.bid.wishlist

import com.google.gson.annotations.SerializedName

data class WishlistProductRequest(
    @SerializedName("product_id")
    val productId: Int
)