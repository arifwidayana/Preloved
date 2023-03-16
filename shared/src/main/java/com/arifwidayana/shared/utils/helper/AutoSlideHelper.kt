package com.arifwidayana.shared.utils.helper

import android.os.Handler
import android.os.Looper
import androidx.viewpager2.widget.ViewPager2
import com.arifwidayana.shared.utils.Constant.DELAY_AUTO_SLIDE
import com.arifwidayana.shared.utils.Constant.LAST_AUTO_SLIDE

class AutoSlideHelper(private val viewPager2: ViewPager2) {
    private var autoSlideHandler = Handler(Looper.getMainLooper())
    private var autoSlideRunnable: Runnable? = null
    private var autoSlideEnabled = true
    private var lastSlideTime = LAST_AUTO_SLIDE

    init {
        autoSlideRunnable = object : Runnable {
            override fun run() {
                if (autoSlideEnabled && System.currentTimeMillis() - lastSlideTime >= DELAY_AUTO_SLIDE) {
                    viewPager2.adapter?.itemCount?.let {
                        val nextBanner = (viewPager2.currentItem + 1) % it
                        viewPager2.setCurrentItem(nextBanner, true)
                        lastSlideTime = System.currentTimeMillis()
                    }
                }
                autoSlideHandler.postDelayed(this, DELAY_AUTO_SLIDE)
            }
        }
    }

    fun startAutoSlide() {
        autoSlideEnabled = true
        lastSlideTime = System.currentTimeMillis()
        autoSlideHandler.postDelayed(autoSlideRunnable!!, DELAY_AUTO_SLIDE)
    }

    fun stopAutoSlide() {
        autoSlideEnabled = false
        autoSlideHandler.removeCallbacks(autoSlideRunnable!!)
    }
}