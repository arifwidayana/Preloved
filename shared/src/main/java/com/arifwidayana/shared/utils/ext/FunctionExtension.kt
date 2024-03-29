package com.arifwidayana.shared.utils.ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SearchView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

fun EditText.changed(
    beforeTextChanged: ((String) -> Unit)? = null,
    onTextChanged: ((String) -> Unit)? = null,
    afterTextChanged: ((String) -> Unit)? = null
) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            beforeTextChanged?.invoke(s.toString())
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged?.invoke(s.toString())
        }

        override fun afterTextChanged(editable: Editable?) {
            this@changed.removeTextChangedListener(this).also {
                addTextChangedListener(this)
            }
            afterTextChanged?.invoke(editable.toString())
        }
    })
}

fun TabLayout.changed(
    onTabSelected: ((Int) -> Unit)? = null,
    onTabUnselected: ((Int) -> Unit)? = null,
    onTabReselected: ((Int) -> Unit)? = null
) {
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            onTabSelected?.invoke(tab?.id ?: 0)
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            onTabUnselected?.invoke(tab?.id ?: 0)
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
            onTabReselected?.invoke(tab?.id ?: 0)
        }
    })
}

fun SearchView.changed(
    onQueryTextSubmit: ((String) -> Unit)? = null,
    onQueryTextChange: ((String) -> Unit)? = null
) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            onQueryTextSubmit?.invoke(query.toString())
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            onQueryTextChange?.invoke(newText.toString())
            return false
        }
    })
}

fun ViewPager2.changed(
    onPageScrolled: ((Int, Float, Int) -> Unit)? = null,
    onPageSelected: ((Int) -> Unit)? = null,
    onPageScrollStateChanged: ((Int) -> Unit)? = null
) {
    this.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            onPageScrolled?.invoke(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            onPageSelected?.invoke(position)
        }

        override fun onPageScrollStateChanged(state: Int) {
            onPageScrollStateChanged?.invoke(state)
        }
    })
}