package com.arifwidayana.sale.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.arifwidayana.shared.data.network.model.response.sale.sold.SaleProductSoldParamResponse
import com.arifwidayana.style.databinding.CardProductSaleSoldBinding

class SoldAdapter(
    private val onClick: (Int) -> Unit
) : ListAdapter<SaleProductSoldParamResponse, SoldAdapter.SoldHolder>(
    Differ()
) {
    class SoldHolder(
        private val binding: CardProductSaleSoldBinding,
        private val onClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SaleProductSoldParamResponse) {
            binding.apply {
                sivProductImage.load(data.imageUrl) {
                    scale(Scale.FILL)
                }
                tvStatusProduct.text = data.status
                tvTransactionDate.text = data.updatedAt
                tvProductName.text = data.name
                tvBasePrice.text = data.basePrice
                tvCategory.text = data.categories
                root.setOnClickListener {
                    onClick.invoke(data.id)
                }
            }
        }
    }

    class Differ : DiffUtil.ItemCallback<SaleProductSoldParamResponse>() {
        override fun areItemsTheSame(
            oldItem: SaleProductSoldParamResponse,
            newItem: SaleProductSoldParamResponse
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SaleProductSoldParamResponse,
            newItem: SaleProductSoldParamResponse
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoldHolder {
        val binding = CardProductSaleSoldBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SoldHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: SoldHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}