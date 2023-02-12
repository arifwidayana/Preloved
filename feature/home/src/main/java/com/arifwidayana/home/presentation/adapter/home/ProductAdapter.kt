package com.arifwidayana.home.presentation.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.arifwidayana.home.databinding.CardCategoryProductBinding
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import com.arifwidayana.shared.utils.Constant.SOLD
import com.arifwidayana.shared.utils.ext.convertCurrency
import com.arifwidayana.style.R

typealias BuyerProductParam = BuyerProductParamResponse

class ProductAdapter(
    private val onClick: (Int) -> Unit
) : PagingDataAdapter<BuyerProductParam, ProductAdapter.ProductViewHolder>(DIFF_CALLBACK) {

    inner class ProductViewHolder(
        private val binding: CardCategoryProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BuyerProductParam) {
            with(binding) {
                sivImageItem.load(data.imageUrl) {
                    placeholder(R.drawable.ic_account)
                }
                tvStatusProduct.text = data.status
                if (data.status != SOLD) {
                    mcvStatusProduct.visibility = View.GONE
                }
                tvNameItem.text = data.name
                tvPriceItem.text = convertCurrency(data.basePrice)
                tvCategoryItem.text = data.categories

                root.setOnClickListener {
                    onClick.invoke(data.id)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = CardCategoryProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BuyerProductParam>() {
            override fun areItemsTheSame(oldItem: BuyerProductParam, newItem: BuyerProductParam): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: BuyerProductParam, newItem: BuyerProductParam): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}