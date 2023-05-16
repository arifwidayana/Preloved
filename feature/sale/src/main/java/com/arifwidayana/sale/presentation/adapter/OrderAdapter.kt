package com.arifwidayana.sale.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.arifwidayana.shared.data.network.model.response.sale.order.SaleOrderParamResponse
import com.arifwidayana.style.databinding.CardProductSaleOrderBinding

class OrderAdapter(
    private val onClick: (Int) -> Unit
) : ListAdapter<SaleOrderParamResponse, OrderAdapter.OrderHolder>(
    Differ()
) {
    class OrderHolder(
        private val binding: CardProductSaleOrderBinding,
        private val onClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SaleOrderParamResponse) {
            binding.apply {
                sivProductImage.load(data.imageProductUrl) { scale(Scale.FILL) }
                tvStatusProduct.text = data.status
                tvTransactionDate.text = data.transactionDate
                tvProductName.text = data.productName
                tvBasePrice.text = data.basePrice
                tvOfferPrice.text = data.price
                root.setOnClickListener {
                    onClick.invoke(data.id)
                }
            }
        }
    }

    class Differ : DiffUtil.ItemCallback<SaleOrderParamResponse>() {
        override fun areItemsTheSame(
            oldItem: SaleOrderParamResponse,
            newItem: SaleOrderParamResponse
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SaleOrderParamResponse,
            newItem: SaleOrderParamResponse
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val binding = CardProductSaleOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}