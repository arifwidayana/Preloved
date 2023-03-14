package com.arifwidayana.account.presentation.ui.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.account.domain.wishlist.WishlistUseCase
import com.arifwidayana.core.wrapper.ViewResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WishlistViewModel(
    private val wishlistUseCase: WishlistUseCase
) : WishlistContract, ViewModel() {
    private val _getWishlistResult = MutableStateFlow<ViewResource<WishlistDataResult>>(ViewResource.Empty())
    override val getWishlistResult: StateFlow<ViewResource<WishlistDataResult>> = _getWishlistResult.asStateFlow()

    override fun getWishlist() {
        viewModelScope.launch {
            wishlistUseCase().collect {
                _getWishlistResult.value = it
            }
        }
    }
}