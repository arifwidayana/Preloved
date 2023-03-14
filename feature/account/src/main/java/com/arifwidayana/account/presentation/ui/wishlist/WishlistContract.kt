package com.arifwidayana.account.presentation.ui.wishlist

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.account.wishlist.WishlistAccountParamResponse
import kotlinx.coroutines.flow.StateFlow

typealias WishlistDataResult = List<WishlistAccountParamResponse>

interface WishlistContract {
    val getWishlistResult: StateFlow<ViewResource<WishlistDataResult>>
    fun getWishlist()
}