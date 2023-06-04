package com.arifwidayana.sale.presentation.ui.bid.status

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.arifwidayana.core.base.BaseBottomSheetDialogFragment
import com.arifwidayana.sale.databinding.FragmentStatusBinding
import com.arifwidayana.shared.data.network.model.request.bidder.SaleBidderParamRequest
import com.arifwidayana.shared.data.network.model.response.sale.order.SaleOrderParamResponse
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.ext.source
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class StatusFragment(
    private val dataProduct: SaleOrderParamResponse
) : BaseBottomSheetDialogFragment<FragmentStatusBinding, StatusViewModel>(
    FragmentStatusBinding::inflate
) {
    override val viewModel: StatusViewModel by inject()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        onCheckChanged()
    }

    private fun onClick() {
        binding.apply {
            rgStatus.setOnCheckedChangeListener { _, _ ->
                btnChatBuyer.isEnabled = true
            }
            btnChatBuyer.setOnClickListener {
                when (rgStatus.checkedRadioButtonId) {
                    rbAccepted.id -> postStatusOffer(Constant.ACCEPTED)
                    rbDeclined.id -> postStatusOffer(Constant.DECLINED)
                }
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.statusProductOfferResult.collect {
                        it.source(
                            doOnLoading = {
                            },
                            doOnSuccess = { source ->
                                showMessageToast(true, source.payload?.status)
                                dismiss()
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
    }

    private fun onCheckChanged() {
        binding.apply {
            tvSubtitleAccepted.setOnClickListener { rbAccepted.isChecked = true }
            tvSubtitleDeclined.setOnClickListener { rbDeclined.isChecked = true }
        }
    }

    private fun postStatusOffer(status: String) {
        viewModel.statusProductOffer(
            SaleBidderParamRequest(
                idOffer = dataProduct.id,
                status = status
            )
        )
    }
}
