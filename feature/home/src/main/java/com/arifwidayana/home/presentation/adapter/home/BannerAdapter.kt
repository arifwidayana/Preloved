package com.arifwidayana.home.presentation.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.arifwidayana.home.databinding.CardBannerProductBinding
import com.arifwidayana.shared.data.network.model.response.home.banner.BannerParamResponse
import com.arifwidayana.style.R

class BannerAdapter : ListAdapter<BannerParamResponse, BannerAdapter.BannerHolder>(
    Differ()
) {
    class BannerHolder(
        private val binding: CardBannerProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentBanner: BannerParamResponse) {
            binding.apply {
                sivBanner.load(currentBanner.imageUrl) {
                    placeholder(R.color.white)
                    scale(Scale.FILL)
                }
            }
        }
    }

    class Differ : DiffUtil.ItemCallback<BannerParamResponse>() {
        override fun areItemsTheSame(
            oldItem: BannerParamResponse,
            newItem: BannerParamResponse
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BannerParamResponse,
            newItem: BannerParamResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerHolder {
        val binding =
            CardBannerProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerHolder, position: Int) {
        holder.bind(getItem(position))
    }
}