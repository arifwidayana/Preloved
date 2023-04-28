package com.arifwidayana.sale.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.arifwidayana.shared.data.network.model.response.sale.history.HistoryParamResponse
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.style.databinding.CardHistorySaleBinding

class HistoryAdapter(
    private val context: Context,
    private val onClick: (Int) -> Unit
) : ListAdapter<HistoryParamResponse, HistoryAdapter.NotificationHolder>(
    Differ()
) {
    class NotificationHolder(
        private val binding: CardHistorySaleBinding,
        private val context: Context,
        private val onClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HistoryParamResponse) {
            binding.apply {
                data.let {
                    sivProductImage.load(it.imageUrl) {
                        scale(Scale.FILL)
                    }
                    if (it.status.first == Constant.SALE_HISTORY_STATUS) {
                        tvStatusProduct.text = context.getString(it.status.second)
                    }
                    tvBasePrice.text = it.product.basePrice
                    tvTransactionDate.text = it.transactionDate
                    tvProductName.text = it.productName
                    tvBidPrice.text = it.price
                }
                root.setOnClickListener {
                    onClick.invoke(data.product.id)
                }
            }
        }
    }

    class Differ : DiffUtil.ItemCallback<HistoryParamResponse>() {
        override fun areItemsTheSame(
            oldItem: HistoryParamResponse,
            newItem: HistoryParamResponse
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: HistoryParamResponse,
            newItem: HistoryParamResponse
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        val binding = CardHistorySaleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationHolder(binding, context, onClick)
    }

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}