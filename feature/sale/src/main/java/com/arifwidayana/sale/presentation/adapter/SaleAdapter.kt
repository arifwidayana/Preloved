package com.arifwidayana.sale.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.arifwidayana.sale.presentation.ui.history.HistoryFragment
import com.arifwidayana.sale.presentation.ui.order.OrderFragment
import com.arifwidayana.sale.presentation.ui.product.ProductFragment
import com.arifwidayana.sale.presentation.ui.sold.SoldFragment

class SaleAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val listFragment = arrayListOf(
        ProductFragment(),
        OrderFragment(),
        SoldFragment(),
        HistoryFragment()
    )

    override fun getItemCount(): Int {
        return listFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return listFragment[position]
    }
}