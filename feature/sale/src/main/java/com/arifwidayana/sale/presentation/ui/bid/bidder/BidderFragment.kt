package com.arifwidayana.sale.presentation.ui.bid.bidder

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import coil.load
import coil.size.Scale
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.sale.databinding.FragmentBidderBinding
import com.arifwidayana.sale.presentation.ui.bid.chat.ChatFragment
import com.arifwidayana.sale.presentation.ui.bid.status.StatusFragment
import com.arifwidayana.shared.data.network.model.response.sale.order.SaleOrderParamResponse
import com.arifwidayana.shared.utils.ext.source
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class BidderFragment : BaseFragment<FragmentBidderBinding, BidderViewModel>(
    FragmentBidderBinding::inflate
) {
    private val args by navArgs<BidderFragmentArgs>()
    private lateinit var dataProduct: SaleOrderParamResponse
    override val viewModel: BidderViewModel by inject()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModel.offerData(args.id)
    }

    private fun onClick() {
        binding.apply {
            sivBack.setOnClickListener {
                moveNavigateUp()
            }
            btnChatBuyer.setOnClickListener {
                ChatFragment(dataProduct).show(childFragmentManager, null)
            }
            btnChangeStatus.setOnClickListener {
                StatusFragment(dataProduct).show(childFragmentManager, null)
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.offerDataResult.collect {
                        it.source(
                            doOnLoading = {
                            },
                            doOnSuccess = { source ->
                                setStateView(source.payload)
                            },
                            doOnError = { error ->
                                showMessageSnackBar(true, exception = error.exception)
                            }
                        )
                    }
                }
            }
        }
    }

    private fun setStateView(data: SaleOrderParamResponse?) {
        binding.apply {
            data?.let {
                dataProduct = it
                sivProfileUser.load(it.userOfferImageUrl) { scale(Scale.FILL) }
                sivProductImage.load(it.imageProductUrl) { scale(Scale.FILL) }
                tvNameUser.text = it.userOfferName
                tvLocationUser.text = it.userOfferLocation
                tvStatusProduct.text = it.status
                tvTransactionDate.text = it.transactionDate
                tvProductName.text = it.productName
                tvBasePrice.text = it.basePrice
                tvBidPrice.text = it.price
            }
        }
    }
}