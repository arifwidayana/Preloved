package com.arifwidayana.notification.presentation.ui

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.arifwidayana.core.base.BaseFragment
import com.arifwidayana.notification.R
import com.arifwidayana.notification.databinding.FragmentNotificationBinding
import com.arifwidayana.notification.presentation.adapter.NotificationAdapter
import com.arifwidayana.shared.data.network.model.response.notification.NotificationParamResponse
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.ext.source
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class NotificationFragment : BaseFragment<FragmentNotificationBinding, NotificationViewModel>(
    FragmentNotificationBinding::inflate
) {
    override val viewModel: NotificationViewModel by inject()

    override fun initView() {
        onView()
    }

    private fun onView() {
        viewModel.getNotify()
    }

    override fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getNotifyResult.collect {
                    it.source(
                        doOnLoading = {},
                        doOnSuccess = { result ->
                            setStateNotification(result.payload)
                        },
                        doOnError = { error ->
                            showMessageSnackBar(true, exception = error.exception)
                        }
                    )
                }
            }
        }
    }

    private fun setStateNotification(data: NotifyParamDataResult?) {
        binding.apply {
            data?.let {
                val adapter = NotificationAdapter(requireContext()) {
                    viewModel.readNotification(it.id)
                    val parcel = Bundle().apply { putInt("id", it.productId) }
                    moveNav(navigateFrag(it), parcel)
                }
                adapter.submitList(data.sortedByDescending { it.id })
                rvNotification.adapter = adapter
            }
        }
    }

    private fun navigateFrag(data: NotificationParamResponse): Int {
        return when (data.status) {
            Constant.BID -> {
                if (data.notificationType == Constant.BUYER) {
                    R.id.action_notificationFragment_to_bid_nav
                } else {
                    R.id.action_notificationFragment_to_account_nav
                }
            }
            Constant.CREATE -> R.id.action_notificationFragment_to_account_nav
            else -> R.id.action_notificationFragment_to_bid_nav
        }
    }
}