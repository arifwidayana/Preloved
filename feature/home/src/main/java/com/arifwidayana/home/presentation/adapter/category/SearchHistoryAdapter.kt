package com.arifwidayana.home.presentation.adapter.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arifwidayana.home.databinding.CardSearchHistoryBinding
import com.arifwidayana.shared.data.local.model.entity.SearchHistoryParamEntity

class SearchHistoryAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<SearchHistoryParamEntity, SearchHistoryAdapter.HistorySearchHolder>(
    Differ()
) {
    class HistorySearchHolder(
        private val binding: CardSearchHistoryBinding,
        private val onClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentHistory: SearchHistoryParamEntity) {
            binding.apply {
                tvHistorySearch.text = currentHistory.searchHistoryName

                root.setOnClickListener {
                    onClick.invoke(currentHistory.searchHistoryName)
                }
            }
        }
    }

    class Differ : DiffUtil.ItemCallback<SearchHistoryParamEntity>() {
        override fun areItemsTheSame(
            oldItem: SearchHistoryParamEntity,
            newItem: SearchHistoryParamEntity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SearchHistoryParamEntity,
            newItem: SearchHistoryParamEntity
        ): Boolean {
            return oldItem.searchHistoryName == newItem.searchHistoryName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorySearchHolder {
        val binding =
            CardSearchHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistorySearchHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: HistorySearchHolder, position: Int) {
        holder.bind(getItem(position))
    }
}