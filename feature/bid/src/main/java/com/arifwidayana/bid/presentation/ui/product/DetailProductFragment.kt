package com.arifwidayana.bid.presentation.ui.product

import androidx.lifecycle.lifecycleScope
import coil.load
import coil.size.Scale
import com.arifwidayana.bid.databinding.FragmentDetailProductBinding
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.shared.utils.ext.source
import com.arifwidayana.style.R
import org.koin.android.ext.android.inject

class DetailProductFragment : BaseFragment<FragmentDetailProductBinding, DetailProductViewModel>(
    FragmentDetailProductBinding::inflate
) {
    private var args: Int = 0
    override val viewModel: DetailProductViewModel by inject()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        args = requireArguments().getInt("id")
        viewModel.apply {
            detailProduct(args)
        }
    }

    private fun onClick() {
        binding.apply {
            ibBack.setOnClickListener {
                moveNavigateUp()
            }
            btnWishlist.setOnClickListener {
//                viewModel.wishlistProduct(args)
            }
            btnBid.setOnClickListener {

            }
        }
    }

    override fun observeData() {
        lifecycleScope.apply {
            launchWhenStarted {
                viewModel.detailProductResult.collect {
                    it.source(
                        doOnSuccess = { result ->
                            setStateProduct(result.payload)
                        },
                        doOnError = { error ->
                            showMessageSnackBar(true, error.exception.toString())
                        }
                    )
                }
            }
            launchWhenStarted {
                viewModel.checkWishlistProductResult.collect {
                    it.source(
                        doOnSuccess = { result ->
                            setStateWishlist(result.payload)
                        },
                        doOnError = { error ->
                            showMessageSnackBar(true, error.exception.toString())
                        }
                    )
                }
            }
            launchWhenStarted {
                viewModel.wishlistProductResult.collect {
                    it.source(
                        doOnSuccess = { result ->
                            setStateWishlist(result.payload)
                            showMessageSnackBar(true, result.message)
                        },
                        doOnError = { error ->
                            showMessageSnackBar(true, error.exception?.message)
                        }
                    )
                }
            }
        }
    }

    private fun setStateWishlist(data: Boolean?) {
        data?.let {
            when (it) {
                true -> setViewWishlist(R.drawable.ic_selected_wishlist)
                false -> setViewWishlist(R.drawable.ic_unselected_wishlist)
            }
        }
    }

    private fun setViewWishlist(drawable: Int) {
        binding.btnWishlist.setImageResource(drawable)
    }

    private fun setStateProduct(data: ProductParamDataResponse?) {
        binding.apply {
            data?.let {
                sivImageProduct.load(it.imageUrl) {
                    placeholder(R.color.gray)
                    scale(Scale.FIT)
                }
                sivPhotoSeller.load(it.user.imageUrl) {
                    placeholder(R.color.gray)
                    scale(Scale.FIT)
                }
                tvTitleProduct.text = it.name
                tvCategoryProduct.text = it.categories
                tvPriceProduct.text = it.basePrice.toString()
                tvUsernameSeller.text = it.user.fullName
                tvLocationSeller.text = it.location
                tvDescriptionProduct.text = it.description
            }
        }
    }
}