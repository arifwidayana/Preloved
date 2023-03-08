package com.arifwidayana.account.presentation.adapter.wishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.arifwidayana.home.databinding.CardProductBinding
import com.arifwidayana.shared.data.network.model.response.account.wishlist.WishlistAccountParamResponse

class WishlistAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<WishlistAccountParamResponse, WishlistAdapter.OrderHolder>(
        Differ()
    ) {
    class OrderHolder(
        private val binding: CardProductBinding,
        private val onClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: WishlistAccountParamResponse) {
            binding.apply {
                data.product.let {
                    sivImageItem.load(it.imageUrl) {
                        scale(Scale.FILL)
                    }
                    tvStatusProduct.text = it.status
                    tvNameItem.text = it.name
                    tvCategoryItem.text = it.categories
                    tvPriceItem.text = it.basePrice
                }

                root.setOnClickListener {
                    onClick.invoke(data.productId)
                }
            }
        }
    }

    class Differ : DiffUtil.ItemCallback<WishlistAccountParamResponse>() {
        override fun areItemsTheSame(
            oldItem: WishlistAccountParamResponse,
            newItem: WishlistAccountParamResponse
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: WishlistAccountParamResponse,
            newItem: WishlistAccountParamResponse
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