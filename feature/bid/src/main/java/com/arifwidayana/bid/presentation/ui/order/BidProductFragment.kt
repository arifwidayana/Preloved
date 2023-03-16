package com.arifwidayana.bid.presentation.ui.order

import android.net.Uri
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import coil.size.Scale
import com.arifwidayana.bid.databinding.FragmentBidProductBinding
import com.arifwidayana.bid.presentation.ui.product.ProductParamDataResponse
import com.arifwidayana.core.base.BaseBottomSheetDialogFragment
import com.arifwidayana.shared.data.network.model.request.bid.BidProductParamRequest
import com.arifwidayana.shared.utils.Navigation.ORDER_URI
import com.arifwidayana.shared.utils.ext.source
import com.arifwidayana.style.R
import org.koin.android.ext.android.inject

class BidProductFragment(
    private val product: ProductParamDataResponse
) : BaseBottomSheetDialogFragment<FragmentBidProductBinding, BidProductViewModel>(
    FragmentBidProductBinding::inflate
) {
    override val viewModel: BidProductViewModel by inject()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        setStateProduct()
    }

    private fun onClick() {
        binding.apply {
            btnBidProduct.setOnClickListener {
                viewModel.bidProduct(
                    BidProductParamRequest(
                        productId = product.id,
                        bidPrice = etBidPrice.text.toString().toInt()
                    )
                )
            }
        }
    }

    override fun observeData() {
        lifecycleScope.apply {
            launchWhenStarted {
                viewModel.bidProductResult.collect {
                    it.source(
                        doOnSuccess = { result ->
                            showMessageToast(true, result.message)
                            dismiss()
                            findNavController().navigate(deepLink = Uri.parse(ORDER_URI))
                        },
                        doOnError = { error ->
                            showMessageToast(true, exception = error.exception)
                            dismiss()
                        }
                    )
                }
            }
        }
    }

    private fun setStateProduct() {
        binding.apply {
            sivImageProduct.load(product.imageUrl) {
                placeholder(R.color.gray)
                scale(Scale.FILL)
            }
            tvNameProduct.text = product.name
            tvCategoryProduct.text = product.categories
            tvPriceProduct.text = product.basePrice
        }
    }
}