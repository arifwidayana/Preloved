package com.arifwidayana.sale.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.arifwidayana.shared.data.network.model.response.sale.product.SaleProductParamResponse
import com.arifwidayana.style.databinding.CardProductBinding

class ProductAdapter(
    private val onClick: (Int) -> Unit
) : ListAdapter<SaleProductParamResponse, ProductAdapter.ProductHolder>(
    Differ()
) {
    class ProductHolder(
        private val binding: CardProductBinding,
        private val onClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SaleProductParamResponse) {
            binding.apply {
                sivImageItem.load(data.imageUrl) {
                    scale(Scale.FILL)
                }
                tvStatusProduct.visibility = View.GONE
                tvNameItem.text = data.name
                tvPriceItem.text = data.basePrice
                tvCategoryItem.text = data.categories
                root.setOnClickListener {
                    onClick.invoke(data.id)
                }
            }
        }
    }

    class Differ : DiffUtil.ItemCallback<SaleProductParamResponse>() {
        override fun areItemsTheSame(
            oldItem: SaleProductParamResponse,
            newItem: SaleProductParamResponse
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SaleProductParamResponse,
            newItem: SaleProductParamResponse
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = CardProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}