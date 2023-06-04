package com.arifwidayana.sale.presentation.ui.bid.chat

import androidx.lifecycle.ViewModel
import coil.load
import coil.size.Scale
import com.arifwidayana.core.base.BaseBottomSheetDialogFragment
import com.arifwidayana.sale.databinding.FragmentChatBinding
import com.arifwidayana.shared.data.network.model.response.sale.order.SaleOrderParamResponse
import com.arifwidayana.shared.utils.ext.whatsAppMessage
import org.koin.android.ext.android.inject

class ChatFragment(
    private val dataProduct: SaleOrderParamResponse
) : BaseBottomSheetDialogFragment<FragmentChatBinding, ViewModel>(
    FragmentChatBinding::inflate
) {
    override val viewModel: ViewModel by inject()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        setStateView()
    }

    private fun onClick() {
        binding.apply {
            btnChatBuyer.setOnClickListener {
                navigate()
            }
        }
    }

    override fun observeData() {
    }

    private fun setStateView() {
        binding.apply {
            sivImageProduct.load(dataProduct.imageProductUrl) { scale(Scale.FILL) }
            sivProfileUser.load(dataProduct.userOfferImageUrl) { scale(Scale.FILL) }
            tvNameUser.text = dataProduct.userOfferName
            tvLocationUser.text = dataProduct.userOfferLocation
            tvNameProduct.text = dataProduct.productName
            tvBasePrice.text = dataProduct.basePrice
            tvPriceProduct.text = dataProduct.price
        }
    }

    private fun navigate() {
        whatsAppMessage(
            context = requireContext(),
            number = dataProduct.userPhoneNumber,
            message = "Please confirm your bid product *${dataProduct.productName}*\n" +
                "with id number *${dataProduct.productId}*\n" +
                "and *${dataProduct.price}*"
        )
    }
}