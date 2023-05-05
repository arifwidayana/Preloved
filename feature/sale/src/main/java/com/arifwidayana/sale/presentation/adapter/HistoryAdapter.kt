package com.arifwidayana.sale.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.arifwidayana.shared.data.network.model.response.sale.history.SaleHistoryParamResponse
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.style.databinding.CardHistorySaleBinding

class HistoryAdapter(
    private val context: Context,
    private val onClick: (Int) -> Unit
) : ListAdapter<SaleHistoryParamResponse, HistoryAdapter.HistoryHolder>(
    Differ()
) {
    class HistoryHolder(
        private val binding: CardHistorySaleBinding,
        private val context: Context,
        private val onClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SaleHistoryParamResponse) {
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

    class Differ : DiffUtil.ItemCallback<SaleHistoryParamResponse>() {
        override fun areItemsTheSame(
            oldItem: SaleHistoryParamResponse,
            newItem: SaleHistoryParamResponse
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SaleHistoryParamResponse,
            newItem: SaleHistoryParamResponse
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val binding = CardHistorySaleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryHolder(binding, context, onClick)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}