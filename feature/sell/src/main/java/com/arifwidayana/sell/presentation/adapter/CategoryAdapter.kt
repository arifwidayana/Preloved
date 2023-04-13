package com.arifwidayana.sell.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arifwidayana.sell.presentation.ui.sell.SellViewModel
import com.arifwidayana.shared.data.network.model.request.sell.category.SellCategoryRequest
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryParamResponse
import com.arifwidayana.style.databinding.CardCategoryProductBinding

class CategoryAdapter(
    private val sellViewModel: SellViewModel
) : ListAdapter<CategoryParamResponse, CategoryAdapter.CategoryHolder>(
    Differ()
) {
    class CategoryHolder(
        private val binding: CardCategoryProductBinding,
        private val sellViewModel: SellViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CategoryParamResponse) {
            val result = sellViewModel.listCategoryResult.map { it.id }
            binding.apply {
                checkbox.text = data.name
                checkbox.isChecked = result.contains(data.id)
                checkbox.setOnClickListener {
                    sellViewModel.listCategory(
                        SellCategoryRequest(
                            id = data.id,
                            name = data.name
                        )
                    )
                }
            }
        }
    }

    class Differ : DiffUtil.ItemCallback<CategoryParamResponse>() {
        override fun areItemsTheSame(
            oldItem: CategoryParamResponse,
            newItem: CategoryParamResponse
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CategoryParamResponse,
            newItem: CategoryParamResponse
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val binding =
            CardCategoryProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(binding, sellViewModel)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}