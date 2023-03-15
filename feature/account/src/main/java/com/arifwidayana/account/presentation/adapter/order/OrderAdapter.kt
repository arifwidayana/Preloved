package com.arifwidayana.account.presentation.adapter.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.arifwidayana.style.databinding.CardProductBinding
import com.arifwidayana.shared.data.network.model.response.account.order.OrderAccountParamResponse

class OrderAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<OrderAccountParamResponse, OrderAdapter.OrderHolder>(
        Differ()
    ) {
    class OrderHolder(
        private val binding: CardProductBinding,
        private val onClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: OrderAccountParamResponse) {
            binding.apply {
                sivImageItem.load(data.imageProduct) {
                    scale(Scale.FILL)
                }
                tvStatusProduct.text = data.status
                tvNameItem.text = data.productName
                tvCategoryItem.text = data.transactionDate
                tvPriceItem.text = data.price
                root.setOnClickListener {
                    onClick.invoke(data.productId)
                }
            }
        }
    }

    class Differ : DiffUtil.ItemCallback<OrderAccountParamResponse>() {
        override fun areItemsTheSame(
            oldItem: OrderAccountParamResponse,
            newItem: OrderAccountParamResponse
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: OrderAccountParamResponse,
            newItem: OrderAccountParamResponse
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val binding =
            CardProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}