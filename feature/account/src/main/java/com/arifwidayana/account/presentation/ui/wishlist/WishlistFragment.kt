package com.arifwidayana.account.presentation.ui.wishlist

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.arifwidayana.account.R
import com.arifwidayana.account.databinding.FragmentWishlistBinding
import com.arifwidayana.account.presentation.adapter.wishlist.WishlistAdapter
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.shared.utils.ext.source
import org.koin.android.ext.android.inject

class WishlistFragment : BaseFragment<FragmentWishlistBinding, WishlistViewModel>(
    FragmentWishlistBinding::inflate
) {
    override val viewModel: WishlistViewModel by inject()

    override fun initView() {
        onView()
    }

    private fun onView() {
        viewModel.getWishlist()
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.getWishlistResult.collect {
                it.source(
                    doOnSuccess = { result ->
                        setStateRecycler(result.payload)
                    },
                    doOnError = { error ->
                        showMessageSnackBar(true, exception = error.exception)
                    }
                )
            }
        }
    }

    private fun setStateRecycler(data: WishlistDataResult?) {
        binding.apply {
            val adapter = WishlistAdapter {
                val parcel = Bundle().apply { putInt("id", it) }
                moveNav(R.id.action_wishlistFragment_to_bid_nav, parcel)
            }
            adapter.submitList(data)
            rvWishlist.adapter = adapter
        }
    }
}