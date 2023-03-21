package com.arifwidayana.notification.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.arifwidayana.shared.data.network.model.response.notification.NotificationParamResponse
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.style.databinding.CardNotificationBinding

class NotificationAdapter(
    private val context: Context,
    private val onClick: (NotificationParamResponse) -> Unit
) : ListAdapter<NotificationParamResponse, NotificationAdapter.NotificationHolder>(
    Differ()
) {
    class NotificationHolder(
        private val binding: CardNotificationBinding,
        private val context: Context,
        private val onClick: (NotificationParamResponse) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NotificationParamResponse) {
            binding.apply {
                data.let {
                    sivProductImage.load(it.imageUrl) {
                        scale(Scale.FILL)
                    }
                    tvBasePrice.text = it.basePrice
                    tvTransactionDate.text = it.transactionDate
                    tvProductName.text = it.productName
                    tvBidPrice.text = it.bidPrice
                    tvStatusProduct.text = it.statusProduct
                    it.state.forEach { res ->
                        if (res.first == Constant.NOTIFICATION_MESSAGE) {
                            tvNotificationMessage.text = context.getString(res.second)
                        }
                    }
                    if (it.status == Constant.CREATE) {
                        tvBidPrice.visibility = ViewGroup.GONE
                    }
                }
                root.setOnClickListener {
                    onClick.invoke(data)
                }
            }
        }
    }

    class Differ : DiffUtil.ItemCallback<NotificationParamResponse>() {
        override fun areItemsTheSame(
            oldItem: NotificationParamResponse,
            newItem: NotificationParamResponse
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: NotificationParamResponse,
            newItem: NotificationParamResponse
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        val binding = CardNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationHolder(binding, context, onClick)
    }

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}