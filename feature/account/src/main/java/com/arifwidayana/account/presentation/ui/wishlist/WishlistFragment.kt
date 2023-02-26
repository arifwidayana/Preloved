package com.arifwidayana.account.presentation.ui.wishlist

import com.arifwidayana.account.databinding.FragmentWishlistBinding
import com.arifwidayana.core.base.BaseFragment
import org.koin.android.ext.android.inject

class WishlistFragment : BaseFragment<FragmentWishlistBinding, WishlistViewModel>(
    FragmentWishlistBinding::inflate
) {
    override val viewModel: WishlistViewModel by inject()

    override fun initView() {
    }

    override fun observeData() {
    }
}